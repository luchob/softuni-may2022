package bg.softuni.docker.web;

import bg.softuni.docker.model.StudentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

  @GetMapping("/all")
  public List<StudentDTO> all() {
    return List.of(
        new StudentDTO().setAge(21).setName("Pesho"),
        new StudentDTO().setAge(22).setName("Anna")
    );
  }

}
