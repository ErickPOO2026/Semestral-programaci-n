package utp.Ac.Pa.sistema.domain;

import java.util.Date;

public class Devolucion {
    private String idDevolucion;
    private Date fechaEntrega;
    private int diasRetraso;
    private Prestamo prestamo;

    public Devolucion(String idDevolucion, Date fechaEntrega, Prestamo prestamo) {
        this.idDevolucion = idDevolucion;
        this.fechaEntrega = fechaEntrega;
        this.prestamo = prestamo;
        this.diasRetraso = calcularDiasRetraso();
    }

    public int calcularDiasRetraso() {
        long diff = fechaEntrega.getTime() - prestamo.getFechaDevolucionEsperada().getTime();
        if (diff <= 0) return 0;
        return (int) (diff / (1000 * 60 * 60 * 24));
    }

    public boolean requierePenalizacion() {
        return diasRetraso > 0;
    }

    public int getDiasRetraso() { return diasRetraso; }
    public String getIdDevolucion() { return idDevolucion; }
}