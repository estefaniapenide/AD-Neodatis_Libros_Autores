/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operaciones;

import POO.Autor;
import POO.Libro;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import static Cliente.Conexion.getOdb;
import java.math.BigInteger;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 *
 * @author Estefania
 */
public abstract class Crud {

    public static void storeAutor(Autor autor) {

        getOdb().store(autor);
        getOdb().commit();
    }

    public static Autor buscarAutorPorDni(String dni) {

        Autor autor = null;
        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", dni));
        Objects aut = getOdb().getObjects(query);
        while (aut.hasNext()) {
            autor = (Autor) aut.next();
        }

        return autor;
    }

    public static void imprimirAutorPorDni(String dni) {
        Autor autor = null;
        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", dni));
        Objects aut = getOdb().getObjects(query);
        while (aut.hasNext()) {
            autor = (Autor) aut.next();
            System.out.println(autor);
        }
    }

    public static Libro buscarLibroPorCodigo(int cod) {
        Libro libro = null;
        IQuery query = new CriteriaQuery((Libro.class), Where.equal("cod", cod));
        Objects lib = getOdb().getObjects(query);
        while (lib.hasNext()) {
            libro = (Libro) lib.next();
        }
        return libro;
    }

    public static void imprimirLibroPorCodigo(int cod) {

        Libro libro = null;
        IQuery query = new CriteriaQuery((Libro.class), Where.equal("cod", cod));
        Objects lib = getOdb().getObjects(query);
        while (lib.hasNext()) {
            libro = (Libro) lib.next();
            System.out.println(libro);
        }

    }

    public static void addLibroAAutor(Autor autor, Libro libro) {

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", autor.getDni()));
        Objects aut = getOdb().getObjects(query);
        Autor a = (Autor) aut.next();
        a.getLibros().add(libro);
        getOdb().store(a);
        getOdb().commit();

    }

    public static void modificarDireccionAutor(String nuevaDireccion, Autor autor) {

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", autor.getDni()));
        Objects aut = getOdb().getObjects(query);
        Autor a = (Autor) aut.next();
        a.setDireccion(nuevaDireccion);
        getOdb().store(a);
        getOdb().commit();
    }

    public static Autor buscarAutorPorNombre(String nombre) {

        Autor autor = null;
        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", nombre));
        Objects aut = getOdb().getObjects(query);
        while (aut.hasNext()) {
            autor = (Autor) aut.next();
        }

        return autor;

    }

    public static void mostrarLibrosDeAutor(Autor autor) {

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", autor.getNombre()));
        Objects aut = getOdb().getObjects(query);
        Autor a = (Autor) aut.getFirst();
        System.out.println("TÍTULO\t\tPRECIO");
        for (Libro libro : a.getLibros()) {
            System.out.println(libro.getTitulo()+"\t"
            +libro.getPrecio());
        }

    }

    public static boolean libroPerteneceAAutor(Autor autor, Libro libro) {

        boolean pertenece = false;
        Autor aut = null;

        IQuery query = new CriteriaQuery((Autor.class), new And().add(Where.equal("dni", autor.getDni())).add(Where.equal("libros.cod", libro.getCod())));
        Objects a = getOdb().getObjects(query);
        while (a.hasNext()) {
            aut = (Autor) a.next();
        }
        if (aut != null) {
            pertenece = true;
        }

        return pertenece;

    }

    public static Libro buscarLibroPorTitulo(String titulo) {

        Libro libro = null;
        IQuery query = new CriteriaQuery((Libro.class), Where.equal("titulo", titulo));
        Objects lib = getOdb().getObjects(query);
        while (lib.hasNext()) {
            libro = (Libro) lib.next();
        }
        return libro;

    }

    public static void modificarPrecioLibroDadoNombreAutorYTituloLibro(float nuevoPrecioLibro, String nombreAutor, String titulo) {

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", nombreAutor));
        Objects aut = getOdb().getObjects(query);
        Autor a = (Autor) aut.getFirst();
        a.getLibros();
        for (Libro libro : a.getLibros()) {
            if (libro.getTitulo().equals(titulo)) {
                libro.setPrecio(nuevoPrecioLibro);
            }
        }
        getOdb().store(a);
        getOdb().commit();
    }

    public static void autoresNacionalidadDeterminada(String nacionalidad) {

        Values valores = getOdb().getValues(new ValuesCriteriaQuery(Autor.class, Where.equal("nacionalidad", nacionalidad)).field("nombre").field("dni").field("direccion").field("edad").field("nacionalidad"));
        System.out.println("NOMBRE\t\tDNI\t\tDIRECCIÓN\t\t\tEDAD\t\tNACIONALIDAD");
        while (valores.hasNext()) {
            ObjectValues objectValues = (ObjectValues) valores.next();
            System.out.println(objectValues.getByAlias("nombre") + "\t\t"
                    + objectValues.getByAlias("dni") + "\t"
                    + objectValues.getByAlias("direccion") + "\t\t"
                    + objectValues.getByAlias("edad") + "\t\t"
                    + objectValues.getByAlias("nacionalidad"));
        }
    }

    public static void autoresNacionalidadDeterminadaMenoresDeCiertaEdad(String nacionalidad, int edad) {

        IQuery query = new CriteriaQuery((Autor.class), new And().add(Where.equal("nacionalidad", nacionalidad)).add(Where.lt("edad", edad)));
        Objects aut = getOdb().getObjects(query);

        System.out.println("NOMBRE\t\tEDAD\t\tLIBROS");
        while (aut.hasNext()) {
            Autor autor = (Autor) aut.next();
            System.out.print(autor.getNombre() + "\t\t" + autor.getEdad() + "\t\t");
            for (Libro libro : autor.getLibros()) {
                System.out.print("'" + libro.getTitulo() + "' ");
            }
            System.out.println();
        }
    }

    public static void numeroAutoresPorNacionalidad() {

        Values valores = getOdb().getValues(new ValuesCriteriaQuery(Autor.class).field("nacionalidad").count("dni").groupBy("nacionalidad"));
        while (valores.hasNext()) {
            ObjectValues objectValues = (ObjectValues) valores.nextValues();
            BigInteger numero = (BigInteger) objectValues.getByAlias("dni");
            String nacionalidad = (String) objectValues.getByAlias("nacionalidad");
            System.out.println(nacionalidad + " : " + numero.intValue());
        }
    }

}
