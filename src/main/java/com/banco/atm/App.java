package com.banco.atm;

import com.banco.atm.objects.CuentaBancaria;
import com.banco.atm.service.CajeroService;
import com.banco.atm.ui.MenuConsola;
import com.banco.atm.exception.*;

public class App {

    public static void main(String[] args) {

        CajeroService cajero = new CajeroService();

        // =========================
        // 🏦 Crear cuentas
        // =========================
        CuentaBancaria c1 = new CuentaBancaria("001", "Martina", 50000);
        CuentaBancaria c2 = new CuentaBancaria("002", "Lala", 30000);

        cajero.agregarCuenta(c1);
        cajero.agregarCuenta(c2);

        System.out.println("===== SIMULACION ATM =====");

        // =========================
        // ✅ Operaciones válidas
        // =========================
        try {
            cajero.depositar("001", 5000);
            System.out.println("Deposito realizado correctamente.");

            cajero.extraer("001", 2000);
            System.out.println("Extraccion realizada correctamente.");

            cajero.transferir("001", "002", 3000);

            cajero.consultarSaldo("001");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // =========================
        // ❌ Excepción 1: saldo insuficiente
        // =========================
        try {
            cajero.extraer("002", 100000);
        } catch (SaldoInsuficienteException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // =========================
        // ❌ Excepción 2: límite excedido
        // =========================
        try {
            cajero.extraer("001", 20000);
        } catch (LimiteExtraccionExcedidoException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // =========================
        // 📜 Historial
        // =========================
        cajero.mostrarHistorial("001");

        // =========================
        // 🖥️ Menú interactivo
        // =========================
        MenuConsola menu = new MenuConsola(cajero);
        menu.iniciar();
    }
}