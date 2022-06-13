package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddOfferDTO {

  @NotNull
  private EngineEnum engine;

  @NotNull
  private TransmissionEnum transmission;

  @NotEmpty
  private String imageUrl;

  public EngineEnum getEngine() {
    return engine;
  }

  public AddOfferDTO setEngine(EngineEnum engine) {
    this.engine = engine;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public AddOfferDTO setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public TransmissionEnum getTransmission() {
    return transmission;
  }

  public AddOfferDTO setTransmission(TransmissionEnum transmission) {
    this.transmission = transmission;
    return this;
  }
}
