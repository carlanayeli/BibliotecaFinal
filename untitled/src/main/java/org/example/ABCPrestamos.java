package org.example;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ABCPrestamos {
  public List<Prestamo> listaDePrestamos = new ArrayList<>();

  // CARLA ADDED GETTER HERE:
  public List<Prestamo> getListaDePrestamos() {
    return listaDePrestamos;
  }

  public void registrarPrestamos(List<Usuario> usuarios, List<Libro> libros) {
    if (usuarios.isEmpty() || libros.isEmpty()) {
      JOptionPane.showMessageDialog(
          null, "No hay usuarios o libros registrados para realizar un préstamo.");
      return;
    }

    int idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario:"));
    int idLibro = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro:"));

    Usuario usuario = buscarUsuario(usuarios, idUsuario);
    Libro libro = buscarLibro(libros, idLibro);

    if (usuario == null || libro == null) {
      JOptionPane.showMessageDialog(null, "Usuario o libro no encontrado.");
      return;
    }

    Fecha fechaPrestamo = obtenerFecha("préstamo");
    Fecha fechaDevolucion = obtenerFecha("devolución");

    Prestamo nuevoPrestamo = new Prestamo(idUsuario, idLibro, fechaPrestamo, fechaDevolucion);
    listaDePrestamos.add(nuevoPrestamo);

    JOptionPane.showMessageDialog(null, "Préstamo registrado exitosamente.");
  }

  public void devolverPrestamo() {
    if (listaDePrestamos.isEmpty()) {
      JOptionPane.showMessageDialog(null, "No hay préstamos registrados.");
      return;
    }

    int idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del usuario:"));
    int idLibro = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro:"));

    Prestamo prestamoADevolver = null;
    for (Prestamo prestamo : listaDePrestamos) {
      if (prestamo.getIdUsuario() == idUsuario && prestamo.getIdLibro() == idLibro) {
        prestamoADevolver = prestamo;
        break;
      }
    }

    if (prestamoADevolver == null) {
      JOptionPane.showMessageDialog(null, "No se encontró el préstamo especificado.");
      return;
    }

    listaDePrestamos.remove(prestamoADevolver);
    JOptionPane.showMessageDialog(null, "Devolución registrada exitosamente.");
  }

  public void mostrarPrestamos() {
    if (listaDePrestamos.isEmpty()) {
      JOptionPane.showMessageDialog(null, "No hay préstamos registrados.");
    } else {
      StringBuilder sb = new StringBuilder("Préstamos registrados:\n\n");
      for (Prestamo prestamo : listaDePrestamos) {
        sb.append(prestamo.toString()).append("\n");
      }
      JOptionPane.showMessageDialog(null, sb.toString());
    }
  }

  private Usuario buscarUsuario(List<Usuario> usuarios, int id) {
    for (Usuario usuario : usuarios) {
      if (usuario.getId() == id) {
        return usuario;
      }
    }
    return null;
  }

  private Libro buscarLibro(List<Libro> libros, int id) {
    for (Libro libro : libros) {
      if (libro.getIdLibro() == id) {
        return libro;
      }
    }
    return null;
  }

  public Fecha obtenerFecha(String tipo) {
    int dia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el día de " + tipo + ":"));
    int mes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el mes de " + tipo + ":"));
    int anio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de " + tipo + ":"));
    return new Fecha(dia, mes, anio);
  }
}
