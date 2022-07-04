package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.OfferDTOs.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDTOs.CardListingOfferDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private OfferRepository offerRepository;
    private UserRepository userRepository;
    private ModelRepository modelRepository;
    private OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository,
                        UserRepository userRepository,
                        ModelRepository modelRepository,
                        OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerMapper = offerMapper;
    }

    public void addOffer(AddOfferDTO addOfferDTO) {
        OfferEntity newOffer = offerMapper.
                addOfferDtoToOfferEntity(addOfferDTO);

        // TODO - current user should be logged in
//        UserEntity seller = userRepository.findByEmail(currentUser.getEmail()).
//                orElseThrow();

        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).
                orElseThrow();

        newOffer.setModel(model);
        //newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }

    public List<CardListingOfferDTO> findOfferByOfferName(String query) {
        return this.offerRepository
                .findAllByModel_NameContains(query)
                .stream()
                .map(offer -> offerMapper.offerEntityToCardListingOfferDto(offer))
                .toList();
    }
}
