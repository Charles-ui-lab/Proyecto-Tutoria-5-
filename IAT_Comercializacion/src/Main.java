// Importación de clases necesarias
import OrganizadordeClases.*; // Importa todas las clases del paquete OrganizadordeClases
import java.math.BigDecimal; // Para manejar valores monetarios con precisión
import java.util.ArrayList; // Para usar listas dinámicas
import java.util.List; // Para usar interfaces de listas
import java.util.Scanner; // Para leer entrada del usuario

public class Main {
    // Instancia del servicio de clientes (para operaciones CRUD)
    private static ClienteService clienteService = new ClienteService();
    // Instancia del servicio de facturas
    private static FacturaService facturaService = new FacturaService();
    // Scanner para leer entrada del usuario
    private static Scanner scanner = new Scanner(System.in);

    // Método principal - Punto de entrada de la aplicación
    public static void main(String[] args) {
        // Mensaje de bienvenida
        System.out.println("🍶 BIENVENIDO A IAT PRODUCTOS LÁCTEOS ARTESANALES 🐄");

        // Variable para controlar el bucle principal
        boolean salir = false;
        while (!salir) {
            // Mostrar menú principal
            mostrarMenuPrincipal();
            // Leer opción del usuario (número entre 1 y 6)
            int opcion = leerEntero(scanner, 1, 6);

            // Switch para manejar las opciones del menú
            switch (opcion) {
                case 1 -> gestionarClientes(); // Gestión CRUD de clientes
                case 2 -> generarFactura();    // Generar facturas
                case 3 -> generarReportes();   // Generar reportes
                case 4 -> listarClientes();    // Listar todos los clientes
                case 5 -> buscarCliente();     // Buscar cliente específico
                case 6 -> salir = confirmarSalida(); // Salir del programa
                default -> System.out.println("Opción no válida.");
            }
        }

        // Mensaje de despedida
        System.out.println("¡Gracias por usar el Sistema de Gestión Comercial IAT PRODUCTOS LÁCTEOS SAS 👋");
        // Cerrar scanner para liberar recursos
        scanner.close();
    }

    /**
     * Método para validar que un NIT sea numérico, positivo y de máximo 12 dígitos
     * @param mensaje Mensaje a mostrar al usuario
     * @return NIT válido como String
     */
    private static String leerNitValido(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            // Leer entrada y eliminar espacios en blanco
            String input = scanner.nextLine().trim();

            // Validar que solo contenga dígitos
            if (!input.matches("\\d+")) {
                System.out.println("Error: Solo se permiten caracteres numéricos (0-9)");
                continue;
            }

            // Validar longitud máxima de 12 dígitos
            if (input.length() > 12) {
                System.out.println("Error: El NIT no puede exceder los 12 dígitos");
                continue;
            }

            try {
                // Convertir a número y validar que sea positivo
                long nit = Long.parseLong(input);
                if (nit <= 0) {
                    System.out.println("Error: El NIT debe ser mayor que cero");
                    continue;
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Error: Valor numérico inválido");
            }
        }
    }

