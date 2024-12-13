package com.three.gyangriha.repo;

import com.three.gyangriha.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMobileNo(String mobileNo);
    List<User> findByNameContaining(String name);
    Optional<User> findByIdProofNo(String idProofNo);

    boolean existsByEmail(String email);
}
