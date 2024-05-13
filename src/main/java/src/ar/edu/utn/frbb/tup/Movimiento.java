package src.ar.edu.utn.frbb.tup;

import java.time.LocalDateTime;

public class Movimiento {
    private LocalDateTime fecha;
    private String tipo;
    private double monto;

    public Movimiento(LocalDateTime fecha, String tipo, double monto) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Movimiento [fecha=" + fecha + ", tipo=" + tipo + ", monto=" + monto + "]";
    }

}
