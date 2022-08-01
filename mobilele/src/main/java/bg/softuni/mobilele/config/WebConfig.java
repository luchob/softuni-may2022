package bg.softuni.mobilele.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final LocaleChangeInterceptor localeChangeInterceptor;

  public WebConfig(LocaleChangeInterceptor localeChangeInterceptor) {
    this.localeChangeInterceptor = localeChangeInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor);
  }
}
