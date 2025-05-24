package OrganizadordeClases;
// Define el paquete al que pertenece esta clase para organizar el código en módulos y evitar conflictos de nombres.

import java.io.BufferedWriter;
// Importa la clase BufferedWriter, que permite escribir texto en archivos de forma eficiente con buffer.

import java.io.File;
// Importa la clase File, que representa archivos y directorios en el sistema de archivos.

import java.io.FileWriter;
// Importa la clase FileWriter, que permite escribir caracteres en archivos.

import java.io.IOException;
// Importa la clase IOException, que representa excepciones relacionadas con operaciones de entrada/salida.

import java.util.List;
// Importa la interfaz List para manejar colecciones ordenadas de objetos.

/**
 * Clase ReporteService que contiene métodos para generar reportes de clientes en formato texto y CSV,
 * guardándolos en una carpeta específica.
 */
public class ReporteService {

    // Define una constante estática y final para el nombre de la carpeta donde se guardarán los reportes.
    private static final String CARPETA_REPORTES = "Reportes";

    /**
     * Método privado y estático que crea la carpeta "Reportes" si no existe.
     *
     * @throws IOException si no se puede crear la carpeta.
     */
    private static void crearCarpetaReportesSiNoExiste() throws IOException {
        // Crea un objeto File que representa la carpeta "Reportes".
        File carpeta = new File(CARPETA_REPORTES);

        // Verifica si la carpeta no existe.
        if (!carpeta.exists()) {
            // Intenta crear la carpeta y guarda el resultado (true si se creó, false si no).
            boolean creada = carpeta.mkdir();

            // Si no se pudo crear la carpeta, lanza una excepción IOException con mensaje.
            if (!creada) {
                throw new IOException("No se pudo crear la carpeta " + CARPETA_REPORTES);
            }
        }
    }

    /**
     * Método público y estático que genera un reporte de clientes en formato texto (.txt).
     *
     * @param clientes lista de objetos Cliente a incluir en el reporte.
     * @param nombreArchivo nombre base del archivo (sin extensión).
     * @return true si el reporte se generó correctamente, false en caso de error.
     */
    public static boolean generarReporteTexto(List<Cliente> clientes, String nombreArchivo) {
        try {
            // Llama al método que asegura que la carpeta "Reportes" exista.
            crearCarpetaReportesSiNoExiste();

            // Construye la ruta completa del archivo dentro de la carpeta "Reportes",
            // usando File.separator para compatibilidad multiplataforma.
            String rutaArchivo = CARPETA_REPORTES + File.separator + nombreArchivo + ".txt";

            // Usa try-with-resources para abrir un BufferedWriter que escribirá en el archivo.
            // Esto garantiza que el recurso se cierre automáticamente al finalizar.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
                // Escribe el título del reporte seguido de un salto de línea.
                writer.write("Reporte de Clientes\n");
                // Escribe una línea separadora y dos saltos de línea para formato.
                writer.write("===================\n\n");

                // Itera sobre cada cliente en la lista.
                for (Cliente c : clientes) {
                    // Escribe la identificación del cliente seguida de un salto de línea.
                    writer.write("ID: " + c.getIdentificacion() + "\n");
                    // Escribe el nombre del cliente.
                    writer.write("Nombre: " + c.getNombre() + "\n");
                    // Escribe el tipo de cliente.
                    writer.write("Tipo Cliente: " + c.getTipoCliente() + "\n");
                    // Escribe el monto de compra del cliente.
                    writer.write("Monto Compra: " + c.getMontoCompra() + "\n");
                    // Escribe la dirección del cliente.
                    writer.write("Dirección: " + c.getDireccion() + "\n");
                    // Escribe el teléfono del cliente.
                    writer.write("Teléfono: " + c.getTelefono() + "\n");
                    // Escribe una línea separadora para distinguir cada cliente.
                    writer.write("-----------------------------\n");
                }
            }
            // Si todo salió bien, retorna true indicando éxito.
            return true;

        } catch (IOException e) {
            // En caso de error de entrada/salida, imprime el mensaje de error en la consola de error.
            System.err.println("Error al generar reporte de texto: " + e.getMessage());
            // Retorna false indicando que hubo un error.
            return false;
        }
    }

    /**
     * Método público y estático que genera un reporte de clientes en formato CSV (.csv).
     *
     * @param clientes lista de objetos Cliente a incluir en el reporte.
     * @param nombreArchivo nombre base del archivo (sin extensión).
     * @return true si el reporte se generó correctamente, false en caso de error.
     */
    public static boolean generarReporteCSV(List<Cliente> clientes, String nombreArchivo) {
        try {
            // Asegura que la carpeta "Reportes" exista antes de crear el archivo.
            crearCarpetaReportesSiNoExiste();

            // Construye la ruta completa del archivo CSV dentro de la carpeta "Reportes".
            String rutaArchivo = CARPETA_REPORTES + File.separator + nombreArchivo + ".csv";

            // Abre el BufferedWriter para escribir en el archivo CSV con try-with-resources.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
                // Escribe la cabecera del archivo CSV con los nombres de las columnas separados por comas.
                writer.write("Identificacion,Nombre,TipoCliente,MontoCompra,Direccion,Telefono\n");

                // Itera sobre cada cliente en la lista.
                for (Cliente c : clientes) {
                    // Formatea una línea CSV con los datos del cliente.
                    // Los campos de texto se colocan entre comillas para evitar problemas con comas internas.
                    String linea = String.format("\"%s\",\"%s\",\"%s\",%s,\"%s\",\"%s\"\n",
                            c.getIdentificacion(),
                            c.getNombre(),
                            c.getTipoCliente(),
                            c.getMontoCompra().toString(),
                            c.getDireccion(),
                            c.getTelefono());

                    // Escribe la línea formateada en el archivo CSV.
                    writer.write(linea);
                }
            }
            // Retorna true si el archivo se generó correctamente.
            return true;

        } catch (IOException e) {
            // En caso de error, imprime el mensaje en la consola de error.
            System.err.println("Error al generar reporte CSV: " + e.getMessage());
            // Retorna false indicando fallo.
            return false;
        }
    }
}
