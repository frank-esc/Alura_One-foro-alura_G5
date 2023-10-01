package com.alura.foro.api.controllers;

import com.alura.foro.api.domain.usuario.DatosAutenticacionUsuario;
import com.alura.foro.api.domain.usuario.Usuario;
import com.alura.foro.api.infra.security.DatosJWTToken;
import com.alura.foro.api.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticacion", description = "obtiene token para el usuario asignado")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario autenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(autenticacionUsuario.nombreUsuario(),
                autenticacionUsuario.contrasenha());

        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
