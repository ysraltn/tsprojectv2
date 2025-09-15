package tech.ysraltn.tsprojectV2.repository;

import tech.ysraltn.tsprojectV2.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);

} 
