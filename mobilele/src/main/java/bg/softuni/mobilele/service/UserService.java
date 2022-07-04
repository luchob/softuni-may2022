package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.UserDTOs.UserRegisterDTO;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.UserMapper;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder,
                     UserMapper userMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
  }

  public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

    UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
    newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

    this.userRepository.save(newUser);
    login(newUser);
  }


  private void login(UserEntity userEntity) {
   //todo
  }

}
