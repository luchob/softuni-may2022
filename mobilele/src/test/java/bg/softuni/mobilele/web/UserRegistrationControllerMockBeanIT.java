package bg.softuni.mobilele.web;

import bg.softuni.mobilele.service.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerMockBeanIT {

  @MockBean
  private EmailService mockEmailService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testRegisterUser() throws Exception {

    mockMvc.perform(post("/users/register").
            param("email", "pesho@example.com").
            param("firstName", "Pesho").
            param("lastName", "Danielov").
            param("password", "topsecret").
            param("confirmPassword", "topsecret").
            cookie(new Cookie("lang", "de_DE")).
            with(csrf())).
        andExpect(status().is3xxRedirection()).
        andExpect(redirectedUrl("/"));

    Mockito.verify(mockEmailService).
        sendRegistrationEmail(
            "pesho@example.com",
            "Pesho Danielov",
            Locale.GERMANY
            );

//    MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
//    assertEquals(receivedMessages.length, 1);
//
//    MimeMessage receivedMessage = receivedMessages[0];
//    assertTrue(receivedMessage.getContent().toString().contains("Pesho Danielov"));
  }
}
