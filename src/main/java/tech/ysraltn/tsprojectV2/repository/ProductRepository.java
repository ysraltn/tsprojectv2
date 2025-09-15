package tech.ysraltn.tsprojectV2.repository;

import tech.ysraltn.tsprojectV2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySerial(String serial);
    List<Product> findByInstitution_Id(Long institutionId);
    long countByModel(String model);
    long countByModelContaining(String keyword);
}
