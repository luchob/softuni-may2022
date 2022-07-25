package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.VoteEntity;
import bg.softuni.mobilele.model.entity.VoteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<VoteEntity, VoteKey> {
}
