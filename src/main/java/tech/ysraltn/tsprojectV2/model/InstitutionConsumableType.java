package tech.ysraltn.tsprojectV2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "institution_consumable_types", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"institution_id", "consumable_type_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionConsumableType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consumable_type_id")
    private ConsumableType consumableType;
}
