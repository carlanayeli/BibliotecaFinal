package org.example;

public class Prestamo {
  private int idUsuario;
  private int idLibro;
  private Fecha fechaPrestamo;
  private Fecha fechaDevolucion;

  public Prestamo(int idUsuario, int idLibro, Fecha fechaPrestamo, Fecha fechaDevolucion) {
    this.idUsuario = idUsuario;
    this.idLibro = idLibro;
    this.fechaPrestamo = fechaPrestamo;
    this.fechaDevolucion = fechaDevolucion;
  }

  // Getters y Setters
  public int getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
  }

  public int getIdLibro() {
    return idLibro;
  }

  public void setIdLibro(int idLibro) {
    this.idLibro = idLibro;
  }

  public Fecha getFechaPrestamo() {
    return fechaPrestamo;
  }

  public void setFechaPrestamo(Fecha fechaPrestamo) {
    this.fechaPrestamo = fechaPrestamo;
  }

  public Fecha getFechaDevolucion() {
    return fechaDevolucion;
  }

  public void setFechaDevolucion(Fecha fechaDevolucion) {
    this.fechaDevolucion = fechaDevolucion;
  }

  @Override
  public String toString() {
    return "ID Usuario: "
        + idUsuario
        + "\nID Libro: "
        + idLibro
        + "\nFecha Préstamo: "
        + fechaPrestamo
        + "\nFecha Devolución: "
        + fechaDevolucion
        + "\n";
  }
}
