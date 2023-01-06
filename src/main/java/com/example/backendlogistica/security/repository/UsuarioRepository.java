package com.example.backendlogistica.security.repository;

import com.example.backendlogistica.security.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByCorreoUsuario(String correo);

}
