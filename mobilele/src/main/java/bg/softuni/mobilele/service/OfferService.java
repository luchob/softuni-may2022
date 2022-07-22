package bg.softuni.mobilele.service;

import bg.softuni.mobilele.exception.ObjectNotFoundException;
import bg.softuni.mobilele.model.dto.offer.AddOfferDTO;
import bg.softuni.mobilele.model.dto.offer.OfferDetailDTO;
import bg.softuni.mobilele.model.dto.offer.SearchOfferDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.OfferSpecification;
import bg.softuni.mobilele.repository.UserRepository;
import com.lowagie.text.DocumentException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final OfferMapper offerMapper;
    private final TemplateEngine templateEngine;

    public OfferService(OfferRepository offerRepository,
                        UserRepository userRepository,
                        ModelRepository modelRepository,
                        OfferMapper offerMapper,
                        TemplateEngine templateEngine) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerMapper = offerMapper;
        this.templateEngine = templateEngine;
    }

    public List<OfferDetailDTO> getAllOffers(SearchOfferDTO searchOfferDTO) {
        return offerRepository.
            findAll(new OfferSpecification(searchOfferDTO)).
            stream().
            map(offerMapper::offerEntityToCardListingOfferDto).
            toList();
    }

    public Page<OfferDetailDTO> getAllOffers(Pageable pageable) {
        return offerRepository.
            findAll(pageable).
            map(offerMapper::offerEntityToCardListingOfferDto);

    }

    public void addOffer(AddOfferDTO addOfferDTO, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper.
                addOfferDtoToOfferEntity(addOfferDTO);

        UserEntity seller = userRepository.findByEmail(userDetails.getUsername()).
                orElseThrow();

        ModelEntity model = modelRepository.findById(addOfferDTO.getModelId()).
                orElseThrow();

        newOffer.setModel(model);
        newOffer.setSeller(seller);

        offerRepository.save(newOffer);
    }

    public byte[] generateOfferPDF(UUID offerUUID){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        OfferDetailDTO offerDetailDTO =
            offerRepository.
                findById(offerUUID).
                map(offerMapper::offerEntityToCardListingOfferDto).
                orElseThrow(ObjectNotFoundException::new);

        final Context ctx = new Context();
        setupPdfContext(offerDetailDTO, ctx);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(templateEngine.process("pdf/details", ctx));
        renderer.layout();
        try {
            renderer.createPDF(byteArrayOutputStream);
        } catch (DocumentException e) {
            throw new RuntimeException("Something went wrong.", e);
        }

        return byteArrayOutputStream.toByteArray();
    }


    private void setupPdfContext(OfferDetailDTO offerDetailDTO,
                                 Context ctx) {
        ctx.setVariable("brand", offerDetailDTO.getBrand());
        ctx.setVariable("model", offerDetailDTO.getModel());
        ctx.setVariable("year", offerDetailDTO.getYear());
        ctx.setVariable("price", offerDetailDTO.getPrice());

    }

}
