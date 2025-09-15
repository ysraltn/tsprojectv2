package tech.ysraltn.tsprojectV2.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import tech.ysraltn.tsprojectV2.model.Institution;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class InstitutionSpecifications {

    public static Specification<Institution> withFilters(String city, String name) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (city != null && !city.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
            }

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

