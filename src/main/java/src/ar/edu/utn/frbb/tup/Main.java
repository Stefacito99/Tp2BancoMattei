package src.ar.edu.utn.frbb.tup;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        Banco banco = new Banco(); // Crear una instancia de Banco
        boolean bandera = true;
        int decision;

        System.out.println("Hola! bienvenido al Banco Lynx!");
        System.out.println("Leer atentamente las opciones en pantalla");
        while (bandera) {
            System.out.println("Opcion '1': Crear Cuenta");
            System.out.println("Opcion '2': Depositar Dinero");
            System.out.println("Opcion '3': Retirar Dinero");
            System.out.println("Opcion '4': Transferir Dinero");
            System.out.println("Opcion '5': Ver Saldo");
            System.out.println("Opcion '6': Ver Historial");
            System.out.println("Opcion '7': Modificar Cuenta");
            System.out.println("Opcion '8': Eliminar Cuenta");
            System.out.println("Opcion '0': Salir");
            decision = scanner.nextInt();

            switch (decision) {
                case 1:
                    GestorDeCuentas.CrearCuenta(banco);
                    break;
                case 2:
                    GestorDeSaldo.depositar(banco);
                    break;
                case 3:
                    GestorDeSaldo.retirar(banco);
                    break;
                case 4:
                    GestorDeSaldo.transferir(banco);
                    break;
                case 5:
                    GestorDeSaldo.verSaldo(banco);
                    break;
                case 6:
                    GestorDeSaldo.verHistorial(banco);
                    break;
                case 7:
                    GestorDeCuentas.ModificarCuenta(banco);
                    break;
                case 8:
                    GestorDeCuentas.EliminarCuenta(banco);
                    break;
                case 0:
                    bandera = false;
                    break;
                default:
                    System.out.println("No ingreso una opcion correcta. Por favor lea antentamente las opciones posibles!");
                    break;
            }
            System.out.println("-------------------------------------------");
        }

        System.out.println("Gracias por utilizar nuestros servicios bancarios. ¡Esperamos verte pronto de nuevo!");
    }
}