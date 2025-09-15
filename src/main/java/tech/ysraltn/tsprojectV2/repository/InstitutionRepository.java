package tech.ysraltn.tsprojectV2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ysraltn.tsprojectV2.model.Institution;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long>, JpaSpecificationExecutor<Institution> {

    @EntityGraph(attributePaths = {
        "consumableTypes",
        "consumableTypes.consumableType",
        "consumableTypes.consumableType.consumable"
    })
    Page<Institution> findAll(Specification<Institution> spec, Pageable pageable);

    boolean existsByName(String name);
}

