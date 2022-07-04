package bg.softuni.mobilele.model.dto.offer;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AddOfferDTO {

  @NotNull
  @Min(1)
  private Long modelId;
  @NotNull
  private EngineEnum engine;

  @Positive
  @NotNull
  private Integer price;

  @Positive
  @NotNull
  private Integer mileage;

  @Min(1900)
  @NotNull
  private Integer year;

  @NotEmpty
  private String description;

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

  public Long getModelId() {
    return modelId;
  }

  public AddOfferDTO setModelId(Long modelId) {
    this.modelId = modelId;
    return this;
  }

  public Integer getPrice() {
    return price;
  }

  public AddOfferDTO setPrice(Integer price) {
    this.price = price;
    return this;
  }

  public Integer getYear() {
    return year;
  }

  public AddOfferDTO setYear(Integer year) {
    this.year = year;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public AddOfferDTO setDescription(String description) {
    this.description = description;
    return this;
  }

  public Integer getMileage() {
    return mileage;
  }

  public AddOfferDTO setMileage(Integer mileage) {
    this.mileage = mileage;
    return this;
  }
}
