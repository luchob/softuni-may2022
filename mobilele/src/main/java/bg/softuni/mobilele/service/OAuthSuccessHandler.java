package bg.softuni.mobilele.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private UserService userService;

  public OAuthSuccessHandler(UserService userService) {
    this.userService = userService;
    setDefaultTargetUrl("/");
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {

    if (authentication instanceof
        OAuth2AuthenticationToken oAuth2AuthenticationToken) {
      var userEmail = oAuth2AuthenticationToken.
          getPrincipal().
          getAttribute("email").
          toString();

      userService.createUserIfNotExist(userEmail);
      userService.login(userEmail);
    }

    super.onAuthenticationSuccess(request, response, authentication);
  }
}
