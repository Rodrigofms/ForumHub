package br.com.alura.ForumHub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha
) {
}
