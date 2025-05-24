package Organizador_de_Clases;
//Creamos SubClase ProductoLacteoConSabor quien hereda atributos y metodos
// de La Clase Madre ProductoLacteo

import java.io.FileWriter;
import java.io.IOException;

public class ProductoLacteoConSabor extends ProductoLacteo {
    private int horasFermentacion;
    private String[] sabores;
    private boolean contieneFrutas;
    private boolean llevaAzucar;

    public ProductoLacteoConSabor(String nombre, int horasFermentacion,
                                  String[] sabores, boolean contieneFrutas,
                                  boolean llevaAzucar) {
        super(nombre);
        this.horasFermentacion = horasFermentacion;
        this.sabores = sabores;
        this.contieneFrutas = contieneFrutas;
        this.llevaAzucar = llevaAzucar;
    }

    @Override
    public void mostrarInfoBasica() {
        System.out.println("Producto con sabor: " + getNombre());
        System.out.println("Horas de fermentación: " + horasFermentacion);
        System.out.println("Sabores: " + String.join(", ", sabores));
        System.out.println("Contiene frutas: " + (contieneFrutas ? "Sí" : "No"));
        System.out.println("Lleva azúcar: " + (llevaAzucar ? "Sí" : "No"));
    }

    @Override
    public void procesar() {
        System.out.println("Procesando producto con sabores especiales...");
    }

    @Override
    public void envasar() {
        System.out.println("Envasando producto en empaques especiales.");
    }

    // NUEVO MÉTODO PARA ARCHIVOS
    @Override
    public void mostrarInfoEnArchivo(FileWriter writer) throws IOException {
        super.mostrarInfoEnArchivo(writer);
        writer.write("Tipo: Producto con sabor\n");
        writer.write("Horas fermentación: " + horasFermentacion + "\n");
        writer.write("Sabores: " + String.join(", ", sabores) + "\n");
        writer.write("Contiene frutas: " + (contieneFrutas ? "Sí" : "No") + "\n");
        writer.write("Lleva azúcar: " + (llevaAzucar ? "Sí" : "No") + "\n");
    }

    public int getHorasFermentacion() {
        return horasFermentacion;
    }

    public String[] getSabores() {
        return sabores;
    }

    public boolean tieneFrutas() {
        return contieneFrutas;
    }

    public boolean llevaAzucar() {
        return llevaAzucar;
    }
}
