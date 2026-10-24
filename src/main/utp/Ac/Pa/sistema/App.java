package utp.Ac.Pa.sistema;

import utp.Ac.Pa.sistema.domain.*;
import utp.Ac.Pa.sistema.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;

public class App {
    private Administrador adminPrincipal;
    private List<Libro> listaLibros;
    private List<Usuario> listaUsuarios;
    private List<Prestamo> listaPrestamos;
    private List<Penalizacion> listaPenalizaciones;

    public App() {
        this.listaLibros = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.listaPrestamos = new ArrayList<>();
        this.listaPenalizaciones = new ArrayList<>();
        this.adminPrincipal = new Administrador("A001", "Admin General", "admin@utp.ac.pa", "SUPERUSER");
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        Autor autor1 = new Autor("AUT01", "Gabriel García Márquez", "Colombiana");
        Editorial ed1 = new Editorial("ED01", "Sudamericana", "Argentina");
        Categoria cat1 = new Categoria("CAT01", "Literatura", "Obras literarias clasicas");

        Libro libro1 = new Libro("978-0307474728", "Cien Años de Soledad", 1967, autor1, ed1, cat1);
        listaLibros.add(libro1);

        Usuario usuario1 = new Usuario("U001", "Adrian Villarreal", "adrian.villarreal@utp.ac.pa", "Estudiante");
        listaUsuarios.add(usuario1);
    }

    public void iniciarSistema() {
        IOUtils.printHeader("SISTEMA DE GESTION DE BIBLIOTECA ACADEMICA - UTP");
        boolean salir = false;

        while (!salir) {
            int opcion = IOUtils.showMenu();
            switch (opcion) {
                case 1:
                    registrarNuevoLibro();
                    break;
                case 2:
                    registrarNuevoUsuario();
                    break;
                case 3:
                    procesarPrestamo();
                    break;
                case 4:
                    procesarDevolucion();
                    break;
                case 5:
                    consultarCatalogo();
                    break;
                case 6:
                    generarReporte();
                    break;
                case 7:
                    salir = true;
                    System.out.println("\n[+] Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("\n[!] Opcion invalida. Intente de nuevo.");
            }
        }
    }

    private void registrarNuevoLibro() {
        IOUtils.printHeader("REGISTRO DE NUEVO LIBRO");
        String isbn = IOUtils.readString("Ingrese ISBN: ");
        String titulo = IOUtils.readString("Ingrese Titulo: ");
        int anio = IOUtils.readInt("Ingrese Año de Publicacion: ");

        String nomAutor = IOUtils.readString("Nombre del Autor: ");
        Autor autor = new Autor("AUT" + (System.currentTimeMillis() % 1000), nomAutor, "Desconocida");

        String nomEd = IOUtils.readString("Nombre de la Editorial: ");
        Editorial ed = new Editorial("ED" + (System.currentTimeMillis() % 1000), nomEd, "Desconocido");

        String nomCat = IOUtils.readString("Categoria Tematica: ");
        Categoria cat = new Categoria("CAT" + (System.currentTimeMillis() % 1000), nomCat, "General");

        Libro libro = new Libro(isbn, titulo, anio, autor, ed, cat);
        adminPrincipal.registrarLibro(libro);
        listaLibros.add(libro);
        System.out.println("\n[✓] Libro registrado con exito.");
    }

    private void registrarNuevoUsuario() {
        IOUtils.printHeader("REGISTRO DE NUEVO USUARIO");
        String id = IOUtils.readString("ID Usuario: ");
        String nombre = IOUtils.readString("Nombre Completo: ");
        String correo = IOUtils.readString("Correo Institucional: ");
        String tipo = IOUtils.readString("Tipo (Estudiante/Docente): ");

        Usuario usuario = new Usuario(id, nombre, correo, tipo);
        adminPrincipal.registrarUsuario(usuario);
        listaUsuarios.add(usuario);
        System.out.println("\n[✓] Usuario registrado con exito.");
    }

    private void procesarPrestamo() {
        IOUtils.printHeader("SOLICITUD DE PRESTAMO");
        String idUsuario = IOUtils.readString("Ingrese ID del Usuario: ");
        Usuario u = buscarUsuario(idUsuario);

        if (u == null) {
            System.out.println("\n[!] Usuario no encontrado.");
            return;
        }

        String isbn = IOUtils.readString("Ingrese ISBN del Libro: ");
        Libro l = buscarLibro(isbn);

        if (l == null) {
            System.out.println("\n[!] Libro no encontrado.");
            return;
        }

        if (!l.isDisponible()) {
            System.out.println("\n[!] El libro no se encuentra disponible actualmente.");
            return;
        }

        Prestamo p = u.solicitarPrestamo(l);
        if (p != null) {
            listaPrestamos.add(p);
            System.out.println("\n[✓] Prestamo registrado exitosamente. ID Prestamo: " + p.getIdPrestamo());
        }
    }

    private void procesarDevolucion() {
        IOUtils.printHeader("REGISTRO DE DEVOLUCION");
        String idPrestamo = IOUtils.readString("Ingrese ID de Prestamo: ");
        Prestamo p = buscarPrestamo(idPrestamo);

        if (p == null) {
            System.out.println("\n[!] Prestamo no encontrado.");
            return;
        }

        Devolucion d = p.registrarDevolucion();
        System.out.println("\n[✓] Devolucion procesada.");
        System.out.println("Dias de retraso: " + d.getDiasRetraso());

        if (d.requierePenalizacion()) {
            double monto = d.getDiasRetraso() * 1.50;
            Penalizacion pen = new Penalizacion("PEN" + (System.currentTimeMillis() % 1000), monto, "Devolución tardía", p.getUsuario());
            adminPrincipal.aplicarPenalizacion(pen);
            listaPenalizaciones.add(pen);
            System.out.println("[!] Se ha generado una PENALIZACION de $" + monto + " al usuario " + p.getUsuario().getNombre());
        }
    }

    private void consultarCatalogo() {
        IOUtils.printHeader("CATALOGO DE LIBROS");
        if (listaLibros.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        for (Libro l : listaLibros) {
            System.out.println("• " + l.getIsbn() + " - " + l.getTitulo() + " | Disponible: " + (l.isDisponible() ? "Si" : "No"));
        }
    }

    private void generarReporte() {
        IOUtils.printHeader("GENERACION DE REPORTES");
        Reporte r = new Reporte("REP01", "General");
        r.generarLibrosMasPrestados(listaPrestamos);
        r.generarEstadisticasPorEditorial(listaLibros);
    }

    private Usuario buscarUsuario(String id) {
        return listaUsuarios.stream().filter(u -> u.getIdUsuario().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    private Libro buscarLibro(String isbn) {
        return listaLibros.stream().filter(l -> l.getIsbn().equalsIgnoreCase(isbn)).findFirst().orElse(null);
    }

    private Prestamo buscarPrestamo(String id) {
        return listaPrestamos.stream().filter(p -> p.getIdPrestamo().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    public static void main(String[] args) {
        App app = new App();
        app.iniciarSistema();
    }
}