/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operaciones;

import java.util.Scanner;
import Cadenas.Cadenas;
import POO.Autor;
import POO.Libro;
import controldata.ControlData;
import java.sql.Date;

/**
 *
 * @author Estefania
 */
public class Gestiones {

    //Menu 1
    private static boolean autorYaGuardado = false;

    public static void altaAutorLibros(Scanner input) {

        System.out.println("\tALTA DE AUTOR CON TODOS SUS LIBROS");

        Autor autor = null;

        autor = nuevoAutor(input);

        if (!autorYaGuardado) {
            addLibros(input, autor);
            if (!autorYaGuardado) {
                Crud.storeAutor(autor);
            }
            System.out.println("AUTOR Y LIBROS GUARDADOS");
        }
    }

    private static Autor nuevoAutor(Scanner input) {

        Autor autor = null;
        autorYaGuardado = false;

        Cadenas.datosAutor();

        Cadenas.dni();
        String dni = ControlData.leerDni(input);

        autor = Crud.buscarAutorPorDni(dni);

        if (autor != null) {
            System.out.println("EL AUTOR CON DNI " + dni + " YA ESTÁ DADO DE ALTA");
            Crud.imprimirAutorPorDni(dni);
            autorYaGuardado = true;
        } else {
            Cadenas.nombre();
            String nombre = ControlData.leerString(input).toUpperCase();

            Cadenas.direccion();
            String direccion = ControlData.leerString(input).toUpperCase();

            Cadenas.edad();
            int edad = ControlData.leerInt(input);

            Cadenas.nacionalidad();
            String nacionalidad = ControlData.leerString(input).toUpperCase();

            autor = new Autor(dni, nombre, direccion, edad, nacionalidad);
            autorYaGuardado = false;
        }
        return autor;

    }

    private static void addLibros(Scanner input, Autor autor) {

        byte op = 2;
        do {
            Cadenas.addLibros();
            op = ControlData.leerByte(input);
            switch (op) {
                case 1:
                    nuevoLibro(input, autor);
                    break;
                case 2:
                    //System.out.println("NO SE AÑADEN MÁS LIBROS");
                    break;
                default:
                    Cadenas.defaultmensaje();
                    break;
            }
        } while (op != 2);

    }

    private static void nuevoLibro(Scanner input, Autor autor) {

        Libro libro = null;

        Cadenas.datosLibro();

        Cadenas.codigo();
        int cod = ControlData.leerInt(input);

        libro = Crud.buscarLibroPorCodigo(cod);

        if (libro != null) {
            System.out.println("EL LIBRO CON CÓDIGO " + cod + " YA ESTÁ DADO DE ALTA");
            Crud.imprimirLibroPorCodigo(cod);
        } else {

            Cadenas.titulo();
            String titulo = ControlData.leerString(input).toUpperCase();

            Cadenas.categoria();
            String categoria = ControlData.leerString(input).toUpperCase();

            Cadenas.precio();
            float precio = ControlData.leerFloat(input);

            Cadenas.fechaPublicacion();
            String fecha = ControlData.leerFecha(input);
            Date date = Date.valueOf(fecha);

            libro = new Libro(cod, titulo, categoria, precio, date);

            if (autorYaGuardado) {
                Crud.addLibroAAutor(autor, libro);
            } else {
                autor.getLibros().add(libro);
                Crud.storeAutor(autor);
                autorYaGuardado = true;
            }

            Cadenas.libroregistrado();
        }

    }

    //Menu 2
    public static void addLibroAAutor(Scanner input) {

        System.out.println("\tAÑADIR LIBRO A AUTOR YA EXISTENTE");
        Cadenas.datosAutor();
        Cadenas.dni();
        String dni = ControlData.leerDni(input);

        Autor autor = Crud.buscarAutorPorDni(dni);
        if (autor != null) {
            Crud.imprimirAutorPorDni(dni);
            Cadenas.datosLibro();
            Cadenas.codigo();
            int cod = ControlData.leerInt(input);
            Libro libro = Crud.buscarLibroPorCodigo(cod);

            if (libro == null) {

                Cadenas.titulo();
                String titulo = ControlData.leerString(input).toUpperCase();

                Cadenas.categoria();
                String categoria = ControlData.leerString(input).toUpperCase();

                Cadenas.precio();
                float precio = ControlData.leerFloat(input);

                Cadenas.fechaPublicacion();
                String fecha = ControlData.leerFecha(input);
                Date date = Date.valueOf(fecha);

                libro = new Libro(cod, titulo, categoria, precio, date);

                Crud.addLibroAAutor(autor, libro);

                Cadenas.libroregistrado();

            } else {
                System.out.println("EL LIBRO CON CÓDIGO " + cod + " YA EXISTE");
                Crud.imprimirLibroPorCodigo(cod);
            }
        } else {
            System.out.println("EL AUTOR CON DNI " + dni + " NO EXISTE");
        }
    }

