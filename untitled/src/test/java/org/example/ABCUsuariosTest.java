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
            // Simulate user input for all prompts using static mocking
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("1");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("John Doe");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("123 Main St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1234567890");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("john.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE123456");

            // Register the user
            abcUsuarios.registrarUsuario();

            // Verify that the user has been added to the list
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "Usuario no registrado.");
            assertEquals(usuario.getId(), usuarios.get(0).getId(), "ID del usuario no coincide.");
        }
    }

    @Test
    void testRegistrarUsuarioLongName() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Simulate user input for all prompts using static mocking
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("1");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("John Doeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("123 Main St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1234567890");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("john.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE123456");

            // Register the user
            abcUsuarios.registrarUsuario();

            // Verify that the user has been added to the list
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "Usuario no registrado.");
            assertEquals(usuario.getId(), usuarios.get(0).getId(), "ID del usuario no coincide.");
        }
    }

    @Test
    void testExistenUsuario() {
        abcUsuarios.getListaDeUsuarios().add(usuario);

        // Test if the existing user is found
        assertTrue(abcUsuarios.existeUsuario(1), "El usuario con ID 1 debería existir.");

        // Test if a non-existing user is correctly not found
        assertFalse(abcUsuarios.existeUsuario(999), "El usuario con ID 999 no debería existir.");
    }


    @Test
    void testMostrarUsuarios_EmptyList() {
        // Test with no users
        String resultado = abcUsuarios.mostrarUsuarios();
        assertEquals("No hay usuarios registrados.", resultado, "Mensaje de usuarios vacíos no coincide.");
    }


    @Test
    void testMostrarUsuarios_WithUsers() {
        // Add a user to the list
        abcUsuarios.getListaDeUsuarios().add(usuario);

        // Test with one user
        String resultado = abcUsuarios.mostrarUsuarios();
        assertTrue(resultado.contains("John Doe"), "El nombre del usuario no se muestra correctamente.");
        assertTrue(resultado.contains("123 Main St"), "El domicilio del usuario no se muestra correctamente.");
    }

    //@Test
    //void testMostrarUsuarios() {
      //  // Test with no users
        //String resultado = abcUsuarios.mostrarUsuarios();
        //assertEquals("No hay usuarios registrados.", resultado, "Mensaje de usuarios vacíos no coincide.");

        // Test with one user
        //abcUsuarios.getListaDeUsuarios().add(usuario);
       // resultado = abcUsuarios.mostrarUsuarios();
       // assertTrue(resultado.contains("John Doe"), "El nombre del usuario no se muestra correctamente.");
       // assertTrue(resultado.contains("123 Main St"), "El domicilio del usuario no se muestra correctamente.");
   // }

    //so tech this does pass because they should be gettin gprompted to input another user until its different.
    @Test
    void testRegistrarUsuarioConIDExistente() {
        abcUsuarios.getListaDeUsuarios().add(usuario);

        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Simulate user input for an existing user ID
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("1", null);
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Jane Doe");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("456 Oak St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("987654321");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("jane.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE654321");

            // Try registering a new user with an existing ID
            abcUsuarios.registrarUsuario();

            // Verify that the number of users is still 1
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "No debería haberse registrado un nuevo usuario.");
        }
    }
/*
    //stuck in a loop . testing for invalid user id.
    @Test
    void testRegistrarUsuario_InvalidID() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Simulate invalid ID input (non-numeric)
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("abc");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("John Doe");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("123 Main St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1234567890");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("john.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE123456");

            // Try registering with invalid ID (non-numeric)
            abcUsuarios.registrarUsuario();

            // The user should not be added to the list
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(0, usuarios.size(), "No debería haberse registrado un nuevo usuario con ID inválido.");
        }
    }
 */
