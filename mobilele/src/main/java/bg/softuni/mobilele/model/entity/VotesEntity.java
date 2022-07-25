package bg.softuni.mobilele.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VotesEntity extends BaseEntity {


  @ManyToOne
  @JoinColumn(name = "offer_id")
  private OfferEntity offerEntity;

  private int totalVotes;

  private int upVotes;

  public OfferEntity getOfferEntity() {
    return offerEntity;
  }

  public VotesEntity setOfferEntity(OfferEntity offerEntity) {
    this.offerEntity = offerEntity;
    return this;
  }

  public int getTotalVotes() {
    return totalVotes;
  }

  public VotesEntity setTotalVotes(int totalVotes) {
    this.totalVotes = totalVotes;
    return this;
  }

  public int getUpVotes() {
    return upVotes;
  }

  public VotesEntity setUpVotes(int upVotes) {
    this.upVotes = upVotes;
    return this;
  }
}
