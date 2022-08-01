package bg.softuni.mobilele.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VoteEntity extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "offer_id")
  private OfferEntity offer;

  private int positive;
  private int negative;

  public OfferEntity getOffer() {
    return offer;
  }

  public void setOffer(OfferEntity offer) {
    this.offer = offer;
  }

  public int getPositive() {
    return positive;
  }

  public VoteEntity setPositive(int positive) {
    this.positive = positive;
    return this;
  }

  public int getNegative() {
    return negative;
  }

  public VoteEntity setNegative(int negative) {
    this.negative = negative;
    return this;
  }
}
