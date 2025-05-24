// Declaración del paquete al que pertenece la clase
package OrganizadordeClases;

// Importación para manejo preciso de valores monetarios
import java.math.BigDecimal;

/**
 * Clase que representa un cliente minorista en el sistema.
 * Hereda de la clase base Cliente e implementa los comportamientos específicos
 * para clientes que realizan compras al por menor, con un descuento del 5%.
 *
 * Los clientes minoristas son aquellos que realizan compras en pequeñas cantidades
 * para consumo propio o reventa a menor escala.
 */
public class ClienteMinorista extends Cliente {

    // Constante que define el porcentaje de descuento para minoristas (5%)
    // Se declara como static final porque es un valor fijo e inmutable
    private static final BigDecimal PORCENTAJE_DESCUENTO = new BigDecimal("0.05");

    /**
     * Constructor para crear un nuevo cliente minorista.
     *
     * @param identificacion Número de documento (cédula, NIT, etc.)
     * @param nombre Nombre completo o razón social
     * @param montoCompra Monto histórico de compras del cliente
     * @param direccion Dirección física (opcional)
     * @param telefono Número de contacto (opcional)
     */
    public ClienteMinorista(String identificacion, String nombre, BigDecimal montoCompra,
                            String direccion, String telefono) {
        // Llama al constructor de la clase padre (Cliente) para inicializar
        // los atributos comunes a todos los clientes
        super(identificacion, nombre, montoCompra, direccion, telefono);
    }

    /**
     * Calcula el descuento aplicable para clientes minoristas (5%).
     * Implementación del método abstracto de la clase Cliente.
     *
     * @param subtotalConIva Subtotal de la compra incluyendo el IVA
     * @return Valor del descuento redondeado a 2 decimales
     */
    @Override
    public BigDecimal calcularDescuento(BigDecimal subtotalConIva) {
        // Multiplica el subtotal por el porcentaje de descuento (5%)
        // y redondea el resultado a 2 decimales usando el método HALF_UP
        // que redondea hacia arriba si el decimal es >= 0.5
        return subtotalConIva.multiply(PORCENTAJE_DESCUENTO)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Devuelve el tipo de cliente (implementación del método abstracto).
     *
     * @return Cadena "Minorista" que identifica el tipo de cliente
     */
    @Override
    public String getTipoCliente() {
        return "Minorista";
    }
}