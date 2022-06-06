package bg.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferController {

  @GetMapping("/offers/all")
  public String allOffers() {
    return "offers";
  }

  @GetMapping("/offers/add")
  public String addOffer() {
    return "offer-add";
  }


}
