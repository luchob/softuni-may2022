package bg.softuni.mobilele.web;

import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

  @GetMapping("/login")
  public String login() {
    return "auth-login";
  }
}
