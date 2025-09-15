package tech.ysraltn.tsprojectV2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "institution_consumables", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"institution_id", "consumable_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionConsumable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumable_id")
    private Consumable consumable;
}
