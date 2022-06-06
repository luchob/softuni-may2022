package bg.softuni.mobilele.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

  @Column(nullable = false,
    unique = true)
  private String email;

  private String password;
  @Column(nullable = false)
  private String firstName;
  @Column(nullable = false)
  private String lastName;
  private boolean isActive;
  private String imageUrl;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<UserRoleEntity> userRoles = new ArrayList<>();

  public String getEmail() {
    return email;
  }

  public UserEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserEntity setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserEntity setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isActive() {
    return isActive;
  }

  public UserEntity setActive(boolean active) {
    isActive = active;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public UserEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public List<UserRoleEntity> getUserRoles() {
    return userRoles;
  }

  public UserEntity setUserRoles(List<UserRoleEntity> userRoles) {
    this.userRoles = userRoles;
    return this;
  }

  public UserEntity addRole(UserRoleEntity userRole) {
    this.userRoles.add(userRole);
    return this;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", isActive=" + isActive +
        ", imageUrl='" + imageUrl + '\'' +
        ", userRoles=" + userRoles +
        '}';
  }
}
