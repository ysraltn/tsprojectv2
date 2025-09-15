package tech.ysraltn.tsprojectV2.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "institutions",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "city"})
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InstitutionConsumableType> consumableTypes;

}
