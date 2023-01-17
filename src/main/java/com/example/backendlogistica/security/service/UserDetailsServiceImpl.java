package com.example.backendlogistica.security.service;

import com.example.backendlogistica.security.entities.Usuario;
import com.example.backendlogistica.security.entities.UsuarioPpal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOptional = usuarioService.getByUserName(username);

        if(!usuarioOptional.isEmpty()){

            Usuario usuario = usuarioOptional.get();
            return UsuarioPpal.build(usuario);
        }

        return null;
    }
}
