package org.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockStatic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class ABCPrestamosTest {

    private ABCPrestamos abcPrestamos;
    private List<Usuario> usuarios;
    private List<Libro> libros;
    private List<Prestamo> prestamos;

    @BeforeEach
    void setUp() {
        // Create mock objects for Usuario, Libro, and Prestamo
        abcPrestamos = new ABCPrestamos();
        usuarios = new ArrayList<>();
        libros = new ArrayList<>();
        prestamos = new ArrayList<>();

        // Setup mock usuarios and libros
        usuarios.add(new Usuario(1, "Usuario1", "Apellido1", 30, "Direccion1", "123456789"));
        usuarios.add(new Usuario(2, "Usuario2", "Apellido2", 25, "Direccion2", "987654321"));
        libros.add(new Libro(1, "Libro1", "Autor1", "Editorial1", 2020));
        libros.add(new Libro(2, "Libro2", "Autor2", "Editorial2", 2021));
    }

    @Test
    void RPExistUserBook() {
        // Mock the static methods of JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Simulate user input for IDs
            when(JOptionPane.showInputDialog(anyString())).thenReturn("1");  // Simulate user ID input
            when(JOptionPane.showInputDialog(anyString())).thenReturn("1");  // Simulate book ID input

            // Mocking the void method showMessageDialog
            doNothing().when(JOptionPane.class);
            JOptionPane.showMessageDialog(any(), anyString());  // This is for mocking the void method

            // Call the registrarPrestamos method (which uses JOptionPane.showInputDialog)
            abcPrestamos.registrarPrestamos(usuarios, libros);

            // Assert that the loan was registered (use the getter for listaDePrestamos)
            assertEquals(1, abcPrestamos.getListaDePrestamos().size());
        }
    }

    @Test
    void MPNoBooks() {
        // Use mockStatic to mock static methods
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {

            // Here we don't need to mock anything because showMessageDialog is a void method
            // Just verify that it's called with the right arguments after calling the method
            abcPrestamos.mostrarPrestamos();

            // Verify that showMessageDialog was called with the "No hay préstamos registrados." message
            mockedStatic.verify(() -> JOptionPane.showMessageDialog(any(), eq("No hay préstamos registrados.")), times(1));
        }
    }

    /**
     * This is a test
     */
    @Test
    void mostrarPLExist() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Mocking the listaDePrestamos to return a non-empty list
            Prestamo prestamoMock = mock(Prestamo.class);
            abcPrestamos.listaDePrestamos = Collections.singletonList(prestamoMock); // Directly setting the list to contain a mock loan

            // Mocking toString to return a string representation of the loan
            when(prestamoMock.toString()).thenReturn("Prestamo mock");
            abcPrestamos.mostrarPrestamos();

            // Verify that showMessageDialog was called with the expected message
            mockedStatic.verify(() -> JOptionPane.showMessageDialog(any(), eq("Préstamos registrados:\n\nPrestamo mock\n")), times(1));
        }
    }


    /**
     *
     */
    @Test
    void FechaValidDateReturn() {
        // Mock static methods using mockStatic
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {

            // Mock showInputDialog to return specific values for day, month, and year
            when(JOptionPane.showInputDialog(anyString()))
                    .thenReturn("25")   // First call: Day = 25
                    .thenReturn("12")   // Second call: Month = 12
                    .thenReturn("2024"); // Third call: Year = 2024

            // Call obtenerFecha method for "préstamo"
            Fecha fecha = abcPrestamos.obtenerFecha("préstamo");

            // Assert that the date was returned correctly
            assertEquals(25, fecha.getDia());
            assertEquals(12, fecha.getMes());
            assertEquals(2024, fecha.getAnio());
        }
    }


    @Test
    void LibroUsarionoReg() {
        // Mock the static methods of JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Simulate an empty users and books list
            usuarios.clear();
            libros.clear();

            // Simulate an error message being shown
            doNothing().when(JOptionPane.class);
            JOptionPane.showMessageDialog(any(), anyString());

            // Try registering a loan (with no users or books)
            abcPrestamos.registrarPrestamos(usuarios, libros);

            // Verify that no loans were registered
            assertEquals(0, abcPrestamos.getListaDePrestamos().size());
        }
    }

    @Test
    void PrestamoInvalidUNB() {
        // Mock the static methods of JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Simulate user input for non-existing user and book
            when(JOptionPane.showInputDialog(anyString()))
                    .thenReturn("999") // Simulate an invalid user ID
                    .thenReturn("999"); // Simulate an invalid book ID

            // Mock showMessageDialog to avoid displaying dialog boxes
            doNothing().when(JOptionPane.class);
            JOptionPane.showMessageDialog(any(), anyString());

            // Try registering a loan (with invalid user and book)
            abcPrestamos.registrarPrestamos(usuarios, libros);

            // Assert that no loans were registered
            assertEquals(0, abcPrestamos.getListaDePrestamos().size());
        }
    }

    @Test
    void RegresoNoExiste() {
        // Mock the static methods of JOptionPane
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {
            // Simulate user input for a non-existing loan
            when(JOptionPane.showInputDialog(anyString())).thenReturn("1");  // Simulate user ID input
            when(JOptionPane.showInputDialog(anyString())).thenReturn("999");  // Simulate invalid book ID input

            // Mock showMessageDialog
            doNothing().when(JOptionPane.class);
            JOptionPane.showMessageDialog(any(), anyString());

            // Call devolverPrestamo (loan doesn't exist)
            abcPrestamos.devolverPrestamo();

            // Assert that no loans were removed
            assertEquals(0, abcPrestamos.getListaDePrestamos().size());
        }
    }


}
