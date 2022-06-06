package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.model.dto.UserRegisterDTO;
import bg.softuni.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/login")
  public String login() {
    return "auth-login";
  }

  @GetMapping("/logout")
  public String logout() {
    userService.logout();
    return "redirect:/";
  }

  @PostMapping("/login")
  public String login(UserLoginDTO userLoginDTO) {
    userService.login(userLoginDTO);
    return "redirect:/";
  }
}
