package bg.softuni.mobilele.model.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "votes")
public class VoteEntity {

  @EmbeddedId
  private VoteKey voteId;

  public VoteKey getVoteId() {
    return voteId;
  }

  public VoteEntity setVoteId(VoteKey voteId) {
    this.voteId = voteId;
    return this;
  }
}
