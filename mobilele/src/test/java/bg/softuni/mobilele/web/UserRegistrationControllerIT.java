package bg.softuni.mobilele.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import bg.softuni.mobilele.repository.UserRepository;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.mail.internet.MimeMessage;


@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testRegisterPage() throws Exception {
    mockMvc.perform(get("/users/register"))
        .andExpect(status().isOk())
        .andExpect(view().name("auth-register"));
  }

  @Nested
  @DisplayName("Registration email test")
  class RegistrationTest {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    private GreenMail greenMail;

    @BeforeEach
    void setup() {
      greenMail = new GreenMail(new ServerSetup(port, host, "smtp"));
      greenMail.start();
      greenMail.setUser(username, password);
    }

    @AfterEach
    void cleanup() {
      greenMail.stop();
    }

    @Test
    public void testRegisterUser() throws Exception {

      mockMvc.perform(post("/users/register").
              param("email", "pesho@example.com").
              param("firstName", "Pesho").
              param("lastName", "Danielov").
              param("password", "topsecret").
              param("confirmPassword", "topsecret").
              with(csrf())).
          andExpect(status().is3xxRedirection()).
          andExpect(redirectedUrl("/"));

      MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
      assertEquals(receivedMessages.length, 1);

      MimeMessage receivedMessage = receivedMessages[0];
      assertTrue(receivedMessage.getContent().toString().contains("Pesho Danielov"));
    }
  }






}
