package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

import java.util.UUID;

public class OfferDTO {

  private UUID id;
  private EngineEnum engine;

  private Integer price;

  private Integer mileage;

  private Integer year;

  private String description;

  private TransmissionEnum transmission;

  private String imageUrl;

  public EngineEnum getEngine() {
    return engine;
  }

  public OfferDTO setEngine(EngineEnum engine) {
    this.engine = engine;
    return this;
  }

  public Integer getPrice() {
    return price;
  }

  public OfferDTO setPrice(Integer price) {
    this.price = price;
    return this;
  }

  public Integer getMileage() {
    return mileage;
  }

  public OfferDTO setMileage(Integer mileage) {
    this.mileage = mileage;
    return this;
  }

  public Integer getYear() {
    return year;
  }

  public OfferDTO setYear(Integer year) {
    this.year = year;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public OfferDTO setDescription(String description) {
    this.description = description;
    return this;
  }

  public TransmissionEnum getTransmission() {
    return transmission;
  }

  public OfferDTO setTransmission(TransmissionEnum transmission) {
    this.transmission = transmission;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferDTO setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public UUID getId() {
    return id;
  }

  public OfferDTO setId(UUID id) {
    this.id = id;
    return this;
  }
}
