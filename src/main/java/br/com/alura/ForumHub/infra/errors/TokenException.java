package br.com.alura.ForumHub.infra.errors;

public class TokenException extends RuntimeException {
    public TokenException(String mensagem) {
        super(mensagem);
    }
}

