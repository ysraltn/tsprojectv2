package tech.ysraltn.tsprojectV2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_product_assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProductAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;
}
