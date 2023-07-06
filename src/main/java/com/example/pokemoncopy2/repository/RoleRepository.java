package com.example.pokemoncopy2.repository;

import com.example.pokemoncopy2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(String user);
}
