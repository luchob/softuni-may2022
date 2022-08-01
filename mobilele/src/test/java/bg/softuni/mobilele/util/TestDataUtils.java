package bg.softuni.mobilele.util;

import bg.softuni.mobilele.model.entity.*;
import bg.softuni.mobilele.model.enums.CategoryEnum;
import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

import static bg.softuni.mobilele.model.enums.TransmissionEnum.MANUAL;

@Component
public class TestDataUtils {

  private UserRepository userRepository;
  private UserRoleRepository userRoleRepository;
  private OfferRepository offerRepository;
  private ModelRepository modelRepository;
  private BrandRepository brandRepository;

  public TestDataUtils(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       OfferRepository offerRepository,
                       ModelRepository modelRepository,
                       BrandRepository brandRepository) {
    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
    this.offerRepository = offerRepository;
    this.modelRepository = modelRepository;
    this.brandRepository = brandRepository;
  }

  private void initRoles() {
    if (userRoleRepository.count() == 0) {
      UserRoleEntity adminRole = new UserRoleEntity().setUserRole(UserRoleEnum.ADMIN);
      UserRoleEntity userRole = new UserRoleEntity().setUserRole(UserRoleEnum.USER);

      userRoleRepository.save(adminRole);
      userRoleRepository.save(userRole);
    }
  }

  public UserEntity createTestAdmin(String email) {

    initRoles();

    var admin = new UserEntity().
        setEmail(email).
        setFirstName("Admin").
        setLastName("Adminov").
        setActive(true).
        setUserRoles(userRoleRepository.findAll());

    return userRepository.save(admin);
  }

  public UserEntity createTestUser(String email) {

    initRoles();

    var user = new UserEntity().
        setEmail(email).
        setFirstName("User").
        setLastName("Userov").
        setActive(true).
        setUserRoles(userRoleRepository.
            findAll().stream().
            filter(r -> r.getUserRole() != UserRoleEnum.ADMIN).
            toList());

    return userRepository.save(user);
  }

  public OfferEntity createTestOffer(UserEntity seller,
                                     ModelEntity model) {
    var offerEntity = new OfferEntity().
        setEngine(EngineEnum.GASOLINE).
        setMileage(100000).
        setPrice(BigDecimal.TEN).
        setDescription("Test description").
        setTransmission(MANUAL).
        setYear(2000).
        setModel(model).
        setSeller(seller);

    return offerRepository.save(offerEntity);
  }

  public BrandEntity createTestBrand() {
    var brandEntity = new BrandEntity().
        setName("Ford");

    return brandRepository.save(brandEntity);
  }

  public ModelEntity createTestModel(BrandEntity brandEntity) {
    ModelEntity model = new ModelEntity().
        setName("Fiesta").
        setBrand(brandEntity).
        setCategory(CategoryEnum.CAR).
        setImageUrl("http://image.com/image.png").
        setStartYear(1978);

    return modelRepository.save(model);
  }

  public void cleanUpDatabase() {
    offerRepository.deleteAll();
    userRepository.deleteAll();
    userRoleRepository.deleteAll();
    modelRepository.deleteAll();
    brandRepository.deleteAll();
  }
}
