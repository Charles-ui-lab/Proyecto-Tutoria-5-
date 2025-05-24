package Organizador_de_Clases;
//Creamos La clase Madre de donde las subClases heredan atributos y Metodos

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * CLASE BASE ABSTRACTA PARA PRODUCTOS LÁCTEOS
 *
 * Cambios realizados para solucionar errores:
 * 1. Implementa Serializable para permitir la persistencia de objetos
 * 2. Añade serialVersionUID para control de versiones
 * 3. Incluye métodos completos para manejo de IDs
 * 4. Actualiza los métodos de visualización para mostrar/escribir el ID
 */
public abstract class ProductoLacteo implements Serializable {
    // Identificador de versión para la serialización (requerido)
    private static final long serialVersionUID = 1L;

    // Campos de la clase
    protected int id;          // ID único del producto
    protected String nombre;   // Nombre del producto

    /**
     * CONSTRUCTOR DE LA CLASE
     * @param nombre Nombre del producto lácteo
     */
    public ProductoLacteo(String nombre) {
        this.nombre = nombre;
        this.id = 0; // Se inicializa a 0, se asignará luego desde Main
    }

    // ============ MÉTODOS DE ACCESO ============

    /**
     * OBTIENE EL ID ÚNICO DEL PRODUCTO
     * @return ID asignado al producto
     */
    public int getId() {
        return this.id;
    }

    /**
     * ESTABLECE UN ID ÚNICO PARA EL PRODUCTO
     * @param id Número identificador único
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * OBTIENE EL NOMBRE DEL PRODUCTO
     * @return Nombre del producto
     */
    public String getNombre() {
        return this.nombre;
    }

    // ============ MÉTODOS DE INFORMACIÓN ============

    /**
     * MUESTRA INFORMACIÓN BÁSICA DEL PRODUCTO EN CONSOLA
     * Incluye el ID único y nombre
     */
    public void mostrarInfoBasica() {
        System.out.println("ID del Producto: " + this.id);
        System.out.println("Nombre: " + this.nombre);
    }

    /**
     * ESCRIBE INFORMACIÓN BÁSICA DEL PRODUCTO EN ARCHIVO
     * @param writer Objeto FileWriter para escribir la información
     * @throws IOException Si ocurre un error al escribir
     */
    public void mostrarInfoEnArchivo(FileWriter writer) throws IOException {
        writer.write("ID: " + this.id + "\n");
        writer.write("Nombre: " + this.nombre + "\n");
    }

    // ============ MÉTODOS ABSTRACTOS ============

    /**
     * MÉTODO ABSTRACTO PARA PROCESAR EL PRODUCTO
     * (Implementación específica en cada subclase)
     */
    public abstract void procesar();

    /**
     * MÉTODO ABSTRACTO PARA ENVASAR EL PRODUCTO
     * (Implementación específica en cada subclase)
     */
    public abstract void envasar();
}