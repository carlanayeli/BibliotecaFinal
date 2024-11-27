package org.example;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

// manages user objects to check register new users and see if it already exist/list them
public class ABCUsuarios {
  private List<Usuario> listaDeUsuarios = new ArrayList<>();

  // checks if id exist already, i changed from Private to Public
  public boolean existeUsuario(int id) {
    for (Usuario usuario : listaDeUsuarios) {
      if (usuario.getId() == id) {
        return true;
      }
    }
    return false;
  }

  // registers
  public void registrarUsuario() {
    int id;
    String nombre;
    String domicilio;
    int telefono;
    String email;
    String ine;

    // Log to indicate that the method is being called
    System.out.println("Starting user registration...");

    // Check if user ID is unique
    do {
      try {
        String input = JOptionPane.showInputDialog("Introduce ID del usuario:");
        if (input == null) return; // User canceled the input
        System.out.println("User ID input received: " + input); // Log input
        id = Integer.parseInt(input);

        if (id <= 0) {
          JOptionPane.showMessageDialog(null, "El ID debe ser un número positivo.");
          continue;
        }
        if (existeUsuario(id)) {
          JOptionPane.showMessageDialog(null, "El usuario ya existe, por favor verifique el ID.");
          System.out.println("ID already exists: " + id); // Log ID check result
          continue;
        }
        break;
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el ID.");
        System.out.println("Invalid ID format entered."); // Log invalid ID input
      }
    } while (true);

    // Proceed with other inputs
    nombre = JOptionPane.showInputDialog("Nombre del usuario:");
    if (nombre == null || nombre.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
      return;
    }
    System.out.println("User name entered: " + nombre); // Log name input

    domicilio = JOptionPane.showInputDialog("Domicilio:");
    if (domicilio == null || domicilio.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "El domicilio no puede estar vacío.");
      return;
    }
    System.out.println("User address entered: " + domicilio); // Log address input

    do {
      try {
        String input = JOptionPane.showInputDialog("Teléfono:");
        if (input == null) return; // User canceled the input
        telefono = Integer.parseInt(input);
        if (telefono <= 0) {
          JOptionPane.showMessageDialog(null, "El teléfono debe ser un número positivo.");
          continue;
        }
        break;
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
            null, "Por favor, ingrese un número válido para el teléfono.");
        System.out.println("Invalid phone number format entered."); // Log invalid phone input
      }
    } while (true);

    email = JOptionPane.showInputDialog("Email:");
    if (email == null || email.trim().isEmpty() || !email.contains("@")) {
      JOptionPane.showMessageDialog(null, "Por favor, ingrese un email válido.");
      return;
    }
    System.out.println("User email entered: " + email); // Log email input

    ine = JOptionPane.showInputDialog("INE:");
    if (ine == null || ine.trim().isEmpty()) {
      JOptionPane.showMessageDialog(null, "El INE no puede estar vacío.");
      return;
    }
    System.out.println("User INE entered: " + ine); // Log INE input

    // Create the new user and add to list
    Usuario nuevoUsuario = new Usuario(id, nombre, domicilio, telefono, email, ine);
    listaDeUsuarios.add(nuevoUsuario);
    System.out.println(
        "User successfully registered with ID: " + id); // Log successful registration
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
