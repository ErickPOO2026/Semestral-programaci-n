package utp.Ac.Pa.sistema.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private String correo;
    private String tipoUsuario;
    private String estado;
    private List<Prestamo> historialPrestamos;

    public Usuario(String idUsuario, String nombre, String correo, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.estado = "ACTIVO";
        this.historialPrestamos = new ArrayList<>();
    }

    public Prestamo solicitarPrestamo(Libro libro) {
        if (!"ACTIVO".equalsIgnoreCase(estado)) {
            System.out.println("[!] El usuario se encuentra sancionado o inactivo.");
            return null;
        }
        if (!libro.isDisponible()) {
            System.out.println("[!] El libro solicitado no está disponible.");
            return null;
        }

        if (libro.prestar()) {
            Date inicio = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(inicio);
            cal.add(Calendar.DAY_OF_MONTH, 7);
            Date esperada = cal.getTime();

            String idPrestamo = "PRES" + (historialPrestamos.size() + 1);
            Prestamo prestamo = new Prestamo(idPrestamo, inicio, esperada, this, libro);
            historialPrestamos.add(prestamo);
            return prestamo;
        }
        return null;
    }

    public List<Prestamo> consultarHistorial() {
        return historialPrestamos;
    }

    public String getIdUsuario() { return idUsuario; }
    public String getNombre() { return nombre; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}