package tech.ysraltn.tsprojectV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ysraltn.tsprojectV2.model.ConsumableType;

import java.util.List;

public interface ConsumableTypeRepository extends JpaRepository<ConsumableType, Long> {
    List<ConsumableType> findByConsumableId(Long consumableId);
}
