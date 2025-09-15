package tech.ysraltn.tsprojectV2.repository;

import tech.ysraltn.tsprojectV2.model.Cycle;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CycleRepository extends JpaRepository<Cycle, Long> {

    // İlişkili ürün ve kurum bilgilerini eager yükle
    @EntityGraph(attributePaths = {
            "product",
            "product.institution",
            "product.owner",
            "product.responsible"
    })
    List<Cycle> findByYear(Integer year);

    @EntityGraph(attributePaths = {
            "product",
            "product.institution",
            "product.owner",
            "product.responsible"
    })
    List<Cycle> findAll();
    List<Cycle> findByYearAndProduct_Responsible_Id(Integer year, Long responsibleId);
    @Query("""
        SELECT c FROM Cycle c
        WHERE (:year IS NULL OR c.year = :year)
        AND (:month IS NULL OR c.month = :month)
        AND (:userId IS NULL OR c.product.responsible.id = :userId)
        AND (:institutionId IS NULL OR c.product.institution.id = :institutionId)
        """)
        List<Cycle> findCyclesWithFilters(
                @Param("year") Integer year,
                @Param("month") Integer month,
                @Param("userId") Long userId,
                @Param("institutionId") Long institutionId
        );

}