    /**
     * Muestra el menú principal de la aplicación
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestión de Clientes (CRUD)");
        System.out.println("2. Generar factura");
        System.out.println("3. Crear reportes");
        System.out.println("4. Ver todos los clientes");
        System.out.println("5. Buscar cliente");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
    }

    /**
     * Lee un número entero dentro de un rango específico
     * @param scanner Objeto Scanner para leer entrada
     * @param min Valor mínimo permitido
     * @param max Valor máximo permitido
     * @return Número entero válido
     */
    private static int leerEntero(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.printf("Por favor ingrese un número entre %d y %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor ingrese un número entero: ");
            }
        }
    }

    /**
     * Lee un valor BigDecimal positivo (para montos monetarios)
     * @param mensaje Mensaje a mostrar al usuario
     * @return Valor BigDecimal válido
     */
    private static BigDecimal leerBigDecimalPositivo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                // Leer valor y redondear a 2 decimales
                BigDecimal valor = new BigDecimal(scanner.nextLine()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (valor.compareTo(BigDecimal.ZERO) > 0) {
                    return valor;
                } else {
                    System.out.println("El monto debe ser mayor que cero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido (ej: 1500.75)");
            }
        }
    }

    /**
     * Menú de gestión de clientes (CRUD)
     */
    private static void gestionarClientes() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        System.out.println("1. Añadir nuevo cliente");
        System.out.println("2. Actualizar cliente");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Volver al menú principal");
        System.out.print("Elige una opción: ");

        int opcion = leerEntero(scanner, 1, 4);

        switch (opcion) {
            case 1 -> agregarCliente(); // Agregar nuevo cliente
            case 2 -> actualizarCliente(); // Actualizar cliente existente
            case 3 -> eliminarCliente(); // Eliminar cliente
            case 4 -> { return; } // Volver al menú principal
        }
    }

    /**
     * Método para agregar un nuevo cliente
     */
    private static void agregarCliente() {
        System.out.println("\n--- NUEVO CLIENTE ---");

        // Solicitar y validar NIT
        String id = leerNitValido("Número de identificación (NIT/cédula): ");
        System.out.print("Nombre completo o razón social: ");
        String nombre = scanner.nextLine();

        // Solicitar monto de compra inicial
        BigDecimal monto = leerBigDecimalPositivo("Monto de compra inicial: ");

        // Información opcional
        System.out.print("Dirección (opcional): ");
        String direccion = scanner.nextLine();

        System.out.print("Teléfono (opcional): ");
        String telefono = scanner.nextLine();

        // Determinar tipo de cliente
        System.out.print("¿Es mayorista? (s/n): ");
        boolean esMayorista = scanner.nextLine().equalsIgnoreCase("s");

        // Crear instancia del cliente según su tipo
        Cliente cliente = esMayorista ?
                new ClienteMayorista(id, nombre, monto, direccion, telefono) :
                new ClienteMinorista(id, nombre, monto, direccion, telefono);

        // Agregar cliente al servicio
        clienteService.agregarCliente(cliente);
        System.out.println("✅ Cliente añadido correctamente!");
    }

    /**
     * Método para actualizar un cliente existente
     */
    private static void actualizarCliente() {
        System.out.println("\n--- ACTUALIZAR CLIENTE ---");
        // Solicitar NIT del cliente a actualizar
        String id = leerNitValido("Ingrese el ID del cliente a actualizar: ");

        // Buscar cliente existente
        Cliente clienteExistente = clienteService.buscarPorId(id);
        if (clienteExistente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        // Mostrar datos actuales
        System.out.println("\nDatos actuales del cliente:");
        mostrarDetallesCliente(clienteExistente);

        // Solicitar nuevos datos (mantener los actuales si no se especifican)
        System.out.print("\nNuevo nombre (dejar vacío para no cambiar): ");
        String nuevoNombre = scanner.nextLine();
        if (nuevoNombre.isEmpty()) {
            nuevoNombre = clienteExistente.getNombre();
        }

        BigDecimal nuevoMonto = clienteExistente.getMontoCompra();
        System.out.print("Nuevo monto de compra (dejar vacío para no cambiar): ");
        String montoStr = scanner.nextLine();
        if (!montoStr.isEmpty()) {
            try {
                nuevoMonto = new BigDecimal(montoStr).setScale(2, BigDecimal.ROUND_HALF_UP);
            } catch (NumberFormatException e) {
                System.out.println("Formato de monto inválido. Se mantendrá el monto actual.");
            }
        }

        System.out.print("Nueva dirección (dejar vacío para no cambiar): ");
        String nuevaDireccion = scanner.nextLine();
        if (nuevaDireccion.isEmpty()) {
            nuevaDireccion = clienteExistente.getDireccion();
        }

        System.out.print("Nuevo teléfono (dejar vacío para no cambiar): ");
        String nuevoTelefono = scanner.nextLine();
        if (nuevoTelefono.isEmpty()) {
            nuevoTelefono = clienteExistente.getTelefono();
        }

        // Crear cliente actualizado según su tipo original
        Cliente clienteActualizado;
        if (clienteExistente instanceof ClienteMayorista) {
            clienteActualizado = new ClienteMayorista(
                    id,
                    nuevoNombre,
                    nuevoMonto,
                    nuevaDireccion,
                    nuevoTelefono
            );
        } else {
            clienteActualizado = new ClienteMinorista(
                    id,
                    nuevoNombre,
                    nuevoMonto,
                    nuevaDireccion,
                    nuevoTelefono
            );
        }

        // Intentar actualizar el cliente
        if (clienteService.actualizarCliente(id, clienteActualizado)) {
            System.out.println("✅ Cliente actualizado correctamente!");
        } else {
            System.out.println("No se pudo actualizar el cliente.");
        }
    }

    /**
     * Método para eliminar un cliente
     */
    private static void eliminarCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        // Solicitar NIT del cliente a eliminar
        String id = leerNitValido("Ingrese el ID del cliente a eliminar: ");

        // Confirmar eliminación
        System.out.print("¿Está seguro que desea eliminar este cliente? (s/n): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("s")) {
            // Intentar eliminar el cliente
            if (clienteService.eliminarCliente(id)) {
                System.out.println("✅ Cliente eliminado correctamente!");
            } else {
                System.out.println("No se pudo eliminar el cliente. Verifique el ID.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    /**
     * Método para listar todos los clientes
     */
    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        // Obtener lista de clientes
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados aún.");
        } else {
            // Mostrar detalles de cada cliente
            clientes.forEach(Main::mostrarDetallesCliente);
        }
    }

    /**
     * Muestra los detalles de un cliente
     * @param cliente Cliente a mostrar
     */
    private static void mostrarDetallesCliente(Cliente cliente) {
        System.out.println("\nID: " + cliente.getIdentificacion());
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Tipo: " + cliente.getTipoCliente());
        System.out.println("Monto de compra: " + cliente.getMontoCompra());
        System.out.println("Dirección: " + cliente.getDireccion());
        System.out.println("Teléfono: " + cliente.getTelefono());
        System.out.println("----------------------------------");
    }

    /**
     * Método para buscar un cliente por su NIT
     */
    private static void buscarCliente() {
        // Solicitar NIT del cliente a buscar
        String id = leerNitValido("\nIngresa el número de identificación a buscar: ");

        // Buscar cliente
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente != null) {
            System.out.println("\n--- CLIENTE ENCONTRADO ---");
            mostrarDetallesCliente(cliente);
        } else {
            System.out.println("No se encontró ningún cliente con ese ID.");
        }
    }

    /**
     * Método para generar una factura
     */
    private static void generarFactura() {
        // Solicitar NIT del cliente
        String id = leerNitValido("\nIngresa el número de identificación del cliente: ");

        // Buscar cliente y generar factura si existe
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente != null) {
            facturaService.generarFactura(cliente);
        } else {
            System.out.println("No se encontró ningún cliente con ese ID.");
        }
    }

    /**
     * Método para generar reportes de clientes
     */
    private static void generarReportes() {
        System.out.println("\n--- GENERAR REPORTES ---");
        // Obtener lista de clientes con el tipo específico
        List<OrganizadordeClases.Cliente> clientes = new ArrayList<>(clienteService.listarClientes());

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes para generar reportes.");
            return;
        }

        // Mostrar opciones de reporte
        System.out.println("\nSeleccione el formato del reporte:");
        System.out.println("1. Mostrar en consola");
        System.out.println("2. Generar archivo de texto (.txt)");
        System.out.println("3. Generar archivo CSV (.csv)");
        System.out.println("4. Volver al menú principal");
        System.out.print("Opción: ");

        int opcionReporte = leerEntero(scanner, 1, 4);

        switch (opcionReporte) {
            case 1 -> { // Mostrar reporte en consola
                System.out.println("\n--- REPORTE DE CLIENTES ---");
                clientes.forEach(Main::mostrarDetallesCliente);
                System.out.println("\nTotal clientes: " + clientes.size());
            }
            case 2 -> { // Generar reporte en archivo de texto
                System.out.print("Ingrese el nombre del archivo (sin extensión): ");
                String nombreArchivo = scanner.nextLine();
                boolean resultado = ReporteService.generarReporteTexto(clientes, nombreArchivo);
                System.out.println(resultado ? "✅ Reporte generado correctamente!" : "Error al generar reporte");
            }
            case 3 -> { // Generar reporte en archivo CSV
                System.out.print("Ingrese el nombre del archivo (sin extensión): ");
                String nombreArchivo = scanner.nextLine();
                boolean resultado = ReporteService.generarReporteCSV(clientes, nombreArchivo);
                System.out.println(resultado ? "✅ Reporte CSV generado correctamente!" : "Error al generar reporte CSV");
            }
            case 4 -> { return; } // Volver al menú principal
        }
    }

    /**
     * Método para confirmar salida del programa
     * @return true si el usuario confirma la salida
     */
    private static boolean confirmarSalida() {
        System.out.print("¿Está seguro que desea salir? (s/n): ");
        String confirmacion = scanner.nextLine();
        return confirmacion.equalsIgnoreCase("s");
    }
}