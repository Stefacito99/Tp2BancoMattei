package src.ar.edu.utn.frbb.tup;

import java.util.Scanner;
import java.time.LocalDateTime;

public class GestorDeCuentas {
    public static Scanner scanner = new Scanner(System.in);

    public static void CrearCuenta(Banco banco) {
        CuentasBancarias nuevacuenta = new CuentasBancarias();
        Clientes nuevocliente = new Clientes();

        // Crear Cliente
        System.out.println("Ingresar la informacion siguiente:");
        System.out.println("DNI:");
        nuevocliente.setDni(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Nombre:");
        nuevocliente.setNombre(scanner.nextLine());
        System.out.println("Apellido:");
        nuevocliente.setApellido(scanner.nextLine());
        System.out.println("Direccion:");
        nuevocliente.setDireccion(scanner.nextLine());
        System.out.println("Telefono:");
        nuevocliente.setTelefono(scanner.nextDouble());
        scanner.nextLine(); // Consumir el salto de línea

        banco.agregarCliente(nuevocliente);

        // Crear Cuenta
        System.out.println("Ingresar datos de Cuenta:");
        System.out.println("Identificador:");
        nuevacuenta.setIdentificador(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Tipo de Cuenta (CORRIENTE ó AHORRO):");
        boolean chequeo = true;
        String respuesta;
        while (chequeo) {
            respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("CORRIENTE")) {
                nuevacuenta.setTipodecuenta(TipoDeCuenta.CORRIENTE);
                chequeo = false;
            } else if (respuesta.equalsIgnoreCase("AHORRO")) {
                nuevacuenta.setTipodecuenta(TipoDeCuenta.AHORRO);
                chequeo = false;
            } else {
                System.out.println("Respuesta incorrecta, intente escribirlo como se muestra en la petición!");
            }
        }
        System.out.println("Saldo:");
        nuevacuenta.setSaldo(scanner.nextDouble());
        scanner.nextLine(); // Consumir el salto de línea
        LocalDateTime fechaActual = LocalDateTime.now();
        nuevacuenta.setFechadeingreso(fechaActual);
        nuevacuenta.setCliente(nuevocliente);

        banco.agregarCuenta(nuevacuenta);
    }

    public static void ModificarCuenta(Banco banco) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Ingrese el DNI del cliente que desea modificar (o 'cancelar' para salir): ");
            String dni = scanner.nextLine();

            if (dni.equalsIgnoreCase("cancelar")) {
                System.out.println("Operación cancelada."); // Si quiere cancelar
                return;
            }

            Clientes clienteAModificar = null;
            for (Clientes cliente : banco.getClientes()) {
                if (String.valueOf(cliente.getDni()).equals(String.valueOf(dni))) { // Convierte int a String y compara Strings
                    clienteAModificar = cliente;
                    break;
                }
            }

            if (clienteAModificar == null) {
                System.out.println("No se encontró ningún cliente con el DNI especificado.");
                continue; // Vuelve al inicio del bucle para pedir otro DNI
            }

            System.out.println("Seleccione el campo que desea modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Dirección");
            System.out.println("4. Teléfono");
            System.out.println("5. Cancelar");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    clienteAModificar.setNombre(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo apellido: ");
                    clienteAModificar.setApellido(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Ingrese la nueva dirección: ");
                    clienteAModificar.setDireccion(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo teléfono: ");
                    clienteAModificar.setTelefono(scanner.nextInt());
                    break;
                case 5:
                    System.out.println("Operación cancelada.");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
            System.out.println("Cliente modificado correctamente.");
        }
    }

    public static void EliminarCuenta(Banco banco) {
        System.out.print("Ingrese el identificador de la cuenta que desea eliminar: ");
        int identificador = scanner.nextInt();
        scanner.nextLine();

        // Buscar la cuenta por identificador y eliminarla
        boolean cuentaEncontrada = false;
        for (CuentasBancarias cuenta : banco.getCuentas()) {
            if (cuenta.getIdentificador() == identificador) {
                banco.eliminarCuenta(cuenta);
                cuentaEncontrada = true;
                break;
            }
        }

        if (cuentaEncontrada) {
            System.out.println("Cuenta eliminada exitosamente.");
        } else {
            System.out.println("No se encontró ninguna cuenta con el identificador especificado.");
        }
    }
}
