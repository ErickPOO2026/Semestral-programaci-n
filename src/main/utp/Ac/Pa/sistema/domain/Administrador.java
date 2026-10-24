package utp.Ac.Pa.sistema.domain;

public class Administrador {
    private String idAdmin;
    private String nombre;
    private String correo;
    private String nivelAcceso;

    public Administrador(String idAdmin, String nombre, String correo, String nivelAcceso) {
        this.idAdmin = idAdmin;
        this.nombre = nombre;
        this.correo = correo;
        this.nivelAcceso = nivelAcceso;
    }

    public void registrarLibro(Libro libro) {
        System.out.println("Admin " + nombre + " registro el libro: " + libro.getTitulo());
    }

    public void registrarUsuario(Usuario usuario) {
        System.out.println("Admin " + nombre + " registro al usuario: " + usuario.getNombre());
    }

    public void aplicarPenalizacion(Penalizacion p) {
        System.out.println("Admin " + nombre + " aplico penalizacion " + p.getIdPenalizacion());
    }

    public String getIdAdmin() { return idAdmin; }
}