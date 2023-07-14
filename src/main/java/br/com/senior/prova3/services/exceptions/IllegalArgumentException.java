package br.com.senior.prova3.services.exceptions;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class IllegalArgumentException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public IllegalArgumentException(String message) {
        super(message);
    }
    public IllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
