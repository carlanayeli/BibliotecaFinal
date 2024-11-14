import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ABCLibros {
    private List<Libro> listaDeLibros = new ArrayList<>();

    private boolean existeLibro(int id) {
        for (Libro libro : listaDeLibros) {
            if (libro.getIdLibro() == id) {
                return true;
            }
        }
        return false;
    }

    public void registrarLibro() {
        int idLibro;
        String titulo;
        String autor;
        String editorial;
        int anio;

        do {
            try {
                String input = JOptionPane.showInputDialog("Introduce ID del libro:");
                if (input == null) return; // El usuario canceló la operación
                idLibro = Integer.parseInt(input);
                if (idLibro <= 0) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo.");
                    continue;
                }
                if (existeLibro(idLibro)) {
                    JOptionPane.showMessageDialog(null, "El libro ya existe, por favor verifique el ID.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el ID.");
            }
        } while (true);

        titulo = JOptionPane.showInputDialog("Título del libro:");
        if (titulo == null || titulo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El título no puede estar vacío.");
            return;
        }

        autor = JOptionPane.showInputDialog("Autor del libro:");
        if (autor == null || autor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El autor no puede estar vacío.");
            return;
        }

        editorial = JOptionPane.showInputDialog("Editorial:");
        if (editorial == null || editorial.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La editorial no puede estar vacía.");
            return;
        }

        do {
            try {
                String input = JOptionPane.showInputDialog("Año de publicación:");
                if (input == null) return; // El usuario canceló la operación
                anio = Integer.parseInt(input);
                if (anio <= 0) {
                    JOptionPane.showMessageDialog(null, "El año debe ser un número positivo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el año.");
            }
        } while (true);

        Libro nuevoLibro = new Libro(idLibro, titulo, autor, editorial, anio);
        listaDeLibros.add(nuevoLibro);

        JOptionPane.showMessageDialog(null, "Libro registrado exitosamente.");
    }

    public String mostrarLibros() {
        if (listaDeLibros.isEmpty()) {
            return "No hay libros registrados.";
        } else {
            StringBuilder sb = new StringBuilder("Libros registrados:\n\n");
            for (Libro libro : listaDeLibros) {
                sb.append(libro.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    public List<Libro> getListaDeLibros() {
        return listaDeLibros;
    }

    public void setListaDeLibros(List<Libro> listaDeLibros) {
        this.listaDeLibros = listaDeLibros;
    }
}
