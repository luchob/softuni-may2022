package bg.softuni.mobilele.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MobileleUserDetails implements UserDetails {

  private final String password;
  private final String username;
  private final String firstName;
  private final String lastName;
  private final Collection<GrantedAuthority> authorities;

  public MobileleUserDetails(String password,
                             String username,
                             String firstName,
                             String lastName,
                             Collection<GrantedAuthority> authorities) {
    this.password = password;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.authorities = authorities;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    StringBuilder fullName = new StringBuilder();
    if (getFirstName() != null) {
      fullName.append(getFirstName());
    }
    if (getLastName() != null) {
      if (!fullName.isEmpty()) {
        fullName.append(" ");
      }
      fullName.append(getLastName());
    }

    return fullName.toString();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
