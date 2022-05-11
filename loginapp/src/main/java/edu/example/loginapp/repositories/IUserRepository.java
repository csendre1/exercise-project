package edu.example.loginapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.example.loginapp.model.AuthUser;

public interface IUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);
}
