package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.offer.AddOfferDTO;
import bg.softuni.mobilele.model.dto.offer.SearchOfferDTO;
import bg.softuni.mobilele.model.user.MobileleUserDetails;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

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
            sort = "price",
            direction = Sort.Direction.ASC,
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
                           @AuthenticationPrincipal MobileleUserDetails userDetails) {

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

    @ModelAttribute(name = "searchOfferModel")
    private SearchOfferDTO initSearchModel() {
        return new SearchOfferDTO();
    }

    @GetMapping("/offers/{id}/details")
    public String getOfferDetail(@PathVariable("id") UUID id) {
        return "details";
    }

    @GetMapping(
        value = "/offers/{id}/download",
        produces = MediaType.APPLICATION_PDF_VALUE
    )
    public void downloadOfferDetails(@PathVariable("id") UUID id,
                                                     HttpServletResponse response) throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"offer" + id + ".pdf\"");
        response.getOutputStream().write(offerService.
            generateOfferPDF(id));
        response.getOutputStream().flush();
    }

}
