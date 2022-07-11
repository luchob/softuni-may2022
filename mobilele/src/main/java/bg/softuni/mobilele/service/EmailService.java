package bg.softuni.mobilele.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

  private final TemplateEngine templateEngine;
  private final JavaMailSender javaMailSender;

  public EmailService(TemplateEngine templateEngine,
                      JavaMailSender javaMailSender) {
    this.templateEngine = templateEngine;
    this.javaMailSender = javaMailSender;
  }

  public void sendRegistrationEmail(
    String userEmail,
    String userName
  ) {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    try {
      MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
      mimeMessageHelper.setFrom("mobilele@mobilele.com");
      mimeMessageHelper.setTo(userEmail);
      mimeMessageHelper.setSubject("Welcome!");
      mimeMessageHelper.setText(generateMessageContent(userName), true);

      javaMailSender.send(mimeMessageHelper.getMimeMessage());
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  private String generateMessageContent(String userName) {
    Context ctx = new Context();
    ctx.setVariable("userName", userName);
    return templateEngine.process("email/registration", ctx);
  }

}
