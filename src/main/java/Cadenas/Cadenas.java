/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cadenas;

/**
 *
 * @author Estefania
 */
public class Cadenas {

    public static void menu() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------\n"
                + "\t\tMENÚ LIBRERÍA\n\n"
                + "1.- Altas de autor con todos sus libros.\n"
                + "2.- Añadir un libro nuevo a un autor ya existente.\n"
                + "3.- Modificación de la dirección de un autor determinado, recibiendo su dni.\n"
                + "4.- Modificación del precio de un libro determinado, recibiendo el título y el nombre del autor.\n"
                + "5.- Borrado de libros, introduciendo el nombre de un autor y el código del libro.\n"
                + "6.- Consulta de todos los autores cuya nacionalidad sea ITALIANA.\n"
                + "7.- Consulta de los libros escritos por un autor determinado entre dos fechas.\n"
                + "8.- Visualiza todos los autores cuya nacionalidad sea ESPAÑOLA y su edad sea < 60 años.\n"
                + "9.- Visualiza por cada nación el número de autores.\n"
                + "10.- Introduciendo el nombre de un autor, se visualizan todos sus libros.\n"
                + "11.- Consultas.'Introduciendo el título de un libro visualice los datos del libro autor'.\n\n"
                + "12.- FIN\n"
                + "--------------------------------------------------------------------------------------------------------------------");
    }

    public static void defaultmensaje() {
        System.out.println("No ha introducido ninguan de las opciones.\n");
    }

    public static void fin() {
        System.out.println("PROGRAMA FINALIZADO.");
    }

    public static void inicioServidor() {
        System.out.println("SERVIDOR INICIADO...");
    }

    public static void conexionServidor() {
        System.out.println("CONEXIÓN CON SERVIDOR ESTABLECIDA");
    }

    public static void errorConexionServidor() {
        System.out.println("NO SE HA CONECTADO A NINGÚN SERVIDOR.\nINICIE EL SERVIDOR PARA PODER CONECTARSE.");
    }

    public static void datosAutor() {
        System.out.println("DATOS DEL AUTOR");
    }

    public static void dni() {
        System.out.println("DNI:");
    }

    public static void nombre() {
        System.out.println("NOMBRE:");
    }

    public static void direccion() {
        System.out.println("DIRECCIÓN:");
    }

    public static void nuevaDireccion() {
        System.out.println("NUEVA DIRECCIÓN:");
    }

    public static void edad() {
        System.out.println("EDAD:");
    }

    public static void nacionalidad() {
        System.out.println("NACIONALIDAD:");
    }

    public static void addLibros() {
        System.out.println("\nAÑADIR LIBRO\n"
                + "1.-SI\n"
                + "2.-NO");
    }

    public static void datosLibro() {
        System.out.println("\nDATOS DEL LIBRO");
    }

    public static void codigo() {
        System.out.println("CÓDIGO:");
    }

    public static void titulo() {
        System.out.println("TITULO:");
    }

    public static void categoria() {
        System.out.println("CATEGORÍA:");
    }

    public static void precio() {
        System.out.println("PRECIO:");
    }
    
    public static void nuevoPrecio() {
        System.out.println("NUEVO PRECIO:");
    }

    public static void fechaPublicacion() {
        System.out.println("FECHA DE PUBLICACIÓN(aaaa-mm-dd):");
    }

    public static void libroregistrado() {
        System.out.println("EL LIBRO HA SIDO REGISTRADO CORRECTAMENTE");
    }
}
