


import Organizador_de_Clases.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * SISTEMA COMPLETO DE GESTIÓN DE PRODUCTOS LÁCTEOS
 * Con persistencia de datos y generación de reportes
 */
public class Main {
    // ================== CONSTANTES ==================
    private static final String ARCHIVO_DATOS = "productos.dat";
    private static final String CARPETA_REPORTES = "reportes";

    // ================== VARIABLES GLOBALES ==================
    private static ArrayList<ProductoLacteo> productos = new ArrayList<>();
    private static int contadorId = 1;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * MÉTODO PRINCIPAL
     */
    public static void main(String[] args) {
        inicializarSistema();

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = obtenerEntero(1, 6);
            procesarOpcion(opcion);
        } while (opcion != 6);

        finalizarPrograma();
    }

    // ================== INICIALIZACIÓN ==================

    /**
     * Configura el entorno inicial del sistema
     */
    private static void inicializarSistema() {
        crearCarpetaReportes();
        cargarDatos();
        System.out.println("\n=== *** IAT PRODUCTOS LACTEOS  SAS*** ===");
        System.out.println("\n=== SISTEMA GESTION DE PRODUCCIÓN INICIADO ===");
    }

    /**
     * Crea la carpeta para reportes si no existe
     */
    private static void crearCarpetaReportes() {
        File carpeta = new File(CARPETA_REPORTES);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    // ================== MENÚ PRINCIPAL ==================

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL DE PRODUCCIÓN ===");
        System.out.println("1. Crear producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Generar reportes");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción (1-6): ");
    }

    private static void procesarOpcion(int opcion) {
        switch(opcion) {
            case 1: crearProducto(); break;
            case 2: listarProductos(); break;
            case 3: actualizarProducto(); break;
            case 4: eliminarProducto(); break;
            case 5: menuReportes(); break;
            case 6: break;
            default: System.out.println("Opción no válida por favor ingrese numeros del 1 al 6 ");
        }
    }

    // ================== OPERACIONES CRUD ==================

    /**
     * Crea un nuevo producto lácteo
     */
    private static void crearProducto() {
        System.out.println("\n--- GESTION DE PRODUCCIÓN  CREAR NUEVO PRODUCTO ---");
        ProductoLacteo producto = seleccionarTipoProducto();

        if (producto != null) {
            producto.setId(contadorId++);
            productos.add(producto);
            System.out.println("\n✔ Producto creado con ID: " + producto.getId());
            mostrarResumenProducto(producto);
        }
    }

    /**
     * Muestra lista completa de productos
     */
    private static void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("\n No hay productos registrados.");
            return;
        }

        System.out.println("\n--- GESTION DE PRODUCCIÓN LISTADO DE PRODUCTOS ---");
        for (ProductoLacteo p : productos) {
            mostrarResumenProducto(p);
            System.out.println("---------------");
        }
    }

    /**
     * Actualiza un producto existente
     */
    private static void actualizarProducto() {
        if (productos.isEmpty()) {
            System.out.println("\nNo hay productos para actualizar.");
            return;
        }

        listarProductos();
        System.out.print("\nIngrese el ID del producto a actualizar: ");
        int id = obtenerEntero(1, contadorId-1);

        ProductoLacteo producto = buscarPorId(id);
        if (producto == null) {
            System.out.println("✖ No se encontró producto con el ID: " + id);
            return;
        }

        System.out.println("\nActualizando producto con el ID: " + id);
        ProductoLacteo actualizado = seleccionarTipoProducto();
        if (actualizado != null) {
            actualizado.setId(id);
            productos.set(productos.indexOf(producto), actualizado);
            System.out.println("✔ Producto actualizado exitosamente...!!");
        }
    }

    /**
     * Elimina un producto de la lista
     */
    private static void eliminarProducto() {
        if (productos.isEmpty()) {
            System.out.println("\nNo existen productos para eliminar.");
            return;
        }

        listarProductos();
        System.out.print("\nIngrese ID del producto a eliminar: ");
        int id = obtenerEntero(1, contadorId-1);

        ProductoLacteo producto = buscarPorId(id);
        if (producto != null) {
            productos.remove(producto);
            System.out.println("✔ Producto eliminado exitosamente...!!");
        } else {
            System.out.println("✖ No se encontró producto con el ID: " + id);
        }
    }

    // ================== REPORTES ==================

    /**
     * Menú para generación de reportes
     */
    private static void menuReportes() {
        System.out.println("\n=== GESTION DE PRODUCCIÓN GENERAR REPORTES ===");
        System.out.println("1. Reporte en texto (.txt)");
        System.out.println("2. Reporte para Excel (.csv)");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción (1-3): ");

        int opcion = obtenerEntero(1, 3);
        switch(opcion) {
            case 1: generarReporteTexto(); break;
            case 2: generarReporteCSV(); break;
            case 3: return;
        }
    }

    /**
     * Genera reporte en formato de texto legible
     */
    private static void generarReporteTexto() {
        if (productos.isEmpty()) {
            System.out.println("\nNo existen productos para generar reporte.");
            return;
        }

        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreArchivo = CARPETA_REPORTES + "/Gestión de Producción Reporte_" + fecha + ".txt";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("=== GESTIÓN DE PRODUCCIÓN  REPORTE DE PRODUCTOS LÁCTEOS ===\n");
            writer.write("Fecha generación: " + new Date() + "\n");
            writer.write("Total productos: " + productos.size() + "\n\n");

            for (ProductoLacteo p : productos) {
                writer.write("ID: " + p.getId() + "\n");
                writer.write("Tipo: " + p.getClass().getSimpleName() + "\n");
                p.mostrarInfoEnArchivo(writer);
                writer.write("----------------------------\n");
            }

            System.out.println("\n✔ Gestión de Producción Reporte generado: " + new File(nombreArchivo).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("\n✖ Error al generar reporte: " + e.getMessage());
        }
    }

    /**
     * Genera reporte en formato CSV para Excel
     */
    private static void generarReporteCSV() {
        if (productos.isEmpty()) {
            System.out.println("\nNo existen productos para generar reporte.");
            return;
        }

        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreArchivo = CARPETA_REPORTES + "/Reporte_" + fecha + ".csv";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            // Encabezado CSV
            writer.write("ID,Tipo,Nombre,Horas Fermentación,Sabores,Contiene Frutas,Lleva Azúcar\n");

            for (ProductoLacteo p : productos) {
                writer.write(p.getId() + ",");
                writer.write(p.getClass().getSimpleName() + ",");
                writer.write("\"" + p.getNombre() + "\",");

                if (p instanceof Yogurt) {
                    Yogurt y = (Yogurt) p;
                    writer.write(y.getHorasFermentacion() + ",N/A,N/A,N/A");
                }
                else if (p instanceof Kumis) {
                    Kumis k = (Kumis) p;
                    writer.write(k.getHorasFermentacion() + ",N/A,N/A,N/A");
                }
                else if (p instanceof ProductoLacteoConSabor) {
                    ProductoLacteoConSabor ps = (ProductoLacteoConSabor) p;
                    writer.write(ps.getHorasFermentacion() + ",");
                    writer.write("\"" + String.join(";", ps.getSabores()) + "\",");
                    writer.write(ps.tieneFrutas() ? "Sí," : "No,");
                    writer.write(ps.llevaAzucar() ? "Sí" : "No");
                }

                writer.write("\n");
            }

            System.out.println("\n✔ Gestión de Producción Reporte CSV generado: " + new File(nombreArchivo).getAbsolutePath());
            System.out.println("Puede abrir este archivo con Excel");
        } catch (IOException e) {
            System.out.println("\n✖ Error al generar CSV: " + e.getMessage());
        }
    }

    // ================== MÉTODOS AUXILIARES ==================

    /**
     * Muestra resumen completo de un producto
     */
    private static void mostrarResumenProducto(ProductoLacteo producto) {
        System.out.println("\nID: " + producto.getId());
        producto.mostrarInfoBasica();
        producto.procesar();
        producto.envasar();
    }

    /**
     * Busca producto por ID
     */
    private static ProductoLacteo buscarPorId(int id) {
        for (ProductoLacteo p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Permite seleccionar el tipo de producto a crear
     */
    private static ProductoLacteo seleccionarTipoProducto() {
        System.out.println("\nSeleccione tipo de producto:");
        System.out.println("1. Yogurt");
        System.out.println("2. Kumis");
        System.out.println("3. Producto con sabor");
        System.out.println("4. Cancelar");
        System.out.print("Opción: ");

        int opcion = obtenerEntero(1, 4);
        switch(opcion) {
            case 1: return crearYogurt();
            case 2: return crearKumis();
            case 3: return crearProductoConSabor();
            default: return null;
        }
    }

    // ================== CREACIÓN DE PRODUCTOS ==================

    private static Yogurt crearYogurt() {
        System.out.println("\n-- Registrando Yogurt --");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Horas de fermentación: ");
        int horas = obtenerEntero(0, 72);

        return new Yogurt(nombre, horas);
    }

    private static Kumis crearKumis() {
        System.out.println("\n-- Registrando Kumis --");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Horas de fermentación: ");
        int horas = obtenerEntero(0, 72);

        return new Kumis(nombre, horas);
    }

    private static ProductoLacteoConSabor crearProductoConSabor() {
        System.out.println("\n-- Registrando Producto con Sabor --");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Horas de fermentación: ");
        int horas = obtenerEntero(0, 72);

        System.out.print("¿Contiene frutas? (s/n): ");
        boolean frutas = scanner.nextLine().equalsIgnoreCase("s");

        System.out.print("¿Lleva azúcar? (s/n): ");
        boolean azucar = scanner.nextLine().equalsIgnoreCase("s");

        System.out.print("Sabores (separados por comas): ");
        String[] sabores = scanner.nextLine().split(",");
        for (int i = 0; i < sabores.length; i++) {
            sabores[i] = sabores[i].trim();
        }

        return new ProductoLacteoConSabor(nombre, horas, sabores, frutas, azucar);
    }

    // ================== PERSISTENCIA DE DATOS ==================

    @SuppressWarnings("unchecked")
    private static void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
            productos = (ArrayList<ProductoLacteo>) ois.readObject();

            // Encuentra el máximo ID para continuar la secuencia
            for (ProductoLacteo p : productos) {
                if (p.getId() >= contadorId) {
                    contadorId = p.getId() + 1;
                }
            }

            System.out.println("✔ Datos cargados (" + productos.size() + " productos)");
        } catch (FileNotFoundException e) {
            System.out.println("ℹ No se encontró archivo de datos previo. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠ Error al cargar datos: " + e.getMessage());
        }
    }

    private static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(productos);
            System.out.println("✔ Datos guardados (" + productos.size() + " productos)");
        } catch (IOException e) {
            System.out.println("⚠ Error al guardar datos: " + e.getMessage());
        }
    }

    // ================== UTILIDADES ==================

    /**
     * Obtiene un número entero dentro de un rango
     */
    private static int obtenerEntero(int min, int max) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.printf("Ingrese un valor entre %d y %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("¡Entrada inválida! Ingrese un número: ");
            }
        }
    }

    /**
     * Finaliza el programa adecuadamente
     */
    private static void finalizarPrograma() {
        guardarDatos();
        scanner.close();
        System.out.println("\n¡Gracias por usar el sistema de Gestion de Produccion *** IAT PRODUCTOS LACTEOS ***");
    }
}
