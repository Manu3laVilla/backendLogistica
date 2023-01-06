package com.example.backendlogistica.security.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPpal implements UserDetails {

    private String nombreUsuario;

    private String userName;

    private String correoUsuario;

    private String passUsuario;

    public UsuarioPpal(){}
    public UsuarioPpal(String nombreUsuario, String userName, String correoUsuario, String passUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.nombreUsuario = nombreUsuario;
        this.userName = userName;
        this.correoUsuario = correoUsuario;
        this.passUsuario = passUsuario;
        this.authorities = authorities;
    }

    public static UsuarioPpal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getIdRolUsuario().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getTipoRol().name())).collect(Collectors.toList());

        return new UsuarioPpal(usuario.getNombreUsuario(), usuario.getUserName(), usuario.getCorreoUsuario(),
                usuario.getPassUsuario(), authorities);
    }

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passUsuario;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
