package com.example.backendlogistica.security.service;

import com.example.backendlogistica.security.entities.Usuario;
import com.example.backendlogistica.security.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByUserName(String userName){
        return usuarioRepository.findByUserName(userName);
    }

    public boolean existsByUserName(String userName){
        return  usuarioRepository.existsByUserName(userName);
    }

    public boolean existsByCorreoUsuario(String correoUsuario){
        return  usuarioRepository.existsByCorreoUsuario(correoUsuario);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

}
