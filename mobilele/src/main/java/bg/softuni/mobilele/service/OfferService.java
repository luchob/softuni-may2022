package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.offer.CreateOrUpdateOfferDTO;
import bg.softuni.mobilele.model.dto.offer.OfferDetailDTO;
import bg.softuni.mobilele.model.dto.offer.SearchOfferDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.OfferSpecification;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final OfferMapper offerMapper;

    public OfferService(OfferRepository offerRepository,
                        UserRepository userRepository,
                        ModelRepository modelRepository,
                        OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerMapper = offerMapper;
    }

    public boolean isOwner(String userName, UUID offerId) {

        boolean isOwner = offerRepository.
            findById(offerId).
            filter(o -> o.getSeller().getEmail().equals(userName)).
            isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository.
            findByEmail(userName).
            filter(this::isAdmin).
            isPresent();
    }

    private boolean isAdmin(UserEntity user) {
        return user.getUserRoles().
            stream().
            anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public void deleteOfferById(UUID offerId) {
        offerRepository.deleteById(offerId);
    }

    public Page<OfferDetailDTO> getAllOffers(Pageable pageable) {
        return offerRepository.
            findAll(pageable).
            map(offerMapper::offerEntityToOfferDetailDto);

    }

    public Optional<CreateOrUpdateOfferDTO> getOfferEditDetails(UUID offerID) {
        return offerRepository.
            findById(offerID).
            map(offerMapper::offerEntityToCreateOrUpdateOfferDtoTo);
    }

    public Optional<OfferDetailDTO> findOfferByUUID(UUID offerID) {
        return offerRepository.
            findById(offerID).
            map(offerMapper::offerEntityToOfferDetailDto);
    }

    public void addOffer(CreateOrUpdateOfferDTO addOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper.
            createOrUpdateOfferDtoToOfferEntity(addOfferDTO);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).
                orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }


    public List<OfferDetailDTO> searchOffer(SearchOfferDTO searchOfferDTO) {
        return this.offerRepository.findAll(new OfferSpecification(searchOfferDTO)).
            stream().map(offer -> offerMapper.offerEntityToOfferDetailDto(offer)).
            toList();
    }
}
