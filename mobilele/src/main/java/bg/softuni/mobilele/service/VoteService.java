package bg.softuni.mobilele.service;

import bg.softuni.mobilele.exception.ObjectNotFoundException;
import bg.softuni.mobilele.model.dto.vote.VoteDTO;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VoteService {

  private final OfferRepository offerRepository;
  private final VoteRepository voteRepository;

  public VoteService(OfferRepository offerRepository,
                     VoteRepository voteRepository) {
    this.offerRepository = offerRepository;
    this.voteRepository = voteRepository;
  }

  public VoteDTO getVotesByOfferId(UUID offerId) {
    if (!offerRepository.existsById(offerId)) {
      throw new ObjectNotFoundException("Offer with UUID " + offerId + " was not found!");
    }

    var voteOpt = voteRepository.findVoteEntityByOffer_Id(offerId).orElse(null);

    return new VoteDTO().
        setNegative(4).
        setPositive(9).
        setCanVote(true);

  }
}
