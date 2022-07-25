package bg.softuni.mobilele.model.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class VoteKey implements Serializable {

  private static final long serialVersionUID = 4200522060011097140L;
  @ManyToOne
  @JoinColumn(name = "user_entity_id")
  private UserEntity userEntity;

  @ManyToOne
  @JoinColumn(name = "offer_entity_id")
  private OfferEntity offerEntity;

  public OfferEntity getOfferEntity() {
    return offerEntity;
  }

  public VoteKey setOfferEntity(OfferEntity offerEntity) {
    this.offerEntity = offerEntity;
    return this;
  }

  public UserEntity getUserEntity() {
    return userEntity;
  }

  public VoteKey setUserEntity(UserEntity userEntity) {
    this.userEntity = userEntity;
    return this;
  }


}
