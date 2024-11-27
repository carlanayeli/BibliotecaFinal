package org.example;

public class Usuario {
  private int id;
  private String nombre;
  private String domicilio;
  private int telefono;
  private String email;
  private String ine;

  public Usuario(int id, String nombre, String domicilio, int telefono, String email, String ine) {
    this.id = id;
    this.nombre = nombre;
    this.domicilio = domicilio;
    this.telefono = telefono;
    this.email = email;
    this.ine = ine;
  }

  // Getters y Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public int getTelefono() {
    return telefono;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIne() {
    return ine;
  }

  public void setIne(String ine) {
    this.ine = ine;
  }

  @Override
  public String toString() {
    return "ID: "
        + id
        + "\nNombre: "
        + nombre
        + "\nDomicilio: "
        + domicilio
        + "\nTeléfono: "
        + telefono
        + "\nEmail: "
        + email
        + "\nINE: "
        + ine
        + "\n";
  }
}
