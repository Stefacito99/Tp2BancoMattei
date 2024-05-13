package src.ar.edu.utn.frbb.tup;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class GestorDeSaldo {
    public static Scanner scanner = new Scanner(System.in);

    public static void depositar(Banco banco) {
        // Solicitar identificador de la cuenta
        System.out.print("Ingrese el identificador de la cuenta bancaria: ");
        int identificador = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Buscar la cuenta bancaria en el banco
        CuentasBancarias cuenta = banco.buscarCuentaPorIdentificador(identificador);
        if (cuenta == null) {
            System.out.println("No se encontró ninguna cuenta con el identificador especificado.");
            return;
        }

        // Solicitar el monto a depositar
        System.out.print("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        // Realizar el depósito
        cuenta.depositar(monto);

        // Crear un objeto Movimiento
        LocalDateTime fecha = LocalDateTime.now();
        Movimiento movimiento = new Movimiento(fecha, "Depósito", monto);

        // Agregar el movimiento al historial de la cuenta bancaria
        cuenta.agregarMovimiento(movimiento);

        System.out.println("Depósito realizado correctamente. Nuevo saldo: " + cuenta.getSaldo());
    }

    public static void retirar(Banco banco) {
        // Solicitar identificador de la cuenta
        System.out.print("Ingrese el identificador de la cuenta bancaria: ");
        int identificador = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Buscar la cuenta bancaria en el banco
        CuentasBancarias cuenta = banco.buscarCuentaPorIdentificador(identificador);
        if (cuenta == null) {
            System.out.println("No se encontró ninguna cuenta con el identificador especificado.");
            return;
        }

        // Solicitar el monto a retirar
        System.out.print("Ingrese el monto a retirar: ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        // Verificar que haya suficiente saldo en la cuenta
        if (monto > cuenta.getSaldo()) {
            System.out.println("Saldo insuficiente para realizar el retiro.");
            return;
        }

        // Realizar el retiro
        cuenta.retirar(monto);

        // Crear un objeto Movimiento
        LocalDateTime fecha = LocalDateTime.now();
        Movimiento movimiento = new Movimiento(fecha, "Retiro", monto);

        // Agregar el movimiento al historial de la cuenta bancaria
        cuenta.agregarMovimiento(movimiento);

        System.out.println("Retiro realizado correctamente. Nuevo saldo: " + cuenta.getSaldo());
    }

    public static void transferir(Banco banco) {
        // Solicitar identificadores de las cuentas origen y destino
        System.out.print("Ingrese el identificador de la cuenta de origen: ");
        int idOrigen = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el identificador de la cuenta de destino: ");
        int idDestino = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Buscar las cuentas bancarias en el banco
        CuentasBancarias cuentaOrigen = banco.buscarCuentaPorIdentificador(idOrigen);
        CuentasBancarias cuentaDestino = banco.buscarCuentaPorIdentificador(idDestino);
        if (cuentaOrigen == null || cuentaDestino == null) {
            System.out.println("No se encontró alguna de las cuentas especificadas.");
            return;
        }

        // Solicitar el monto a transferir
        System.out.print("Ingrese el monto a transferir: ");
        double monto = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        // Verificar que haya suficiente saldo en la cuenta origen
        if (monto > cuentaOrigen.getSaldo()) {
            System.out.println("Saldo insuficiente en la cuenta de origen para realizar la transferencia.");
            return;
        }

        // Realizar la transferencia
        cuentaOrigen.retirar(monto);
        cuentaDestino.depositar(monto);

        // Crear objetos Movimiento para ambas cuentas
        LocalDateTime fecha = LocalDateTime.now();
        Movimiento movimientoOrigen = new Movimiento(fecha, "Transferencia enviada a cuenta #" + idDestino, -monto);
        Movimiento movimientoDestino = new Movimiento(fecha, "Transferencia recibida de cuenta #" + idOrigen, monto);

        // Agregar los movimientos al historial de las cuentas bancarias
        cuentaOrigen.agregarMovimiento(movimientoOrigen);
        cuentaDestino.agregarMovimiento(movimientoDestino);

        System.out.println("Transferencia de $" + monto + " realizada correctamente de la cuenta #" + idOrigen + " a la cuenta #" + idDestino);
    }

    public static void verSaldo(Banco banco) {
        // Solicitar identificador de la cuenta
        System.out.print("Ingrese el identificador de la cuenta bancaria: ");
        int identificador = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Buscar la cuenta bancaria en el banco
        CuentasBancarias cuenta = banco.buscarCuentaPorIdentificador(identificador);
        if (cuenta == null) {
            System.out.println("No se encontró ninguna cuenta con el identificador especificado.");
            return;
        }

        // Mostrar el saldo de la cuenta
        System.out.println("El saldo de la cuenta #" + identificador + " es: $" + cuenta.getSaldo());
    }

    public static void verHistorial(Banco banco) {
        // Solicitar identificador de la cuenta
        System.out.print("Ingrese el identificador de la cuenta bancaria: ");
        int identificador = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Buscar la cuenta bancaria en el banco
        CuentasBancarias cuenta = banco.buscarCuentaPorIdentificador(identificador);
        if (cuenta == null) {
            System.out.println("No se encontró ninguna cuenta con el identificador especificado.");
            return;
        }

        // Obtener el historial de movimientos de la cuenta
        List<Movimiento> historial = cuenta.getHistorialMovimientos();

        // Mostrar el historial de movimientos
        if (historial.isEmpty()) {
            System.out.println("El historial de movimientos de la cuenta #" + identificador + " está vacío.");
        } else {
            System.out.println("Historial de movimientos de la cuenta #" + identificador + ":");
            for (Movimiento movimiento : historial) {
                System.out.println(movimiento);
            }
        }
    }
}
