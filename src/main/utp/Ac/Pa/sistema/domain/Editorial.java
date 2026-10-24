package utp.Ac.Pa.sistema.domain;

public class Editorial {
    private String idEditorial;
    private String nombre;
    private String pais;

    public Editorial(String idEditorial, String nombre, String pais) {
        this.idEditorial = idEditorial;
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getIdEditorial() { return idEditorial; }
    public String getNombre() { return nombre; }
    public String getPais() { return pais; }
}