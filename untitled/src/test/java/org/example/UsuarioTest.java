package org.example;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UsuarioTest {

    //set and initialize data for test
    int userID = 1;
    String userName = "Snoop Dog";
    String addy = "1545 Pecan Grove";
    int phoneNum = 123456789;
    String email = "maindogsnoop@gmail.com";
    String ineNum = "INE123456";


    @Test
    void getId() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        assertEquals(userID, usuario.getId(), "The ID should be 1");
    }

    @Test
    void setId() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        usuario.setId(2);
        assertEquals(2, usuario.getId(), "The ID should be updated to 2");
    }

    @Test
    void getNombre() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        assertEquals(userName, usuario.getNombre(), "The name should be Snoop Dog");
    }

    @Test
    void setNombre() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        usuario.setNombre("Snoop Lion");
        assertEquals("Snoop Lion", usuario.getNombre(), "The name should be updated to Snoop Lion");
    }

    @Test
    void getDomicilio() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        assertEquals(addy, usuario.getDomicilio(), "The address should be 1545 Pecan Grove");
    }

    @Test
    void setDomicilio() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        usuario.setDomicilio("Avenida Principal 456");
        assertEquals("Avenida Principal 456", usuario.getDomicilio(), "The address should be updated to Avenida Principal 456");
    }

    @Test
    void getTelefono() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        assertEquals(phoneNum, usuario.getTelefono(), "The phone number should be 123456789");
    }

    @Test
    void setTelefono() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        usuario.setTelefono(987654321);
        assertEquals(987654321, usuario.getTelefono(), "The phone number should be updated to 987654321");
    }

    @Test
    void getEmail() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        assertEquals(email, usuario.getEmail(), "The email should be maindogsnoop@gmail.com");
    }

    @Test
    void setEmail() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        usuario.setEmail("snoop.lion@example.com");
        assertEquals("snoop.lion@example.com", usuario.getEmail(), "The email should be updated to snoop.lion@example.com");
    }

    @Test
    void getIne() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        assertEquals(ineNum, usuario.getIne(), "The INE should be INE123456");
    }

    @Test
    void setIne() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        usuario.setIne("INE654321");
        assertEquals("INE654321", usuario.getIne(), "The INE should be updated to INE654321");
    }

    @Test
    void testToString() {
        Usuario usuario = new Usuario(userID, userName, addy, phoneNum, email, ineNum);
        String expected = "ID: 1\n" +
                "Nombre: Snoop Dog\n" +
                "Domicilio: 1545 Pecan Grove\n" +
                "Teléfono: 123456789\n" +
                "Email: maindogsnoop@gmail.com\n" +
                "INE: INE123456\n";
        assertEquals(expected, usuario.toString(), "The toString method should return the correct string representation of the Usuario object");
    }
}
