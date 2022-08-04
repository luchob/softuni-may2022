package bg.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaintenanceController {

  @GetMapping("/maintenance")
  public String maintenance() {
    return  "maintenance";
  }

}
