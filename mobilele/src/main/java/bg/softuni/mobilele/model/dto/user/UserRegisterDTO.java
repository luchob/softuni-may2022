package bg.softuni.mobilele.model.dto.user;

import bg.softuni.mobilele.model.validation.FieldMatch;
import bg.softuni.mobilele.model.validation.UniqueUserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(
    first = "password",
    second = "confirmPassword",
    message = "Passwords do not match."
)
public class UserRegisterDTO {

  @NotEmpty(message = "User email should be provided.")
  @Email(message = "User email should be valid.")
  @UniqueUserEmail(message = "User email should be unique.")
  private String email;

  @NotEmpty
  @Size(min = 2, max = 20)
  private String firstName;
  @NotEmpty
  @Size(min = 2, max = 20)
  private String lastName;
  @NotEmpty
  @Size(min = 5)
  private String password;
  private String confirmPassword;

  public String getFirstName() {
    return firstName;
  }

  public UserRegisterDTO setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserRegisterDTO setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegisterDTO setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public UserRegisterDTO setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserRegisterDTO setEmail(String email) {
    this.email = email;
    return this;
  }
}
