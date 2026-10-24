package utp.Ac.Pa.sistema.domain;

public class Libro {
    private String isbn;
    private String titulo;
    private int anioPublicacion;
    private boolean disponible;
    private Autor autor;
    private Editorial editorial;
    private Categoria categoria;

    public Libro(String isbn, String titulo, int anioPublicacion, Autor autor, Editorial editorial, Categoria categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.disponible = true;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
    }

    public boolean prestar() {
        if (disponible) {
            this.disponible = false;
            return true;
        }
        return false;
    }

    public void devolver() {
        this.disponible = true;
    }

    public boolean isDisponible() { return disponible; }
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public Autor getAutor() { return autor; }
    public Editorial getEditorial() { return editorial; }
    public Categoria getCategoria() { return categoria; }
}