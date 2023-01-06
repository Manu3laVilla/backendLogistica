package com.example.backendlogistica.security.controller;

import com.example.backendlogistica.security.dto.JwtDTO;
import com.example.backendlogistica.security.dto.LoginUsuarioDTO;
import com.example.backendlogistica.security.dto.NuevoUsuarioDTO;
import com.example.backendlogistica.security.entities.Rol;
import com.example.backendlogistica.security.entities.Usuario;
import com.example.backendlogistica.security.enums.RolNombre;
import com.example.backendlogistica.security.jwt.JwtProvider;
import com.example.backendlogistica.security.service.RolService;
import com.example.backendlogistica.security.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/logistica/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuarioDTO nuevoUsuarioDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos Incorrectos o Inválidos",HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByUserName(nuevoUsuarioDTO.getUserName()))
            return new ResponseEntity("El Nombre De Usuario Ya Existe", HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByCorreoUsuario(nuevoUsuarioDTO.getCorreoUsuario()))
            return new ResponseEntity("El Correo Ya Existe", HttpStatus.BAD_REQUEST);

        Usuario usuario =
                new Usuario(nuevoUsuarioDTO.getNombreUsuario(), nuevoUsuarioDTO.getUserName(),
                        nuevoUsuarioDTO.getCorreoUsuario(), passwordEncoder.encode(nuevoUsuarioDTO.getPassUsuario()));

        Set<Rol> roles = new HashSet<>();

        roles.add(rolService.getByTipoRol(RolNombre.ROLE_USER).get());
        if(nuevoUsuarioDTO.getIdRolUsuario().contains("admin"))
            roles.add(rolService.getByTipoRol(RolNombre.ROLE_ADMIN).get());

        usuario.setIdRolUsuario(roles);
        usuarioService.save(usuario);
        return new ResponseEntity("Usuario Guardado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuarioDTO loginUsuarioDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos Incorrectos o Inválidos",HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioDTO.getUserName(),
                        loginUsuarioDTO.getPassUsuario()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDTO, HttpStatus.OK);
    }

}