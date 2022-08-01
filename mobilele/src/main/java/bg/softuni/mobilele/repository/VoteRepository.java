package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, Long> {
  Optional<VoteEntity> findVoteEntityByOffer_Id(UUID offerID);
}
