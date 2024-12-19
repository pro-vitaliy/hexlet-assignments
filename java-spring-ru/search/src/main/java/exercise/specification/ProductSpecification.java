package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO productParams) {
        return withTitleCont(productParams.getTitleCont())
                .and(withCategoryId(productParams.getCategoryId()))
                .and(withPriceGt(productParams.getPriceGt()))
                .and(withPriceLt(productParams.getPriceLt()))
                .and(withRatingGt(productParams.getRatingGt()));
    }

    private Specification<Product> withTitleCont(String title) {
        return (root, query, cb) -> {
            return title == null ? cb.conjunction() : cb.like(cb.lower(root.get("title")),
                    "%" + title.toLowerCase() + "%");
        };
    }

    private Specification<Product> withCategoryId(Long id) {
        return (root, query, cb) -> {
            return id == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), id);
        };
    }

    private Specification<Product> withPriceGt(Integer price) {
        return (root, query, cb) -> {
            return price == null ? cb.conjunction() : cb.greaterThan(root.get("price"), price);
        };
    }

    private Specification<Product> withPriceLt(Integer price) {
        return (root, query, cb) -> {
            return price == null ? cb.conjunction() : cb.lessThan(root.get("price"), price);
        };
    }

    private Specification<Product> withRatingGt(Double rating) {
        return (root, query, cb) -> {
            return rating == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), rating);
        };
    }
}
// END
