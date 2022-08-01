package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.offer.CreateOrUpdateOfferDTO;
import bg.softuni.mobilele.model.dto.offer.OfferDetailDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {
  OfferEntity createOrUpdateOfferDtoToOfferEntity(CreateOrUpdateOfferDTO addOfferDTO);

  CreateOrUpdateOfferDTO offerEntityToCreateOrUpdateOfferDtoTo(OfferEntity offerEntity);

  @Mapping(source = "model.name", target = "model")
  @Mapping(source = "model.brand.name", target = "brand")
  @Mapping(source = "seller.firstName", target = "sellerFirstName")
  @Mapping(source = "seller.lastName", target = "sellerLastName")
  OfferDetailDTO offerEntityToOfferDetailDto(OfferEntity offerEntity);
}
