package edu.example.loginapp.autentication;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.example.loginapp.autentication.entities.AuthUser;

public interface IUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);
}
