package com.banco.atm.objects;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class CuentaBancaria {

    private final String numeroCuenta; 
    private double saldo;
    private String titular;
    private boolean activa;
    private ArrayList<String> historialTransacciones;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.activa = true;
        this.historialTransacciones = new ArrayList<>();
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void desactivar() {
        this.activa = false;
    }


    public void depositar(double monto) {
        saldo += monto;
        registrar("DEPOSITO", monto);
    }


    public void retirar(double monto) {
        saldo -= monto;
        registrar("EXTRACCION", monto);
    }


    private void registrar(String tipo, double monto) {
        String registro = "[" + LocalDateTime.now() + "] " +
                tipo + ": $" + monto +
                " | Saldo: $" + saldo;

        historialTransacciones.add(registro);

        if (historialTransacciones.size() > 10) {
            historialTransacciones.remove(0);
        }
    }

    public void mostrarHistorial() {
        System.out.println("---- HISTORIAL ----");
        for (String t : historialTransacciones) {
            System.out.println(t);
        }
    }
}