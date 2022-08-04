package bg.softuni.mobilele.util;

import bg.softuni.mobilele.model.user.MobileleUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestUserDataService implements UserDetailsService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new MobileleUserDetails(1L,
        "topsecret",
        username,
        "Test",
        "user",
        Collections.emptyList());
  }
}
