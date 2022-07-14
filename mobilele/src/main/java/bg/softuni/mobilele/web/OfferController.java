package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.offer.AddOfferDTO;
import bg.softuni.mobilele.model.dto.offer.SearchOfferDTO;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
        @PageableDefault(
            page = 0,
            size = 5) Pageable pageable) {

        model.addAttribute("offers", offerService.getAllOffers(pageable));

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
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferModel", addOfferModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferModel",
                    bindingResult);
            return "redirect:/offers/add";
        }

        offerService.addOffer(addOfferModel, userDetails);

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/search")
    public String searchOffer() {
        return "offer-search";
    }

    @PostMapping("/offers/search")
    public String searchQuery(@Valid SearchOfferDTO searchOfferDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("searchOfferModel", searchOfferDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.searchOfferModel",
                    bindingResult);
            return "redirect:/offers/search";
        }
        //TODO: Лъчо, моля покажи по-културен вариант от това, тъй като работи, но това изписване меко казано
        // ми бърка някъде. В data.sql съм добавил оферта, за да се пробва search-a
        return String.format("redirect:/offers/search/%s", searchOfferDTO.getQuery());
    }

    @GetMapping("offers/search/{query}")
    public String searchResults(@PathVariable String query, Model model) {
        model.addAttribute("offers", this.offerService.findOfferByOfferName(query));
        return "offer-search";
    }

    //TODO: Имаш бонус работещо DTO - CardListingDTO със настроен mapper за offer catalogue

    @ModelAttribute(name = "searchOfferModel")
    private SearchOfferDTO initSearchModel() {
        return new SearchOfferDTO();
    }

}
