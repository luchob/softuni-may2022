package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "offers")
public class OfferEntity  {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Type(type = "uuid-char")
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EngineEnum engine;

  private String imageUrl;

  private int mileage;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TransmissionEnum transmission;

  private int year;

  @ManyToOne
  private ModelEntity model;

  @ManyToOne
  private UserEntity seller;

  public UUID getId() {
    return id;
  }

  public OfferEntity setId(UUID id) {
    this.id = id;
    return this;
  }

  public EngineEnum getEngine() {
    return engine;
  }

  public OfferEntity setEngine(EngineEnum engine) {
    this.engine = engine;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getMileage() {
    return mileage;
  }

  public OfferEntity setMileage(int mileage) {
    this.mileage = mileage;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public OfferEntity setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public TransmissionEnum getTransmission() {
    return transmission;
  }

  public OfferEntity setTransmission(TransmissionEnum transmission) {
    this.transmission = transmission;
    return this;
  }

  public int getYear() {
    return year;
  }

  public OfferEntity setYear(int year) {
    this.year = year;
    return this;
  }

  public ModelEntity getModel() {
    return model;
  }

  public OfferEntity setModel(ModelEntity model) {
    this.model = model;
    return this;
  }

  public UserEntity getSeller() {
    return seller;
  }

  public OfferEntity setSeller(UserEntity seller) {
    this.seller = seller;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public String toString() {
    return "OfferEntity{" +
        "id=" + id +
        ", engine=" + engine +
        ", imageUrl='" + imageUrl + '\'' +
        ", mileage=" + mileage +
        ", price=" + price +
        ", description='" + description + '\'' +
        ", transmission=" + transmission +
        ", year=" + year +
        ", model=" + model +
        ", seller=" + seller +
        '}';
  }
}
