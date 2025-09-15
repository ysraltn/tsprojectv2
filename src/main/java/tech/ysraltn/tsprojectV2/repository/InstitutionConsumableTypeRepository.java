package tech.ysraltn.tsprojectV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ysraltn.tsprojectV2.model.InstitutionConsumableType;

import java.util.List;

public interface InstitutionConsumableTypeRepository extends JpaRepository<InstitutionConsumableType, Long> {
    List<InstitutionConsumableType> findByInstitutionId(Long institutionId);
    List<InstitutionConsumableType> findByConsumableTypeId(Long consumableTypeId);
}
