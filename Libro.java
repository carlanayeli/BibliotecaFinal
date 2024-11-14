public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private String editorial;
    private int anio;

    public Libro(int idLibro, String titulo, String autor, String editorial, int anio) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
    }

    // Getters y Setters
    public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    @Override
    public String toString() {
        return "ID Libro: " + idLibro +
                "\nTítulo: " + titulo +
                "\nAutor: " + autor +
                "\nEditorial: " + editorial +
                "\nAño: " + anio + "\n";
    }
}
