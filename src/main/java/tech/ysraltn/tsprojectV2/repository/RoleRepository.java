package tech.ysraltn.tsprojectV2.repository;

import tech.ysraltn.tsprojectV2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
