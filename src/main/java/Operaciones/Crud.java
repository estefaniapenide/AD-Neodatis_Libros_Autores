/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operaciones;

import Cadenas.Cadenas;
import Cliente.Conexion;
import POO.Autor;
import POO.Libro;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import static Cliente.Conexion.getOdb;
import java.math.BigInteger;
import java.sql.Date;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
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

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        odb.store(autor);
        odb.commit();

        odb.close();

    }

    public static Autor buscarAutorPorDni(String dni) {

        Autor autor = null;

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", dni));
        Objects aut = odb.getObjects(query);
        while (aut.hasNext()) {
            autor = (Autor) aut.next();
        }

        odb.close();

        return autor;
    }

    public static void imprimirAutorPorDni(String dni) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        Autor autor = null;
        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", dni));
        Objects aut = odb.getObjects(query);
        while (aut.hasNext()) {
            autor = (Autor) aut.next();
            System.out.println(autor);
        }

        odb.close();

    }

    public static Libro buscarLibroPorCodigo(int cod) {

        Libro libro = null;

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Libro.class), Where.equal("cod", cod));
        Objects lib = odb.getObjects(query);
        while (lib.hasNext()) {
            libro = (Libro) lib.next();
        }
        odb.close();
        return libro;
    }

    public static void imprimirLibroPorCodigo(int cod) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        Libro libro = null;
        IQuery query = new CriteriaQuery((Libro.class), Where.equal("cod", cod));
        Objects lib = odb.getObjects(query);
        while (lib.hasNext()) {
            libro = (Libro) lib.next();
            System.out.println(libro);
        }

        odb.close();

    }

    public static void addLibroAAutor(Autor autor, Libro libro) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", autor.getDni()));
        Objects aut = odb.getObjects(query);
        Autor a = (Autor) aut.next();
        a.getLibros().add(libro);
        odb.store(a);
        odb.commit();

        odb.close();

    }

    public static void modificarDireccionAutor(String nuevaDireccion, Autor autor) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", autor.getDni()));
        Objects aut = odb.getObjects(query);
        Autor a = (Autor) aut.getFirst();

        Autor autorNuevo = new Autor();
        autorNuevo.setDni(a.getDni());
        autorNuevo.setNombre(a.getNombre());
        autorNuevo.setDireccion(nuevaDireccion);
        autorNuevo.setEdad(a.getEdad());
        autorNuevo.setNacionalidad(a.getNacionalidad());
        autorNuevo.setLibros(a.getLibros());

        odb.delete(a);

        odb.store(autorNuevo);
        odb.commit();

        odb.close();

    }

    public static Autor buscarAutorPorNombre(String nombre) {

        Autor autor = null;
        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", nombre));
        Objects aut = odb.getObjects(query);
        while (aut.hasNext()) {
            autor = (Autor) aut.next();
        }

        odb.close();

        return autor;

    }

    public static void mostrarLibrosDeAutor(Autor autor) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", autor.getNombre()));
        Objects aut = odb.getObjects(query);
        Autor a = (Autor) aut.getFirst();
        System.out.println("TÍTULO\t\tPRECIO");
        for (Libro libro : a.getLibros()) {
            System.out.println(libro.getTitulo() + "\t"
                    + libro.getPrecio());
        }

        odb.close();

    }

    public static void mostrarLibrosDeAutorConCodigo(Autor autor) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", autor.getNombre()));
        Objects aut = odb.getObjects(query);
        Autor a = (Autor) aut.getFirst();
        System.out.println("CÓDIGO\t\tTÍTULO\t\tPRECIO");
        for (Libro libro : a.getLibros()) {
            System.out.println(libro.getCod() + "\t\t"
                    + libro.getTitulo() + "\t"
                    + libro.getPrecio());
        }

        odb.close();

    }

    public static boolean libroPerteneceAAutor(Autor autor, Libro libro) {

        boolean pertenece = false;
        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        Autor a = null;
        IQuery query = new CriteriaQuery((Autor.class), Where.equal("dni", autor.getDni()));
        Objects aut = odb.getObjects(query);
        while (aut.hasNext()) {
            a = (Autor) aut.next();
        }

        if (a != null) {
            for (Libro lib : a.getLibros()) {
                if (lib.getCod() == libro.getCod()) {
                    pertenece = true;
                }
            }
        }

        odb.close();

        return pertenece;

    }

    public static Libro buscarLibroPorTitulo(String titulo) {

        Libro libro = null;
        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Libro.class), Where.equal("titulo", titulo));
        Objects lib = odb.getObjects(query);
        while (lib.hasNext()) {
            libro = (Libro) lib.next();
        }

        odb.close();

        return libro;

    }

    public static void modificarPrecioLibroDadoNombreAutorYTituloLibro(float nuevoPrecioLibro, String nombreAutor, String titulo) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", nombreAutor));
        Objects aut = odb.getObjects(query);
        Autor a = (Autor) aut.getFirst();
        a.getLibros();
        for (Libro libro : a.getLibros()) {
            if (libro.getTitulo().equals(titulo)) {
                libro.setPrecio(nuevoPrecioLibro);
            }
        }
        odb.store(a);
        odb.commit();

        odb.close();

    }

    public static void autoresNacionalidadDeterminada(String nacionalidad) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        Values valores = odb.getValues(new ValuesCriteriaQuery(Autor.class, Where.equal("nacionalidad", nacionalidad)).field("nombre").field("dni").field("direccion").field("edad").field("nacionalidad"));
        System.out.println("NOMBRE\t\tDNI\t\tDIRECCIÓN\t\t\tEDAD\t\tNACIONALIDAD");
        while (valores.hasNext()) {
            ObjectValues objectValues = (ObjectValues) valores.next();
            System.out.println(objectValues.getByAlias("nombre") + "\t\t"
                    + objectValues.getByAlias("dni") + "\t"
                    + objectValues.getByAlias("direccion") + "\t\t"
                    + objectValues.getByAlias("edad") + "\t\t"
                    + objectValues.getByAlias("nacionalidad"));
        }

        odb.close();

    }

    public static void autoresNacionalidadDeterminadaMenoresDeCiertaEdad(String nacionalidad, int edad) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), new And().add(Where.equal("nacionalidad", nacionalidad)).add(Where.lt("edad", edad)));
        Objects aut = odb.getObjects(query);

        System.out.println("NOMBRE\t\tEDAD\t\tLIBROS");
        while (aut.hasNext()) {
            Autor autor = (Autor) aut.next();
            System.out.print(autor.getNombre() + "\t\t" + autor.getEdad() + "\t\t");
            for (Libro libro : autor.getLibros()) {
                System.out.print("'" + libro.getTitulo() + "' ");
            }
            System.out.println();
        }

        odb.close();

    }

    public static void numeroAutoresPorNacionalidad() {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        Values valores = odb.getValues(new ValuesCriteriaQuery(Autor.class).field("nacionalidad").count("dni").groupBy("nacionalidad"));
        while (valores.hasNext()) {
            ObjectValues objectValues = (ObjectValues) valores.nextValues();
            BigInteger numero = (BigInteger) objectValues.getByAlias("dni");
            String nacionalidad = (String) objectValues.getByAlias("nacionalidad");
            System.out.println(nacionalidad + " : " + numero.intValue());
        }

        odb.close();

    }

    public static void borrarLibro(Libro libro, Autor autor) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", autor.getNombre()));
        Objects aut = odb.getObjects(query);
        while (aut.hasNext()) {
            Autor a = (Autor) aut.next();
            for (Libro lib : a.getLibros()) {
                if (lib.getCod() == libro.getCod()) {
                    a.getLibros().remove(lib);
                }
            }

            odb.store(a);
            odb.commit();
        }

        odb.close();
    }

    public static void librosAutorEntreDosFechas(Autor autor, Date fechaInicio, Date fechaFin) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");

        IQuery query = new CriteriaQuery((Autor.class), Where.equal("nombre", autor.getNombre()));
        Objects aut = odb.getObjects(query);
        Autor a = (Autor) aut.getFirst();
        System.out.println("TÍTULO\t\tFECHA PUBLICACIÓN");
        for (Libro lib : a.getLibros()) {
            if (lib.getFechaPublicacion().before(fechaFin) && lib.getFechaPublicacion().after(fechaInicio)) {
                System.out.println(lib.getTitulo() + "\t"
                        + lib.getFechaPublicacion());
            }
        }
        odb.close();
    }

    public static Autor dadoLibroBsucarAutorYOtrosDatosLibro(Libro libro) {

        ODB odb = ODBFactory.openClient("localhost", 8000, "libreria");
        Autor autor = null;
        //Buscar por todos los autores hasta encontrar coincidencia con el libro

        odb.close();
        return autor;
    }
}
