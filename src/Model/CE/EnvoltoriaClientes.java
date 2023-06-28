package Model.CE;

import Model.Clases.Cliente;
import Model.Clases.GrabadoraYLectoraArchivos;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import Model.Interfaces.IABM;

import java.util.*;

/**
 * Clase que envuelve todas los objetos de tipo Cliente almacenados en una coleccion HashMap. Dicha coleccion es manipulada por la interfaz IABM.
 *
 * @see HashMap
 * @see Cliente
 */
public class EnvoltoriaClientes implements IABM<Cliente> {

    //Atributos.
    private HashMap<Integer, Cliente> listaDeClientes;

    //Constructor.
    public EnvoltoriaClientes() {
        this.listaDeClientes = new HashMap<>();
    }

    //Metodos.

    /**
     * Añade un objeto de tipo Cliente a la colección.
     *
     * @param unCliente Objeto de tipo cliente.
     * @throws ElementNotLoadedException Lanza la excepcion si el cliente es nulo.
     * @see GrabadoraYLectoraArchivos Utilizado para leer y persistir los datos.
     */
    @Override
    public void agregar(Cliente unCliente) throws ElementNotLoadedException {
        if (unCliente != null) {
            listaDeClientes = GrabadoraYLectoraArchivos.leerClientes();
            listaDeClientes.put(unCliente.getDni(), unCliente);
            GrabadoraYLectoraArchivos.persistirClientes(listaDeClientes);
        } else {
            throw new ElementNotLoadedException("\nERROR - El cliente no tiene datos, o no existe.\n");
        }
    }

    /**
     * Elimina un objeto de tipo Cliente de la colección . Recorre y verifica si se cumple igualdad de atributo DNI para encontrar dicho cliente.
     *
     * @param dni Valor de referencia del cliente a eliminar.
     * @return true si encuentra y elimina, false si no lo encuentra.
     * @throws ElementNotFoundException Lanza la excepcion si no encuentra al cliente.
     * @see GrabadoraYLectoraArchivos Utilizado para leer y persistir los datos.
     * @see Iterator
     * @see Boolean
     */
    @Override
    public boolean eliminar(int dni) throws ElementNotFoundException {
        boolean rta = false;
        listaDeClientes = GrabadoraYLectoraArchivos.leerClientes();
        Iterator<Map.Entry<Integer, Cliente>> it = listaDeClientes.entrySet().iterator();
        int flag = 0;
        while (it.hasNext() && flag == 0) {
            Map.Entry<Integer, Cliente> entrada = it.next();
            Cliente aux = entrada.getValue();
            if (aux.getDni() == dni) {
                it.remove();
                flag = 1;
                rta = true;
            }
        }

        if (flag == 0) {
            throw new ElementNotFoundException("\nERROR - El cliente no fue encontrado o ya fue eliminado.\n");
        } else {
            GrabadoraYLectoraArchivos.persistirClientes(listaDeClientes);
        }

        return rta;
    }

    /**
     * Modifica la membresia VIP de un objeto de tipo Cliente de la colección.
     *
     * @param unCliente Cliente a modificar.
     * @param opcion    Valor para condicionar modificacion.
     * @throws ElementUnmodifiedException Lanza la excepcion si el cliente no pudo ser modificado.
     * @see Iterator
     */
    @Override
    public void modificar(Cliente unCliente, float opcion) throws ElementUnmodifiedException, ElementNotFoundException, ElementNotLoadedException {

        int opcionInt = (int) opcion;
        Iterator<Map.Entry<Integer, Cliente>> it = listaDeClientes.entrySet().iterator();
        if (unCliente != null) {
            Cliente aux = unCliente;
            eliminar(unCliente.getDni());

            if (opcionInt == 1) {
                aux.setEsVip(true);
            } else if (opcionInt == 2) {
                aux.setEsVip(false);
            }

            agregar(aux);

        } else {
            throw new ElementUnmodifiedException("\nERROR - El elemento no pudo ser modificado.\n");
        }
    }

    /**
     * Retorna la información de todos los objetos de tipo Cliente que se encuentren en la colección. Recorre y concatena un String con la información de cada uno.
     *
     * @return String
     * @see Iterator
     */
    @Override
    public String listar() {
        String aux = "";
        Iterator<Map.Entry<Integer, Cliente>> it = listaDeClientes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Cliente> entrada = it.next();
            Cliente nuevoCliente = entrada.getValue();
            aux = aux + "\n" + nuevoCliente.toString();
        }
        return aux;
    }

    /**
     * Recorre la coleccion para buscar si existe un determinado Cliente, si existe lo retorna.
     *
     * @param dni numero dni del Cliente.
     * @return Objeto Cliente si fue encontrado, sino retornara un Objeto nulo.
     * @throws ElementNotFoundException Lanza la excepcion si no encuentra al cliente.
     */
    public Cliente buscar(int dni) throws ElementNotFoundException {

        int flag = 0;
        Cliente aux = null;
        Iterator<Map.Entry<Integer, Cliente>> it = listaDeClientes.entrySet().iterator();
        while (it.hasNext() && flag == 0) {
            Map.Entry<Integer, Cliente> entrada = it.next();
            Cliente nuevo = entrada.getValue();
            if (nuevo.getDni() == dni) {
                flag = 1;
                aux = nuevo;
            }
        }

        if (aux == null) {
            throw new ElementNotFoundException("\nERROR - Cliente no encontrado.\n");
        }

        return aux;
    }

}
