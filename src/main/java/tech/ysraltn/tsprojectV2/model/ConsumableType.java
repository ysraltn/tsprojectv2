package tech.ysraltn.tsprojectV2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consumable_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsumableType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String typeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumable_id")
    private Consumable consumable;
}
