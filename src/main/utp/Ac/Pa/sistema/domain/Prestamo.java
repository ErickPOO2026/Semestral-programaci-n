package utp.Ac.Pa.sistema.domain;

import java.util.Date;

public class Prestamo {
    private String idPrestamo;
    private Date fechaInicio;
    private Date fechaDevolucionEsperada;
    private String estado;
    private Usuario usuario;
    private Libro libro;

    public Prestamo(String idPrestamo, Date fechaInicio, Date fechaDevolucionEsperada, Usuario usuario, Libro libro) {
        this.idPrestamo = idPrestamo;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.usuario = usuario;
        this.libro = libro;
        this.estado = "EN_CURSO";
    }

    public Devolucion registrarDevolucion() {
        this.estado = "FINALIZADO";
        this.libro.devolver();
        Date fechaEntregaReal = new Date();
        String idDev = "DEV" + idPrestamo;
        return new Devolucion(idDev, fechaEntregaReal, this);
    }

    public boolean esVencido() {
        Date ahora = new Date();
        return ahora.after(fechaDevolucionEsperada) && "EN_CURSO".equals(estado);
    }

    public String getIdPrestamo() { return idPrestamo; }
    public Date getFechaDevolucionEsperada() { return fechaDevolucionEsperada; }
    public Usuario getUsuario() { return usuario; }
    public Libro getLibro() { return libro; }
}