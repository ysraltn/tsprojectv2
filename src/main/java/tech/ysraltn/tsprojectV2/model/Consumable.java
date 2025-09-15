package tech.ysraltn.tsprojectV2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "consumables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "consumable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsumableType> types;
}
