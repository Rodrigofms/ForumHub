package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.usuario.DadosLogin;
import br.com.alura.ForumHub.domain.usuario.TokenResponse;
import br.com.alura.ForumHub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "Autenticação - Não necessita autenticar")
@RequiredArgsConstructor
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Operation(summary = "Logar", description = "Aprova o acesso do usuario e gera um token temporário")
    public ResponseEntity<?> login(@Valid @RequestBody DadosLogin dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        Authentication auth = manager.authenticate(authToken);
        String token = tokenService.gerar(auth);
        return ResponseEntity.ok(new TokenResponse("Bearer",token));
    }
}
