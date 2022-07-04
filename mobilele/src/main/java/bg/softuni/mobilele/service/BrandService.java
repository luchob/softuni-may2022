package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.brand.BrandDTO;
import bg.softuni.mobilele.model.dto.model.ModelDTO;
import bg.softuni.mobilele.model.entity.BrandEntity;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {

  private BrandRepository brandRepository;

  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public List<BrandDTO> getAllBrands() {
    return brandRepository.
        findAll().
        stream().
        map(this::mapBrand).
        collect(Collectors.toList());
  }

  private BrandDTO mapBrand(BrandEntity brandEntity) {
    List<ModelDTO> models = brandEntity.
        getModels().
        stream().
        map(this::mapModel).
        toList();

    return new BrandDTO().
        setModels(models).
        setName(brandEntity.getName());
  }

  private ModelDTO mapModel(ModelEntity modelEntity) {
    return new ModelDTO().
        setId(modelEntity.getId()).
        setName(modelEntity.getName());
  }
}
