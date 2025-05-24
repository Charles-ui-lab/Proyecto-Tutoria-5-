package Organizador_de_Clases;

import java.io.FileWriter;
import java.io.IOException;

public class Kumis extends ProductoLacteo {
    private int horasFermentacion;

    public Kumis(String nombre, int horasFermentacion) {
        super(nombre);
        this.horasFermentacion = horasFermentacion;
    }

    @Override
    public void mostrarInfoBasica() {
        System.out.println("Kumis: " + getNombre());
        System.out.println("Horas de fermentación: " + horasFermentacion);
    }

    @Override
    public void procesar() {
        System.out.println("Procesando kumis...");
    }

    @Override
    public void envasar() {
        System.out.println("Envasando kumis en botellas de vidrio.");
    }

    // NUEVO MÉTODO PARA ARCHIVOS
    @Override
    public void mostrarInfoEnArchivo(FileWriter writer) throws IOException {
        super.mostrarInfoEnArchivo(writer);
        writer.write("Tipo: Kumis\n");
        writer.write("Horas fermentación: " + horasFermentacion + "\n");
    }

    public int getHorasFermentacion() {
        return horasFermentacion;
    }
}
