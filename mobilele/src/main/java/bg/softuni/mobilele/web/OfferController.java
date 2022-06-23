package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.AddOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDTO;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class OfferController {

  private final OfferService offerService;
  private final BrandService brandService;

  public OfferController(OfferService offerService,
                         BrandService brandService) {
    this.offerService = offerService;
    this.brandService = brandService;
  }

  @GetMapping("/offers/all")
  public String allOffers(
      Model model,
      Pageable pageable) {

    var offersPage =
        offerService.getPage(pageable);

    List<List<OfferDTO>> offerRows = new LinkedList<>();
    List<OfferDTO> currentRow = new LinkedList<>();
    for (OfferDTO currentOffer : offersPage.getContent()) {
      currentRow.add(currentOffer);
      if (currentRow.size() == 3) {
        offerRows.add(currentRow);
        currentRow = new ArrayList<>(3);
      }
    }

    if (!currentRow.isEmpty()) {
      offerRows.add(currentRow);
    }


    model.addAttribute("offerRows", offerRows);

    return "offers";
  }

  @GetMapping("/offers/add")
  public String addOffer(Model model) {
    if (!model.containsAttribute("addOfferModel")) {
      model.addAttribute("addOfferModel", new AddOfferDTO());
    }
    model.addAttribute("brands", brandService.getAllBrands());

    return "offer-add";
  }

  @PostMapping("/offers/add")
  public String addOffer(@Valid AddOfferDTO addOfferModel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
      redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel",
          bindingResult);
      return "redirect:/offers/add";
    }

    offerService.addOffer(addOfferModel);

    return "redirect:/offers/all";
  }

  @GetMapping("/offers/details/{id}")
  public String offerDetails(@PathVariable("id") String id,
                             Model model) {
    model.addAttribute("id", id);

    return "details";
  }

}
