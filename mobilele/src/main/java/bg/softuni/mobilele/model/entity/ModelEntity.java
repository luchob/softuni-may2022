package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private CategoryEnum category;

  @Column(nullable = false)
  private String imageUrl;

  private int startYear;

  private Long endYear;

  @ManyToOne
  private BrandEntity brand;

  public String getName() {
    return name;
  }

  public ModelEntity setName(String name) {
    this.name = name;
    return this;
  }

  public CategoryEnum getCategory() {
    return category;
  }

  public ModelEntity setCategory(CategoryEnum category) {
    this.category = category;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ModelEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getStartYear() {
    return startYear;
  }

  public ModelEntity setStartYear(int startYear) {
    this.startYear = startYear;
    return this;
  }

  public Long getEndYear() {
    return endYear;
  }

  public ModelEntity setEndYear(Long endYear) {
    this.endYear = endYear;
    return this;
  }

  public BrandEntity getBrand() {
    return brand;
  }

  public ModelEntity setBrand(BrandEntity brand) {
    this.brand = brand;
    return this;
  }

  @Override
  public String toString() {
    return "ModelEntity{" +
        "name='" + name + '\'' +
        ", category=" + category +
        ", imageUrl='" + imageUrl + '\'' +
        ", startYear=" + startYear +
        ", endYear=" + endYear +
        ", brand=" + (brand != null ? brand.getName() : null) +
        '}';
  }
}
