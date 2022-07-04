package bg.softuni.mobilele.config;

import bg.softuni.mobilele.model.user.MobileleUserDetails;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.MobileleUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  //Here we have to expose 3 things:
  // 1. PasswordEncoder
  // 2. SecurityFilterChain
  // 3. UserDetailsService

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Pbkdf2PasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.
        // define which requests are allowed and which not
            authorizeRequests().
        // everyone can download static resources (css, js, images)
            requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
        // everyone can login and register
            antMatchers("/", "/users/login", "/users/register").permitAll().
        // all other pages are available for logger in users
            anyRequest().
        authenticated().
        and().
        // configuration of form login
            formLogin().
        // the custom login form
            loginPage("/users/login").
        // the name of the username form field
            usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
        // the name of the password form field
            passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
        // where to go in case that the login is successful
            defaultSuccessUrl("/").
        // where to go in case that the login failed
            failureForwardUrl("/users/login-error").
        and().
        // configure logut
            logout().
        // which is the logout url, must be POST request
            logoutUrl("/users/logout").
        // on logout go to the home page
            logoutSuccessUrl("/").
        // invalidate the session and delete the cookies
            invalidateHttpSession(true).
        deleteCookies("JSESSIONID");


    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return new MobileleUserDetailsService(userRepository);
  }
}
