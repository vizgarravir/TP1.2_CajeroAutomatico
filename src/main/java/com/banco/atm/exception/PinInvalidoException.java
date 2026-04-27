package com.banco.atm.exception;

public class PinInvalidoException extends Exception {

    public PinInvalidoException(String mensaje) {
        super(mensaje);
    }
}