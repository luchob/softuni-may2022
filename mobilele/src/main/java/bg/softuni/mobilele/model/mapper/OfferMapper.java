package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.offer.AddOfferDTO;
import bg.softuni.mobilele.model.dto.offer.CardListingOfferDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {
  OfferEntity addOfferDtoToOfferEntity(AddOfferDTO addOfferDTO);

  @Mapping(source = "model.name", target = "model")
  @Mapping(source = "model.brand.name", target = "brand")
  CardListingOfferDTO offerEntityToCardListingOfferDto(OfferEntity offerEntity);
}
