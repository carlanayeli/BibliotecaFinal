package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrestamoTest {

    int userID = 1;
    int bookID = 666;
    Fecha fechaPrestamo = new Fecha(15, 11, 2024);
    Fecha fechaDevolucion = new Fecha(15, 12, 2024);

    @Test
    void getIdUsuario() {
        Prestamo prestamo = new Prestamo(userID, bookID, fechaPrestamo, fechaDevolucion);
        assertEquals(1, prestamo.getIdUsuario());
    }

    @Test
    void setIdUsuario() {
        Prestamo prestamo = new Prestamo(userID, bookID, fechaPrestamo, fechaDevolucion);
        prestamo.setIdUsuario(2);
        assertEquals(2, prestamo.getIdUsuario());
    }

    @Test
    void getIdLibro() {
        Prestamo prestamo = new Prestamo(userID, bookID, fechaPrestamo, fechaDevolucion);
        assertEquals(666, prestamo.getIdLibro());
    }

    @Test
    void setIdLibro() {
        Prestamo prestamo = new Prestamo(userID, bookID, fechaPrestamo, fechaDevolucion);
        prestamo.setIdLibro(102);
        assertEquals(102, prestamo.getIdLibro());
    }

    @Test
    void getFechaPrestamo() {
        Prestamo prestamo = new Prestamo(1, 101, fechaPrestamo, fechaDevolucion);
        assertEquals(fechaPrestamo, prestamo.getFechaPrestamo());
    }

    @Test
    void setFechaPrestamo() {
        Prestamo prestamo = new Prestamo(userID, bookID, fechaPrestamo, fechaDevolucion);
        Fecha nuevaFecha = new Fecha(16, 11, 2024);
        prestamo.setFechaPrestamo(nuevaFecha);
        assertEquals(nuevaFecha, prestamo.getFechaPrestamo());
    }

    @Test
    void getFechaDevolucion() {
        Prestamo prestamo = new Prestamo(userID, bookID, fechaPrestamo, fechaDevolucion);
        assertEquals(fechaDevolucion, prestamo.getFechaDevolucion());
    }

    @Test
    void setFechaDevolucion() {
        Prestamo prestamo = new Prestamo(userID, bookID, fechaPrestamo, fechaDevolucion);
        Fecha nuevaFecha = new Fecha(16, 12, 2024);
        prestamo.setFechaDevolucion(nuevaFecha);
        assertEquals(nuevaFecha, prestamo.getFechaDevolucion());
    }

    @Test
    void testToString() {
        Prestamo prestamo = new Prestamo(1, 101, fechaPrestamo, fechaDevolucion);
        String expected = "ID Usuario: 1\n" +
                "ID Libro: 101\n" +
                "Fecha Préstamo: " + fechaPrestamo + "\n" +
                "Fecha Devolución: " + fechaDevolucion + "\n";
        assertEquals(expected, prestamo.toString());
    }
}
