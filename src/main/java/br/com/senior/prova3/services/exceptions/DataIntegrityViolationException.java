package br.com.senior.prova3.services.exceptions;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class DataIntegrityViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }
    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
