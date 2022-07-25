package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.Column;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {

    var userEntity = new UserEntity().
        setEmail("pesho@example.com").
        setPassword(passwordEncoder.encode("topsecret")).
        setFirstName("Pesho").
        setLastName("Petrov").
        setActive(true);

    userRepository.save(userEntity);
  }

  @AfterEach
  void cleanUp() {
    userRepository.deleteAll();
  }

  @Test
  void testLogin() throws Exception {
    mockMvc.perform(post("/users/login").
            param("username", "pesho@example.com").
            param("password", "topsecret").
            with(csrf())).
        andExpect(status().is3xxRedirection()).
        andExpect(redirectedUrl("/"));
  }

  @Test
  void testFailed() throws Exception {
    mockMvc.perform(post("/users/login").
            param("username", "pesho@example.com").
            param("password", "topwrongpassword").
            with(csrf())).
        andExpect(status().is2xxSuccessful()).
        andExpect(forwardedUrl("/users/login-error"));
  }
}
