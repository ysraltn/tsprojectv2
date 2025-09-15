package tech.ysraltn.tsprojectV2.repository;

import tech.ysraltn.tsprojectV2.dto.CycleDto;
import tech.ysraltn.tsprojectV2.model.Cycle;
import tech.ysraltn.tsprojectV2.model.UserProductAssignment;

import org.apache.poi.sl.draw.geom.GuideIf.Op;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserProductAssignmentRepository extends JpaRepository<UserProductAssignment, Long> {
    Optional<UserProductAssignment> findByUserIdAndProductId(Long userId, Long productId);
    @EntityGraph(attributePaths = {
        "product",
        "product.serial"
    })
    List<UserProductAssignment> findAllByProductId(Long productId);
    @EntityGraph(attributePaths = {
        "product",
        "product.serial"
    })
    List<UserProductAssignment> findAllByUserId(Long userId);

    @Query("""
        SELECT new tech.ysraltn.tsprojectV2.dto.CycleDto(
            c.id, c.year, c.month, c.cycleCount,
            p.id, p.serial, p.type, p.brand, p.model, p.status,
            i.id, i.name, i.city,
            o.id, o.name,
            r.id, r.name
        )
        FROM UserProductAssignment upa
        JOIN upa.product p
        JOIN Cycle c ON c.product.id = p.id
        JOIN p.institution i
        JOIN p.owner o
        LEFT JOIN p.responsible r
        WHERE upa.user.id = :userId
    """)
    List<CycleDto> findCycleDtosByUserId(@Param("userId") Long userId);

}
