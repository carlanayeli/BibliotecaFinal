package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LibroTest {

    String bookName = "The Bell Jar";
    String author = "Sylvia Plath";
    String company = "Common House";
    int year = 1955;
    int idNum = 5;

    @Test
    void getIdLibro() {
        Libro libro = new Libro(idNum,bookName, author,company, year);
        assertEquals(idNum, libro.getIdLibro());
    }

    @Test
    void setIdLibro() {
        Libro libro = new Libro(idNum,bookName, author,company, year);
        assertEquals(idNum, libro.getIdLibro());
        libro.setIdLibro(1);
        assertEquals(1, libro.getIdLibro());
    }

    @Test
    void getTitulo() {
        Libro libro = new Libro(idNum, bookName, author,company, year);
        assertEquals(bookName, libro.getTitulo());
    }

    @Test
    void setTitulo() {
        Libro libro = new Libro(idNum,bookName, author,company, year);
        assertEquals(bookName, libro.getTitulo());
        libro.setTitulo("Wrong Norma");
        assertEquals("Wrong Norma", libro.getTitulo());
    }

    @Test
    void getAutor() {
        Libro libro = new Libro(idNum, bookName, author,company, year);
        assertEquals(author, libro.getAutor());
    }

    @Test
    void setAutor() {
        Libro libro = new Libro(idNum, bookName, author,company, year);
        assertEquals(author, libro.getAutor());
        libro.setAutor("Jane Austen");
        assertEquals("Jane Austen", libro.getAutor());
    }

    @Test
    void getEditorial() {
        Libro libro = new Libro(idNum, bookName, author,company, year);
        assertEquals(company, libro.getEditorial());
    }

    @Test
    void setEditorial() {
        Libro libro = new Libro(idNum, bookName, author,company, year);
        assertEquals(company, libro.getEditorial());
        libro.setEditorial("Harper and Row");
        assertEquals("Harper and Row", libro.getEditorial());
    }

    @Test
    void getAnio() {
        Libro libro = new Libro(idNum, bookName, author,company, year);
        assertEquals(year, libro.getAnio());
    }

    @Test
    void setAnio() {
        Libro libro = new Libro(idNum, bookName, author,company, year);
        assertEquals(year, libro.getAnio());
        libro.setAnio(1999);
        assertEquals(1999, libro.getAnio());
    }

    @Test
    void testToString() {
        Libro libro = new Libro(idNum, bookName, author,company, year);
        String expectedOutput = "ID Libro: 5\n" +
                                "Título: The Bell Jar\n" +
                                "Autor: Sylvia Plath\n" +
                                "Editorial: Common House\n" +
                                "Año: 1955\n";
        assertEquals(expectedOutput, libro.toString());
    }
}
