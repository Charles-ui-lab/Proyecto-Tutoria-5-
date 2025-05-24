package OrganizadordeClases;
// Define el paquete al que pertenece esta clase para organizar el código en módulos.

import java.util.ArrayList;
// Importa la clase ArrayList, que es una implementación dinámica de la interfaz List.

import java.util.List;
// Importa la interfaz List, que representa una colección ordenada de elementos.

/**
 * Construimos El CRUD de clientes
 * El ayudante que hace todo el trabajo con los clientes:
 * - (C)Añade nuevos Registros o Clientes
 * - (R)Busca Registros o Clientes
 * - (U)Actualiza Registros o Clientes
 * - (D)Elimina Registros o Clientes
 */
// Comentario de documentación que explica que esta clase implementa un CRUD (Crear, Leer, Actualizar, Eliminar) para clientes.

public class ClienteService {
    // Define la clase pública ClienteService, encargada de gestionar operaciones sobre clientes.

    private List<Cliente> clientes = new ArrayList<>();
    // Declara una lista privada llamada clientes que almacenará objetos de tipo Cliente.
    // Se inicializa como un ArrayList vacío para poder agregar clientes dinámicamente.

    public void agregarCliente(Cliente cliente) {
        // Método público que recibe un objeto Cliente y no retorna nada (void).
        // Se encarga de añadir un nuevo cliente a la lista.

        clientes.add(cliente);
        // Añade el objeto cliente recibido a la lista clientes.
    }

    public Cliente buscarPorId(String id) {
        // Método público que recibe un String id y retorna un objeto Cliente.
        // Busca un cliente en la lista cuyo identificador coincida con el id dado.

        for (Cliente cliente : clientes) {
            // Itera sobre cada cliente en la lista clientes.

            if (cliente.getIdentificacion().equals(id)) {
                // Compara la identificación del cliente actual con el id buscado.
                // Si coinciden, retorna el cliente encontrado.

                return cliente;
            }
        }
        return null;
        // Si no se encuentra ningún cliente con el id dado, retorna null.
    }

    public List<Cliente> listarClientes() {
        // Método público que retorna una lista de clientes.
        // Devuelve una copia nueva de la lista clientes para evitar modificaciones externas.

        return new ArrayList<>(clientes);
        // Crea y retorna un nuevo ArrayList con los mismos elementos de clientes.
    }

    public boolean actualizarCliente(String id, Cliente nuevosDatos) {
        // Método público que recibe un id y un objeto Cliente con nuevos datos.
        // Retorna true si actualiza correctamente un cliente, false si no lo encuentra.

        for (int i = 0; i < clientes.size(); i++) {
            // Itera con índice sobre la lista clientes.

            if (clientes.get(i).getIdentificacion().equals(id)) {
                // Compara la identificación del cliente en la posición i con el id dado.

                clientes.set(i, nuevosDatos);
                // Reemplaza el cliente en la posición i con el objeto nuevosDatos.

                return true;
                // Retorna true indicando que la actualización fue exitosa.
            }
        }
        return false;
        // Retorna false si no se encontró ningún cliente con el id dado.
    }

    public boolean eliminarCliente(String id) {
        // Método público que recibe un id y retorna un booleano.
        // Elimina el cliente cuyo id coincida y retorna true si se eliminó alguno.

        return clientes.removeIf(cliente -> cliente.getIdentificacion().equals(id));
        // Utiliza removeIf con una expresión lambda para eliminar clientes cuyo id coincida.
        // Retorna true si se eliminó al menos un cliente, false si no se encontró ninguno.
    }
}
