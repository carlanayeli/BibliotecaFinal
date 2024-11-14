import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ABCUsuarios {
    private List<Usuario> listaDeUsuarios = new ArrayList<>();

    private boolean existeUsuario(int id) {
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void registrarUsuario() {
        int id;
        String nombre;
        String domicilio;
        int telefono;
        String email;
        String ine;

        do {
            try {
                String input = JOptionPane.showInputDialog("Introduce ID del usuario:");
                if (input == null) return; // El usuario canceló la operación
                id = Integer.parseInt(input);
                if (id <= 0) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo.");
                    continue;
                }
                if (existeUsuario(id)) {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe, por favor verifique el ID.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el ID.");
            }
        } while (true);

        nombre = JOptionPane.showInputDialog("Nombre del usuario:");
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
            return;
        }

        domicilio = JOptionPane.showInputDialog("Domicilio:");
        if (domicilio == null || domicilio.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El domicilio no puede estar vacío.");
            return;
        }

        do {
            try {
                String input = JOptionPane.showInputDialog("Teléfono:");
                if (input == null) return; // El usuario canceló la operación
                telefono = Integer.parseInt(input);
                if (telefono <= 0) {
                    JOptionPane.showMessageDialog(null, "El teléfono debe ser un número positivo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el teléfono.");
            }
        } while (true);

        email = JOptionPane.showInputDialog("Email:");
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un email válido.");
            return;
        }

        ine = JOptionPane.showInputDialog("INE:");
        if (ine == null || ine.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El INE no puede estar vacío.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(id, nombre, domicilio, telefono, email, ine);
        listaDeUsuarios.add(nuevoUsuario);

        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
    }

    public String mostrarUsuarios() {
        if (listaDeUsuarios.isEmpty()) {
            return "No hay usuarios registrados.";
        } else {
            StringBuilder sb = new StringBuilder("Usuarios registrados:\n\n");
            for (Usuario usuario : listaDeUsuarios) {
                sb.append(usuario.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    public List<Usuario> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }
}