//TEST USER WITH INVALID ID THEN VALID TO EXIT LOOP
    @Test
    void testRegistrarUsuario_InvalidID() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Simulate invalid ID input (non-numeric) first, then a valid one
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:"))
                    .thenReturn("abc")  // First invalid input (non-numeric)
                    .thenReturn("123"); // Second valid input (numeric)

            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("John Doe");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("123 Main St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1234567890");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("john.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE123456");

            // Try registering with invalid ID (non-numeric) followed by a valid ID
            abcUsuarios.registrarUsuario();

            // The user should be added to the list now, since the ID is valid on the second try
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "Debería haberse registrado un nuevo usuario con ID válido.");
            assertEquals("John Doe", usuarios.get(0).getNombre(), "El nombre del usuario no coincide.");
        }
    }

    //test for empty name
    @Test
    void testRegistrarUsuario_EmptyName() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("2");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("456 Elm St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("987654321");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("jane.doe@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE654321");

            // Attempt to register a user with an empty name
            abcUsuarios.registrarUsuario();

            // Verify that the user was not added due to an empty name
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(0, usuarios.size(), "No debería haberse registrado un nuevo usuario sin nombre.");
        }
    }

    /*
    //test for invalid number. STUCK IN A LOOP. WHICH TECH I THINK IS RIGHT BECAUSE IT JUST KEEPS ASKING SUER FOR
    //A CORRECT NUMBER UNTIL IT RECIEVES THAT INPUT.
    @Test
    void testRegistrarUsuario_InvalidPhoneNumber() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("3");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Alice Smith");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("789 Maple St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("invalid_phone");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("alice.smith@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE789123");

            // Attempt to register with an invalid phone number
            abcUsuarios.registrarUsuario();

            // The user should not be added to the list due to invalid phone number
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(0, usuarios.size(), "No debería haberse registrado un nuevo usuario con número de teléfono inválido.");
        }
    }
     */

    @Test
    void testRegistrarUsuario_InvalidPhoneNumber() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            // Simulate the ID, Name, Address, and other details
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("3");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Alice Smith");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("789 Maple St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("alice.smith@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE789123");

            // Simulate invalid phone number input (non-numeric value) twice
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:"))
                    .thenReturn("invalid_phone")  // First invalid input
                    .thenReturn("another_invalid_phone")  // Second invalid input
                    .thenReturn("1234567890");  // Valid phone number after two invalid inputs

            // Attempt to register the user with invalid phone numbers followed by valid one
            abcUsuarios.registrarUsuario();

            // Verify that the user is successfully added after valid phone number is provided
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(1, usuarios.size(), "Debería haberse registrado un nuevo usuario con teléfono válido.");
            assertEquals("Alice Smith", usuarios.get(0).getNombre(), "El nombre del usuario no coincide.");
        }
    }


    //test for invalid email
    @Test
    void testRegistrarUsuario_InvalidEmail() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("4");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Bob Johnson");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("101 Birch St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("1122334455");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("invalid_email");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn("INE987654");

            // Attempt to register with an invalid email
            abcUsuarios.registrarUsuario();

            // The user should not be added due to invalid email
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(0, usuarios.size(), "No debería haberse registrado un nuevo usuario con correo electrónico inválido.");
        }
    }

    //test for user canceling input. STUCK IN A LOOP NOT SURE WHY. CANT HANDLE NULL VALUES.
    @Test
    void testRegistrarUsuario_CancelInput() {
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Introduce ID del usuario:")).thenReturn("5");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Nombre del usuario:")).thenReturn("Carol White");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Domicilio:")).thenReturn("102 Pine St");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Teléfono:")).thenReturn("123456789");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("Email:")).thenReturn("carol.white@example.com");
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog("INE:")).thenReturn(null); // Simulate cancel action

            // Try to register a user, but user cancels the last input (INE)
            abcUsuarios.registrarUsuario();

            // No user should be added due to canceled input
            List<Usuario> usuarios = abcUsuarios.getListaDeUsuarios();
            assertEquals(0, usuarios.size(), "No debería haberse registrado un nuevo usuario debido a la cancelación.");
        }
    }

}
