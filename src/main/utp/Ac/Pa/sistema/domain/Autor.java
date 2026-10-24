package utp.Ac.Pa.sistema.domain;

public class Autor {
    private String idAutor;
    private String nombre;
    private String nacionalidad;

    public Autor(String idAutor, String nombre, String nacionalidad) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getIdAutor() { return idAutor; }
    public String getNombre() { return nombre; }
    public String getNacionalidad() { return nacionalidad; }
}