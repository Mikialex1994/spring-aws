package com.example.pokemoncopy2.repository;

import com.example.pokemoncopy2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {


    Optional<UserEntity> findByusername(String username);

    boolean existsByUsername(String username);
}
