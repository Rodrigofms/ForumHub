package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.usuario.DadosRegistroUsuario;
import br.com.alura.ForumHub.domain.usuario.DadosUsuario;
import br.com.alura.ForumHub.domain.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários - Não necessita autenticar")
@RequiredArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Operation(summary = "Registar", description = "Cadastro de novo usuário")
    public ResponseEntity<DadosUsuario> registrar(@Valid @RequestBody DadosRegistroUsuario dados, UriComponentsBuilder builder) {
        DadosUsuario dadosUsuario = service.registrar(dados);
        URI uri = builder.path("/usuarios/{id}").buildAndExpand(dadosUsuario.id()).toUri();
        return ResponseEntity.created(uri).body(dadosUsuario);
    }
}