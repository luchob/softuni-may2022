package bg.softuni.mobilele.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

  private String name;
  private boolean loggedIn;

  public String getName() {
    return name;
  }

  public CurrentUser setName(String name) {
    this.name = name;
    return this;
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }

  public boolean isAnonymous() {
    return !isLoggedIn();
  }

  public CurrentUser setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
    return this;
  }

  public void clear() {
    loggedIn = false;
    name = null;
  }
}
