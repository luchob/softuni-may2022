package bg.softuni.mobilele;

import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.entity.VoteEntity;
import bg.softuni.mobilele.model.entity.VoteKey;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.VoteRepository;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Test implements CommandLineRunner {

  @Autowired
  VoteRepository voteRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  OfferRepository offerRepository;

  @Override
  public void run(String... args) throws Exception {
    OfferEntity offerEntity = offerRepository.findAll().get(0);
    UserEntity userEntity = userRepository.findAll().get(0);

    VoteEntity voteEntity = new VoteEntity();
    voteEntity.setVoteId(new VoteKey().setOfferEntity(offerEntity).setUserEntity(userEntity));

    voteRepository.save(voteEntity);
  }
}
