package org.example;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FechaTest {

    @Test
    void getDia() {
        Fecha fecha = new Fecha(24, 2, 2024);
        assertEquals(24, fecha.getDia());
    }

    @Test
    void setDia() {
        Fecha fecha = new Fecha(24, 2, 2024);
        fecha.setDia(20);
        assertEquals(20, fecha.getDia());
    }

    @Test
    void getMes() {
        Fecha fecha = new Fecha(24, 2, 2024);
        assertEquals(2, fecha.getMes());
    }

    @Test
    void setMes() {
        Fecha fecha = new Fecha(24, 2, 2024);
        fecha.setMes(12);
        assertEquals(12, fecha.getMes());
    }

    @Test
    void getAnio() {
        Fecha fecha = new Fecha(24, 2, 2024);
        assertEquals(2024, fecha.getAnio());
    }

    @Test
    void setAnio() {
        Fecha fecha = new Fecha(24, 2, 2024);
        fecha.setAnio(2023);
        assertEquals(2023, fecha.getAnio());
    }

    @Test
    void returnDate() {
        Fecha fecha = new Fecha(22, 5, 2015);
        assertEquals("22/05/2015", fecha.toString());
    }

    @Test
    void esValida() {
        Fecha fecha = new Fecha(29, 2, 2024); // 2024 is a leap year
        assertTrue(fecha.esValida());
    }

    @Test
    void esValida_NoValida_FebreroNoBisiesto() {
        Fecha fecha = new Fecha(29, 2, 2023); // 2023 is not a leap year
        assertFalse(fecha.esValida());
    }

    @Test
    void validInvalidoMonth() {
        Fecha fecha = new Fecha(15, 13, 2024); // 13 is not a valid month
        assertFalse(fecha.esValida());
    }

    @Test
    void validInvalidDay() {
        Fecha fecha = new Fecha(32, 1, 2024); // January has 31 days, so 32 is invalid
        assertFalse(fecha.esValida());
    }

    @Test
    void validInvalidYear() {
        Fecha fecha = new Fecha(10, 10, 1899); // Year before 1900 is invalid
        assertFalse(fecha.esValida());
    }

    @Test
    void FechaValida() {
        Fecha fecha = new Fecha(15, 5, 2024); // This is a valid date
        assertTrue(fecha.esValida());
    }
}
