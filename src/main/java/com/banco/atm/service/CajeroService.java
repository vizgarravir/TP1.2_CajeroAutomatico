package com.banco.atm.service;

import com.banco.atm.objects.*;
import com.banco.atm.exception.*;

import java.util.ArrayList;
import java.util.List;

public class CajeroService {

    private List<CuentaBancaria> cuentas = new ArrayList<>();

    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public CuentaBancaria buscarCuenta(String numeroCuenta) {
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public void depositar(String numeroCuenta, double monto)
            throws CuentaInactivaException {

        CuentaBancaria cuenta = buscarCuenta(numeroCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        if (!cuenta.isActiva()) {
            throw new CuentaInactivaException("La cuenta esta inactiva");
        }

        if (monto <= 0) {
            System.out.println("El monto debe ser positivo");
            return;
        }

        cuenta.depositar(monto);
    }

    public void extraer(String numeroCuenta, double monto)
            throws SaldoInsuficienteException,
                   LimiteExtraccionExcedidoException,
                   CuentaInactivaException {

        CuentaBancaria cuenta = buscarCuenta(numeroCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        if (!cuenta.isActiva()) {
            throw new CuentaInactivaException("La cuenta esta inactiva");
        }

        if (monto <= 0) {
            System.out.println("El monto debe ser positivo");
            return;
        }

        if (monto > 10000) {
            throw new LimiteExtraccionExcedidoException("No se puede extraer mas de $10000 por operacion");
        }

        if (monto > cuenta.getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        cuenta.retirar(monto);
    }

    public void transferir(String cuentaOrigen, String cuentaDestino, double monto)
            throws SaldoInsuficienteException,
                   LimiteExtraccionExcedidoException,
                   CuentaInactivaException {

        CuentaBancaria origen = buscarCuenta(cuentaOrigen);
        CuentaBancaria destino = buscarCuenta(cuentaDestino);

        if (origen == null || destino == null) {
            System.out.println("Una de las cuentas no existe");
            return;
        }

        if (!origen.isActiva() || !destino.isActiva()) {
            throw new CuentaInactivaException("Una de las cuentas esta inactiva");
        }

        if (monto <= 0) {
            System.out.println("El monto debe ser positivo");
            return;
        }

        if (monto > 10000) {
            throw new LimiteExtraccionExcedidoException("No se puede transferir mas de $10000 por operacion");
        }

        if (monto > origen.getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para transferir");
        }

        origen.retirar(monto);
        destino.depositar(monto);

        System.out.println("Transferencia realizada correctamente");
    }

    public void consultarSaldo(String numeroCuenta) {
        CuentaBancaria cuenta = buscarCuenta(numeroCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        cuenta.depositar(0); // registra consulta si tu modelo no tiene método propio
        System.out.println("Saldo actual: $" + cuenta.getSaldo());
    }

    public void mostrarHistorial(String numeroCuenta) {
        CuentaBancaria cuenta = buscarCuenta(numeroCuenta);

        if (cuenta == null) {
            System.out.println("Cuenta no encontrada");
            return;
        }

        cuenta.mostrarHistorial();
    }
}