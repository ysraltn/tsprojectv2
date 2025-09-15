package tech.ysraltn.tsprojectV2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cycles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "year", "month"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer cycleCount;
}
