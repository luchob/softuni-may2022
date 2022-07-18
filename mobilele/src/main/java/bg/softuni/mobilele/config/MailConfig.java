package bg.softuni.mobilele.config;

import org.hibernate.validator.spi.nodenameprovider.Property;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

  @Bean
  public JavaMailSender javaMailSender(
      @Value("${mail.host}") String mailHost,
      @Value("${mail.port}") Integer mailPort,
      @Value("${mail.username}") String userName,
      @Value("${mail.password}") String password

  ) {
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setHost(mailHost);
    javaMailSender.setPort(mailPort);
    javaMailSender.setUsername(userName);
    javaMailSender.setPassword(password);
    javaMailSender.setJavaMailProperties(mailProperties());
    javaMailSender.setDefaultEncoding("UTF-8");

    return javaMailSender;
  }

  private Properties mailProperties() {
    Properties properties = new Properties();
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.transport.protocol", "smtp");
    return properties;
  }

}
