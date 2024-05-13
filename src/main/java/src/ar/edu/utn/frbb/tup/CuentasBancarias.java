package src.ar.edu.utn.frbb.tup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CuentasBancarias {
    private int identificador;
    private TipoDeCuenta tipodecuenta;
    private double saldo;
    private LocalDateTime fechadeingreso;
    private Clientes cliente;
    private List<Movimiento> historialMovimientos;

    public CuentasBancarias(int identificador, TipoDeCuenta tipodecuenta, double saldo, LocalDateTime fechadeingreso, Clientes cliente) {
        this.identificador = identificador;
        this.tipodecuenta = tipodecuenta;
        this.saldo = saldo;
        this.fechadeingreso = fechadeingreso;
        this.cliente = cliente;
        this.historialMovimientos = new ArrayList<>();
    }
    
    public CuentasBancarias() {
        historialMovimientos = new ArrayList<>();
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public TipoDeCuenta getTipodecuenta() {
        return tipodecuenta;
    }

    public void setTipodecuenta(TipoDeCuenta tipodecuenta) {
        this.tipodecuenta = tipodecuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFechadeingreso() {
        return fechadeingreso;
    }

    public void setFechadeingreso(LocalDateTime fechadeingreso) {
        this.fechadeingreso = fechadeingreso;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "CuentasBancarias [identificador=" + identificador + ", tipodecuenta=" + tipodecuenta + ", saldo="
                + saldo + ", fechadeingreso=" + fechadeingreso + ", cliente=" + cliente + "]";
    }

    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            System.out.println("Depósito de $" + monto + " realizado correctamente.");
            agregarMovimiento(new Movimiento(LocalDateTime.now(), "Depósito", monto));
        } else {
            System.out.println("El monto a depositar debe ser mayor que cero.");
        }
    }
    
    public void retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            System.out.println("Retiro de $" + monto + " realizado correctamente.");
            agregarMovimiento(new Movimiento(LocalDateTime.now(), "Retiro", -monto));
        } else {
            System.out.println("Error al retirar fondos: el monto a retirar debe ser mayor que cero y no puede superar el saldo disponible.");
        }
    }
    
    public void transferir(CuentasBancarias destino, double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            destino.depositar(monto);
            System.out.println("Transferencia de $" + monto + " a la cuenta #" + destino.getIdentificador() + " realizada correctamente.");
            agregarMovimiento(new Movimiento(LocalDateTime.now(), "Transferencia a #" + destino.getIdentificador(), -monto));
            destino.agregarMovimiento(new Movimiento(LocalDateTime.now(), "Transferencia desde #" + getIdentificador(), monto));
        } else {
            System.out.println("Error al realizar la transferencia: el monto a transferir debe ser mayor que cero y no puede superar el saldo disponible.");
        }
    }

    public List<Movimiento> getHistorialMovimientos() {
        return historialMovimientos;
    }

    public void agregarMovimiento(Movimiento movimiento) {
        historialMovimientos.add(movimiento);
    }
}
