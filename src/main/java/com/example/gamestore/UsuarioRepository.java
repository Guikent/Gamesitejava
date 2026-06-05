package com.example.gamestore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Alterado de findByUsername para findByLogin para manter a consistência
    Optional<Usuario> findByLogin(String login);
}