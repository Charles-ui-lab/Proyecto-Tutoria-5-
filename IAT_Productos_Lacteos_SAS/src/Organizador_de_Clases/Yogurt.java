package Organizador_de_Clases;
//Creamos la SubClase Yogurt quien hereda Atributos y Metodos
// de la Clase Madre ProductoLacteo
import java.io.FileWriter;
import java.io.IOException;

public class Yogurt extends ProductoLacteo {
    private int horasFermentacion;

    public Yogurt(String nombre, int horasFermentacion) {
        super(nombre);
        this.horasFermentacion = horasFermentacion;
    }

    @Override
    public void mostrarInfoBasica() {
        System.out.println("Yogurt: " + getNombre());
        System.out.println("Horas de fermentación: " + horasFermentacion);
    }

    @Override
    public void procesar() {
        System.out.println("Procesando yogurt...");
    }

    @Override
    public void envasar() {
        System.out.println("Envasando yogurt en recipientes plásticos.");
    }

    // NUEVO MÉTODO PARA ARCHIVOS
    @Override
    public void mostrarInfoEnArchivo(FileWriter writer) throws IOException {
        super.mostrarInfoEnArchivo(writer);
        writer.write("Tipo: Yogurt\n");
        writer.write("Horas fermentación: " + horasFermentacion + "\n");
    }

    public int getHorasFermentacion() {
        return horasFermentacion;
    }
}

