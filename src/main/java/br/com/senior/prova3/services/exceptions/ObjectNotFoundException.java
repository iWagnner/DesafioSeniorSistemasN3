package br.com.senior.prova3.services.exceptions;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
