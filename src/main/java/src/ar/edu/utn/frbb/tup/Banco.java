package src.ar.edu.utn.frbb.tup;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<CuentasBancarias> cuentas;
    private List<Clientes> clientes;

    public Banco() {
        cuentas = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    // Métodos para acceder a las cuentas y clientes
    public List<CuentasBancarias> getCuentas() {
        return cuentas;
    }

    public List<Clientes> getClientes() {
        return clientes;
    }

    // Métodos para agregar cuentas y clientes
    public void agregarCuenta(CuentasBancarias cuenta) {
        cuentas.add(cuenta);
    }

    public void agregarCliente(Clientes cliente) {
        clientes.add(cliente);
    }

    public void eliminarCuenta(CuentasBancarias cuenta) {
        cuentas.remove(cuenta);
    }

    public CuentasBancarias buscarCuentaPorIdentificador(int identificador) {
        for (CuentasBancarias cuenta : cuentas) {
            if (cuenta.getIdentificador() == identificador) {
                return cuenta;
            }
        }
        return null;
    }
}