    //Menú 3
    public static void modificarDireccionAutor(Scanner input) {

        System.out.println("\tMODIFICAR LA DIRECCIÓN DE UN AUTOR");
        Cadenas.datosAutor();
        Cadenas.dni();
        String dni = ControlData.leerDni(input);

        Autor autor = Crud.buscarAutorPorDni(dni);

        if (autor != null) {

            Crud.imprimirAutorPorDni(dni);

            Cadenas.nuevaDireccion();
            String nuevaDireccion = ControlData.leerString(input).toUpperCase();

            Crud.modificarDireccionAutor(nuevaDireccion, autor);

            System.out.println("LA DIRECCIÓN HA SIDO MODIFICADA");

            Crud.imprimirAutorPorDni(dni);

        } else {
            System.out.println("EL AUTOR CON DNI " + dni + " NO EXISTE");
        }
    }

    //Menú 4 //Pendiente por el CRUD libroPerteneceAAutor(autor, libro)
    public static void modificarPrecioLibroDadoAutorYTitulo(Scanner input) {

        System.out.println("\tMODIFICAR EL PRECIO DE UN LIBRO DADO SU TÍTULO Y SU AUTOR");

        Cadenas.datosAutor();
        Cadenas.nombre();
        String nombre = ControlData.leerString(input).toUpperCase();

        Autor autor = Crud.buscarAutorPorNombre(nombre);

        if (autor != null) {

            System.out.println("\nLIBROS DE " + nombre);
            Crud.mostrarLibrosDeAutor(autor);

            Cadenas.datosLibro();
            Cadenas.titulo();
            String titulo = ControlData.leerString(input).toUpperCase();

            Libro libro = Crud.buscarLibroPorTitulo(titulo);

            if (libro != null) {

                if (Crud.libroPerteneceAAutor(autor, libro)) {

                    Crud.imprimirLibroPorCodigo(libro.getCod());

                    Cadenas.nuevoPrecio();
                    float nuevoPrecio = ControlData.leerFloat(input);

                    Crud.modificarPrecioLibroDadoNombreAutorYTituloLibro(nuevoPrecio, nombre, titulo);

                } else {
                    System.out.println("EL LIBRO " + titulo + " NO PERTENECE AL AUTOR " + nombre);
                }
            } else {
                System.out.println("EL LIBRO " + titulo + " NO EXISTE");
            }
        } else {
            System.out.println("EL AUTOR " + nombre + " NO EXISTE");
        }

    }

    //Menú 5
    //Menú 6
    public static void autoresNacionalidadItaliana(Scanner input) {
        System.out.println("AUTORES DE NACIONALIDAD ITALIANA\n");
        Crud.autoresNacionalidadDeterminada("ITALIANA");
    }

    //Menú 7
    //Menú 8
    public static void autoresNacionalidadEspanolaMenoresDe60(Scanner input) {
        System.out.println("AUTORES DE NACIONALIDAD ESPAÑOLA MENORES DE 60 AÑOS\n");
        Crud.autoresNacionalidadDeterminadaMenoresDeCiertaEdad("ESPAÑOLA", 60);
    }

    //Menú 9
    public static void numeroAutoresPorNacionalidad(Scanner input) {
        System.out.println("NÚMERO DE AUTORES POR NACIONALIDAD\n");
        Crud.numeroAutoresPorNacionalidad();
    }

    //Menú 10
    public static void mostrarLibrosAutor(Scanner input) {
        System.out.println("LIBROS DE UN AUTOR DETERMINADO");
        Cadenas.datosAutor();
        Cadenas.nombre();
        String nombre = ControlData.leerString(input).toUpperCase();

        Autor autor = Crud.buscarAutorPorNombre(nombre);

        if (autor != null) {
            
            Crud.mostrarLibrosDeAutor(autor);

        } else {
            System.out.println("EL AUTOR " + nombre + " NO EXISTE");
        }
    }

}
