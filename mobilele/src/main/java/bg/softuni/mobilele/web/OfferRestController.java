package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.OfferDTO;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/offers")
@RestController
public class OfferRestController {

  private final OfferService offerService;

  public OfferRestController(OfferService offerService) {
    this.offerService = offerService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<OfferDTO> getOffer(@PathVariable("id") UUID id) {
    Optional<OfferDTO> offerDTOOpt =
        offerService.getOfferById(id);

    if (offerDTOOpt.isEmpty()) {
      return ResponseEntity.
          notFound().
          build();
    } else {
      return ResponseEntity.
          ok(offerDTOOpt.get());
    }
  }

}
