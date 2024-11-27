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
        abcPrestamos = new ABCPrestamos();
        usuarios = new ArrayList<>();
        libros = new ArrayList<>();
        prestamos = new ArrayList<>();

        usuarios.add(new Usuario(1, "Usuario1", "Apellido1", 30, "Direccion1", "123456789"));
        usuarios.add(new Usuario(2, "Usuario2", "Apellido2", 25, "Direccion2", "987654321"));
        libros.add(new Libro(1, "Libro1", "Autor1", "Editorial1", 2020));
        libros.add(new Libro(2, "Libro2", "Autor2", "Editorial2", 2021));
    }

    @Test
    void RPExistUserBook() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {

            when(JOptionPane.showInputDialog(anyString())).thenReturn("1");  // Simulate user ID input
            when(JOptionPane.showInputDialog(anyString())).thenReturn("1");  // Simulate book ID input


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

    @Test
    void FechaValidDateReturn() {

        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {

            when(JOptionPane.showInputDialog(anyString()))
                    .thenReturn("25")
                    .thenReturn("12")
                    .thenReturn("2024");

            // Call obtenerFecha method for "préstamo"
            Fecha fecha = abcPrestamos.obtenerFecha("préstamo");

            assertEquals(25, fecha.getDia());
            assertEquals(12, fecha.getMes());
            assertEquals(2024, fecha.getAnio());
        }
    }


    @Test
    void LibroUsarionoReg() {
        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {

            usuarios.clear();
            libros.clear();

            doNothing().when(JOptionPane.class);
            JOptionPane.showMessageDialog(any(), anyString());

            abcPrestamos.registrarPrestamos(usuarios, libros);

            assertEquals(0, abcPrestamos.getListaDePrestamos().size());
        }
    }

    @Test
    void PrestamoInvalidUNB() {

        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {

            when(JOptionPane.showInputDialog(anyString()))
                    .thenReturn("999") // Simulate an invalid user ID
                    .thenReturn("999"); // Simulate an invalid book ID

            doNothing().when(JOptionPane.class);
            JOptionPane.showMessageDialog(any(), anyString());

            abcPrestamos.registrarPrestamos(usuarios, libros);


            assertEquals(0, abcPrestamos.getListaDePrestamos().size());
        }
    }

    @Test
    void RegresoNoExiste() {

        try (MockedStatic<JOptionPane> mockedStatic = mockStatic(JOptionPane.class)) {

            when(JOptionPane.showInputDialog(anyString())).thenReturn("1");  // Simulate user ID input
            when(JOptionPane.showInputDialog(anyString())).thenReturn("999");  // Simulate invalid book ID input


            doNothing().when(JOptionPane.class);
            JOptionPane.showMessageDialog(any(), anyString());

            // Call devolverPrestamo (loan doesn't exist)
            abcPrestamos.devolverPrestamo();

            // Assert that no loans were removed
            assertEquals(0, abcPrestamos.getListaDePrestamos().size());
        }
    }


}
