package bg.softuni.mobilele.model.dto.model;

public class ModelDTO {
  private long id;
  private String name;

  public long getId() {
    return id;
  }

  public ModelDTO setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public ModelDTO setName(String name) {
    this.name = name;
    return this;
  }
}
