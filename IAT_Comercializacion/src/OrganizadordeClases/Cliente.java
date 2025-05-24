// Declaración del paquete al que pertenece la clase
package OrganizadordeClases;

// Importación de la clase BigDecimal para manejar valores monetarios con precisión
import java.math.BigDecimal;

/**
 * Clase abstracta que representa la plantilla base para todos los tipos de clientes.
 * Proporciona la estructura común y los comportamientos básicos que todos los clientes deben tener.
 * Al ser abstracta, no puede ser instanciada directamente, solo a través de sus clases hijas.
 */
public abstract class Cliente {
    // Atributos protegidos para que sean accesibles por las clases hijas

    /** Número de identificación del cliente (NIT, cédula, etc.) */
    protected String identificacion;

    /** Nombre completo o razón social del cliente */
    protected String nombre;

    /** Monto histórico de compras del cliente */
    protected BigDecimal montoCompra;

    /** Dirección física del cliente (opcional) */
    protected String direccion;

    /** Número de teléfono del cliente (opcional) */
    protected String telefono;

    /**
     * Constructor para inicializar un cliente con sus datos básicos.
     * @param identificacion Número único de identificación del cliente
     * @param nombre Nombre completo o razón social
     * @param montoCompra Monto histórico de compras del cliente
     * @param direccion Dirección física (puede ser null o vacío)
     * @param telefono Número de contacto (puede ser null o vacío)
     */
    public Cliente(String identificacion, String nombre, BigDecimal montoCompra,
                   String direccion, String telefono) {
        // Asignación de parámetros a los atributos de la clase
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.montoCompra = montoCompra;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /**
     * Método abstracto para calcular el descuento aplicable al cliente.
     * Debe ser implementado por las clases concretas (hijas).
     * @param subtotalConIva Subtotal de la compra incluyendo IVA
     * @return Valor del descuento calculado
     */
    public abstract BigDecimal calcularDescuento(BigDecimal subtotalConIva);

    /**
     * Método abstracto para obtener el tipo de cliente.
     * Debe ser implementado por las clases concretas (hijas).
     * @return Cadena que identifica el tipo de cliente (ej: "Mayorista", "Minorista")
     */
    public abstract String getTipoCliente();

    // ============ MÉTODOS GETTERS ============ //

    /**
     * Obtiene el número de identificación del cliente
     * @return Número de identificación
     */
    public String getIdentificacion() { return identificacion; }

    /**
     * Obtiene el nombre o razón social del cliente
     * @return Nombre del cliente
     */
    public String getNombre() { return nombre; }

    /**
     * Obtiene el monto histórico de compras del cliente
     * @return Monto total de compras
     */
    public BigDecimal getMontoCompra() { return montoCompra; }

    /**
     * Obtiene la dirección del cliente
     * @return Dirección física (puede ser null o vacío)
     */
    public String getDireccion() { return direccion; }

    /**
     * Obtiene el número de teléfono del cliente
     * @return Número de contacto (puede ser null o vacío)
     */
    public String getTelefono() { return telefono; }
}