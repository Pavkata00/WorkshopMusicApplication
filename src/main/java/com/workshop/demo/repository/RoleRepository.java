package com.workshop.demo.repository;

import com.workshop.demo.model.entities.RoleEntity;
import com.workshop.demo.model.entities.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRole(RoleEnum role);
}
