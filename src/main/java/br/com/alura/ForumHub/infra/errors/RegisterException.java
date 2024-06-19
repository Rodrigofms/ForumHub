package br.com.alura.ForumHub.infra.errors;

public class RegisterException extends RuntimeException {
    public RegisterException(String message) {
        super(message);
    }
}