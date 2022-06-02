package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserLoginDTO;
import bg.softuni.mobilele.model.dto.UserRegisterDTO;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private UserRepository userRepository;
  private CurrentUser currentUser;
  private PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository,
                     CurrentUser currentUser,
                     PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.currentUser = currentUser;
    this.passwordEncoder = passwordEncoder;
  }

  public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

    UserEntity newUser =
        new UserEntity().
            setActive(true).
            setEmail(userRegisterDTO.getEmail()).
            setFirstName(userRegisterDTO.getFirstName()).
            setLastName(userRegisterDTO.getLastName()).
            setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

    newUser = userRepository.save(newUser);

    login(newUser);
  }

  public boolean login(UserLoginDTO loginDTO) {
    Optional<UserEntity> userOpt = userRepository.
        findByEmail(loginDTO.getUsername());

    if (userOpt.isEmpty()) {
      LOGGER.info("User with not found. User name: {}",
          loginDTO.getUsername());
      return false;
    }

    String rawPassword = loginDTO.getPassword();
    String encodedPassword = userOpt.get().getPassword();

    boolean success = passwordEncoder.
        matches(rawPassword, encodedPassword);

    if (success) {
      login(userOpt.get());
    } else {
      logout();
    }

    return success;
  }

  private void login(UserEntity userEntity) {
    currentUser.
        setLoggedIn(true).
        setName(userEntity.getFirstName() + " " + userEntity.getLastName());
  }

  public void logout() {
    currentUser.clear();
  }

}
