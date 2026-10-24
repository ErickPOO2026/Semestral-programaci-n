package utp.Ac.Pa.sistema.domain;

import java.util.Date;
import java.util.List;

public class Reporte {
    private String idReporte;
    private Date fechaGeneracion;
    private String tipoReporte;

    public Reporte(String idReporte, String tipoReporte) {
        this.idReporte = idReporte;
        this.tipoReporte = tipoReporte;
        this.fechaGeneracion = new Date();
    }

    public void generarLibrosMasPrestados(List<Prestamo> prestamos) {
        System.out.println("\n--- REPORTE: LIBROS MAS PRESTADOS ---");
        System.out.println("Total de préstamos procesados: " + prestamos.size());
        for (Prestamo p : prestamos) {
            System.out.println("• " + p.getLibro().getTitulo() + " (Prestado a: " + p.getUsuario().getNombre() + ")");
        }
    }

    public void generarEstadisticasPorEditorial(List<Libro> libros) {
        System.out.println("\n--- REPORTE: ESTADISTICAS POR EDITORIAL ---");
        System.out.println("Total de títulos en acervo: " + libros.size());
        for (Libro l : libros) {
            System.out.println("• Editorial: " + l.getEditorial().getNombre() + " | Libro: " + l.getTitulo());
        }
    }
}