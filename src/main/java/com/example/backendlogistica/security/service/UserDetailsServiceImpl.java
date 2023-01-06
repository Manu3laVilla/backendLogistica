package com.example.backendlogistica.security.service;

import com.example.backendlogistica.security.entities.Usuario;
import com.example.backendlogistica.security.entities.UsuarioPpal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByUserName(username).get();

        return UsuarioPpal.build(usuario);
    }
}
