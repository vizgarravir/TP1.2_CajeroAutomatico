package com.banco.atm.ui;

import com.banco.atm.service.CajeroService;
import com.banco.atm.exception.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuConsola {

    private CajeroService cajero;
    private Scanner scanner;

    public MenuConsola(CajeroService cajero) {
        this.cajero = cajero;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = 0;

        do {
            mostrarMenu();

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1 -> depositar();
                    case 2 -> extraer();
                    case 3 -> transferir();
                    case 4 -> consultarSaldo();
                    case 5 -> mostrarHistorial();
                    case 6 -> System.out.println("Saliendo del cajero...");
                    default -> System.out.println("Opcion invalida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un numero.");
                scanner.nextLine();
            }

        } while (opcion != 6);
    }

    private void mostrarMenu() {
        System.out.println("\n===== BANCO ATM =====");
        System.out.println("1. Depositar");
        System.out.println("2. Extraer");
        System.out.println("3. Transferir");
        System.out.println("4. Consultar saldo");
        System.out.println("5. Ver historial");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private void depositar() {
        try {
            System.out.print("Numero de cuenta: ");
            String numeroCuenta = scanner.nextLine();

            System.out.print("Monto a depositar: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();

            cajero.depositar(numeroCuenta, monto);
            System.out.println("Deposito realizado correctamente.");

        } catch (InputMismatchException e) {
            System.out.println("Error: monto invalido.");
            scanner.nextLine();
        } catch (CuentaInactivaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void extraer() {
        try {
            System.out.print("Numero de cuenta: ");
            String numeroCuenta = scanner.nextLine();

            System.out.print("Monto a extraer: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();

            cajero.extraer(numeroCuenta, monto);
            System.out.println("Extraccion realizada correctamente.");

        } catch (InputMismatchException e) {
            System.out.println("Error: monto invalido.");
            scanner.nextLine();
        } catch (SaldoInsuficienteException |
                 LimiteExtraccionExcedidoException |
                 CuentaInactivaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void transferir() {
        try {
            System.out.print("Cuenta origen: ");
            String origen = scanner.nextLine();

            System.out.print("Cuenta destino: ");
            String destino = scanner.nextLine();

            System.out.print("Monto a transferir: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();

            cajero.transferir(origen, destino, monto);

        } catch (InputMismatchException e) {
            System.out.println("Error: monto invalido.");
            scanner.nextLine();
        } catch (SaldoInsuficienteException |
                 LimiteExtraccionExcedidoException |
                 CuentaInactivaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void consultarSaldo() {
        System.out.print("Numero de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        cajero.consultarSaldo(numeroCuenta);
    }

    private void mostrarHistorial() {
        System.out.print("Numero de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        cajero.mostrarHistorial(numeroCuenta);
    }
}