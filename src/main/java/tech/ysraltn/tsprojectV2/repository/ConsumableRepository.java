package tech.ysraltn.tsprojectV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ysraltn.tsprojectV2.model.Consumable;

public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
}
