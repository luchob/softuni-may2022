package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.vote.VoteDTO;
import bg.softuni.mobilele.service.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
public class VoteRestController {

  private final VoteService voteService;

  public VoteRestController(VoteService voteService) {
    this.voteService = voteService;
  }

  @GetMapping("/{offer_id}")
  public ResponseEntity<VoteDTO> getVoteByOfferId(@PathVariable("offer_id") UUID offerId) {
    return ResponseEntity.
        ok(voteService.getVotesByOfferId(offerId)) ;
  }

  @PostMapping("/{offer_id}")
  public ResponseEntity<VoteDTO> voteForOffer(@PathVariable("offer_id") UUID offerId,
                                              @RequestBody VoteDTO voteDTO) {
    return ResponseEntity.
        ok(voteDTO) ;
  }

}
