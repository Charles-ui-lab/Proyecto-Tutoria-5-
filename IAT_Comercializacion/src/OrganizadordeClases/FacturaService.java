package OrganizadordeClases;
// Define el paquete al que pertenece esta clase para organizar el código en módulos.

import java.math.BigDecimal;
// Importa la clase BigDecimal para manejar números decimales con precisión (útil para dinero).

import java.math.RoundingMode;
// Importa la enumeración RoundingMode para definir cómo redondear valores decimales.

import java.text.NumberFormat;
// Importa NumberFormat para formatear números, especialmente monedas.

import java.util.Locale;
// Importa Locale para especificar configuraciones regionales (idioma, moneda).

public class FacturaService {
    // Define la clase pública FacturaService, que contiene la lógica para generar facturas.

    private static final BigDecimal IVA = new BigDecimal("0.19");
    // Constante estática y final que representa el porcentaje del IVA (19%), usando BigDecimal para precisión.

    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);
    // Constante estática para formatear valores monetarios según la configuración regional de Estados Unidos.

    public void generarFactura(Cliente cliente) {
        // Método público que no devuelve valor (void), recibe un objeto Cliente para generar una factura.

        BigDecimal montoCompra = cliente.getMontoCompra();
        // Obtiene el monto de la compra desde el objeto cliente (se asume que Cliente tiene este método).

        BigDecimal valorIva = montoCompra.multiply(IVA).setScale(2, RoundingMode.HALF_UP);
        // Calcula el IVA multiplicando el monto por el porcentaje IVA, redondeando a 2 decimales con HALF_UP.

        BigDecimal subtotalConIva = montoCompra.add(valorIva);
        // Suma el monto de la compra más el IVA para obtener el subtotal con IVA incluido.

        BigDecimal descuento = cliente.calcularDescuento(subtotalConIva);
        // Calcula el descuento aplicable llamando a un método del cliente que recibe el subtotal con IVA.

        BigDecimal totalPagar = subtotalConIva.subtract(descuento);
        // Calcula el total a pagar restando el descuento al subtotal con IVA.

        // Imprime en consola la factura con formato legible:
        System.out.println("\n--- FACTURA ---");
        System.out.println("Cliente: " + cliente.getNombre());
        // Muestra el nombre del cliente.

        System.out.println("Identificación: " + cliente.getIdentificacion());
        // Muestra la identificación del cliente.

        System.out.println("Monto compra: " + CURRENCY_FORMAT.format(montoCompra));
        // Muestra el monto de la compra formateado como moneda.

        System.out.println("IVA (19%): " + CURRENCY_FORMAT.format(valorIva));
        // Muestra el valor del IVA formateado.

        System.out.println("Subtotal con IVA: " + CURRENCY_FORMAT.format(subtotalConIva));
        // Muestra el subtotal con IVA.

        System.out.println("Descuento (" + cliente.getTipoCliente() + "): " + CURRENCY_FORMAT.format(descuento));
        // Muestra el descuento aplicado, indicando el tipo de cliente.

        System.out.println("Total a pagar: " + CURRENCY_FORMAT.format(totalPagar));
        // Muestra el total final a pagar después del descuento.

        System.out.println("-----------------\n");
        // Imprime una línea separadora para finalizar la factura.
    }
}
