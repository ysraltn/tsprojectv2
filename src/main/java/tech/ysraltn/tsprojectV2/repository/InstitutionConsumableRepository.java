package tech.ysraltn.tsprojectV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import tech.ysraltn.tsprojectV2.model.InstitutionConsumable;

public interface InstitutionConsumableRepository extends JpaRepository<InstitutionConsumable, Long> {
    @EntityGraph(attributePaths = {"institution", "consumable"})
    java.util.List<InstitutionConsumable> findByInstitution_IdIn(java.util.List<Long> institutionIds);
    
    @EntityGraph(attributePaths = {"institution", "consumable"})
    java.util.List<InstitutionConsumable> findByInstitution_Id(Long institutionId);
}
