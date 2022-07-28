package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.dto.offer.SearchOfferDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class OfferSpecification implements Specification<OfferEntity> {

  private final SearchOfferDTO searchOfferDTO;

  public OfferSpecification(SearchOfferDTO searchOfferDTO) {
    this.searchOfferDTO = searchOfferDTO;
  }

  @Override
  public Predicate toPredicate(Root<OfferEntity> root,
                               CriteriaQuery<?> query,
                               CriteriaBuilder cb) {
    Predicate p = cb.conjunction();

    if (searchOfferDTO.getModel() != null && !searchOfferDTO.getModel().isEmpty()) {
      p.getExpressions().add(
          cb.and(cb.equal(root.join("model").get("name"), searchOfferDTO.getModel()))
      );
    }

    if (searchOfferDTO.getMinPrice() != null) {
      p.getExpressions().add(
          cb.and(cb.greaterThanOrEqualTo(root.get("price"), searchOfferDTO.getMinPrice()))
      );
    }

    if (searchOfferDTO.getMaxPrice() != null) {
      p.getExpressions().add(
          cb.and(cb.lessThanOrEqualTo(root.get("price"), searchOfferDTO.getMaxPrice()))
      );
    }

    return p;
  }
}
