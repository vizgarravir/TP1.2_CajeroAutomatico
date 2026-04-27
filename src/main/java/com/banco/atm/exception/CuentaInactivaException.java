package com.banco.atm.exception;

public class CuentaInactivaException extends Exception {

    public CuentaInactivaException(String mensaje) {
        super(mensaje);
    }
}