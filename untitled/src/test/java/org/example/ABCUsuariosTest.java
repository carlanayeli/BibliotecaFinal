package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class ABCUsuariosTest {

    private ABCUsuarios abcUsuarios;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        abcUsuarios = new ABCUsuarios();
        usuario = new Usuario(1, "John Doe", "123 Main St", 1234567890, "john.doe@example.com", "INE123456");
        abcUsuarios.getListaDeUsuarios().clear(); // Ensure the list is empty before each test
    }

    @Test
    void testRegistrarUsuario() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("1");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("John Doe");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("123 Main St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1234567890");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("john.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE123456");

            abcUsuarios.registrarUsuario();

            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "Usuario no registrado.");
            assertEquals(usuario.getId(), usuarios.get(0).getId(), "ID del usuario no coincide.");
        }
    }

    @Test
    void testRULongName() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {

            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("1");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("John Doeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("123 Main St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1234567890");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("john.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE123456");

            abcUsuarios.registrarUsuario();

            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "Usuario no registrado.");
            assertEquals(usuario.getId(), usuarios.get(0).getId(), "ID del usuario no coincide.");
        }
    }

    @Test
    void testExistenUsuario() {
        abcUsuarios.getListaDeUsuarios().add(usuario);

        assertTrue(abcUsuarios.existeUsuario(1), "El usuario con ID 1 debería existir.");

        assertFalse(abcUsuarios.existeUsuario(999), "El usuario con ID 999 no debería existir.");
    }

    @Test
    void testMostrarUser_Empty() {

        String resultado = abcUsuarios.mostrarUsuarios();
        assertEquals("No hay usuarios registrados.", resultado, "Mensaje de usuarios vacíos no coincide.");
    }

    @Test
    void MostrarWithUsers() {

        abcUsuarios.getListaDeUsuarios().add(usuario);

        String resultado = abcUsuarios.mostrarUsuarios();
        assertTrue(resultado.contains("John Doe"), "El nombre del usuario no se muestra correctamente.");
        assertTrue(resultado.contains("123 Main St"), "El domicilio del usuario no se muestra correctamente.");
    }

    //so tech this does pass because they should be gettin gprompted to input another user until its different.
    @Test
    void RUExistingID() {
        abcUsuarios.getListaDeUsuarios().add(usuario);

        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("1", null);
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Jane Doe");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("456 Oak St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("987654321");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("jane.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE654321");

            abcUsuarios.registrarUsuario();

            // Verify that the number of users is still 1
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "No debería haberse registrado un nuevo usuario.");
        }
    }

    @Test
    void RUInvalidID() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:"))
                    .thenReturn("abc")
                    .thenReturn("123");

            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("John Doe");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("123 Main St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1234567890");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("john.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE123456");

            abcUsuarios.registrarUsuario();

            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "Debería haberse registrado un nuevo usuario con ID válido.");
            assertEquals("John Doe", usuarios.get(0).getNombre(), "El nombre del usuario no coincide.");
        }
    }

    //test for empty name
    @Test
    void RUEmptyName() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("2");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("456 Elm St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("987654321");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("jane.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE654321");

            abcUsuarios.registrarUsuario();

            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(0, usuarios.size(), "No debería haberse registrado un nuevo usuario sin nombre.");
        }
    }

    @Test
    void RUInvalidNumber() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("3");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Alice Smith");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("789 Maple St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("alice.smith@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE789123");

            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:"))
                    .thenReturn("invalid_phone")
                    .thenReturn("another_invalid_phone")
                    .thenReturn("1234567890");

            abcUsuarios.registrarUsuario();

            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "Debería haberse registrado un nuevo usuario con teléfono válido.");
            assertEquals("Alice Smith", usuarios.get(0).getNombre(), "El nombre del usuario no coincide.");
        }
    }

    //test for invalid email
    @Test
    void RUInvalidEmail() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("4");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Bob Johnson");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("101 Birch St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1122334455");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("invalid_email");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE987654");

            abcUsuarios.registrarUsuario();

            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(0, usuarios.size(), "No debería haberse registrado un nuevo usuario con correo electrónico inválido.");
        }
    }

    @Test
    void RUCanelInput() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("5");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Carol White");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("102 Pine St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("123456789");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("carol.white@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn(null); // Simulate cancel action

            abcUsuarios.registrarUsuario();

            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(0, usuarios.size(), "No debería haberse registrado un nuevo usuario debido a la cancelación.");
        }
    }

}
