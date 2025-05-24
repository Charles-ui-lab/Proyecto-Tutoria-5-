// Declaración del paquete al que pertenece la clase
package OrganizadordeClases;

// Importación de la clase BigDecimal para manejo preciso de valores monetarios
import java.math.BigDecimal;

/**
 * Clase que representa un cliente mayorista en el sistema.
 * Hereda de la clase abstracta Cliente e implementa los comportamientos específicos
 * para clientes mayoristas, incluyendo su cálculo de descuento particular.
 */
public class ClienteMayorista extends Cliente {

    // Constante que define el porcentaje de descuento para mayoristas (15%)
    private static final BigDecimal PORCENTAJE_DESCUENTO = new BigDecimal("0.15");

    /**
     * Constructor para crear un nuevo cliente mayorista.
     * @param identificacion Número único de identificación (NIT, cédula, etc.)
     * @param nombre Nombre completo o razón social del cliente
     * @param montoCompra Monto histórico de compras del cliente
     * @param direccion Dirección física del cliente (opcional)
     * @param telefono Número de contacto del cliente (opcional)
     */
    public ClienteMayorista(String identificacion, String nombre, BigDecimal montoCompra,
                            String direccion, String telefono) {
        // Llama al constructor de la clase padre (Cliente) para inicializar los atributos comunes
        super(identificacion, nombre, montoCompra, direccion, telefono);
    }

    /**
     * Implementación del método para calcular el descuento específico para clientes mayoristas.
     * Los mayoristas reciben un 15% de descuento sobre el subtotal que incluye IVA.
     * @param subtotalConIva Subtotal de la compra incluyendo el IVA
     * @return Valor del descuento calculado, redondeado a 2 decimales
     */
    @Override
    public BigDecimal calcularDescuento(BigDecimal subtotalConIva) {
        // Calcula el descuento multiplicando el subtotal por el porcentaje
        // y redondea el resultado a 2 decimales usando el método HALF_UP
        return subtotalConIva.multiply(PORCENTAJE_DESCUENTO)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Implementación del método que devuelve el tipo de cliente.
     * @return Cadena de texto que identifica al cliente como "Mayorista"
     */
    @Override
    public String getTipoCliente() {
        return "Mayorista";
    }
}