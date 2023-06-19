package Model.CE;

import Model.Clases.Cliente;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import Model.Interfaces.IABM;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Clase que envuelve todas los objetos de tipo Cliente almacenados en una coleccion de tipo LinkedHashSet. Dicha coleecion es manipulada por la interfaz implementada IABM.
 *
 * @see LinkedHashSet
 * @see Cliente
 */

public class EnvoltoriaClientes implements IABM<Cliente> {

    private LinkedHashSet<Cliente> listaDeClientes;

    public EnvoltoriaClientes() {
        this.listaDeClientes = new LinkedHashSet<>();
    }

    /**
     * Añade un objeto de tipo Cliente a la LinkedHashSet.
     *
     * @param unCliente Objeto de tipo cliente.
     *                  Lanza una excepcion si el cliente es nulo.
     */
    @Override
    public void agregar(Cliente unCliente) throws ElementNotLoadedException {
        if (unCliente != null) {
            listaDeClientes.add(unCliente);
        } else {
            throw new ElementNotLoadedException("\nERROR - El cliente no tiene datos, o no existe.\n");
        }
    }

    /**
     * Elimina un objeto de tipo Cliente a la LinkedHashSet. Recorre con un iterador la collecion y verifica si se cumple igualdad de atributo DNI para encontrar dicho cliente.
     *
     * @param dni
     * @return true si encuentra y elimina, false si no lo encuentra.
     * Lanza una excepcion personalizada si no encuentra al cliente.
     * @see Iterator
     * @see Boolean
     */
    @Override
    public boolean eliminar(int dni) throws ElementNotFoundException {
        boolean rta = false;
        Iterator<Cliente> it = listaDeClientes.iterator();
        int flag = 0;
        while (it.hasNext() && flag == 0) {
            Cliente aux = (Cliente) it.next();
            if (aux.getDni() == dni) {
                it.remove();
                flag = 1;
                rta = true;
            }
        }

        if (flag == 0) {
            throw new ElementNotFoundException("\nERROR - El cliente no fue encontrado o ya fue eliminado.\n");
        }

        return rta;
    }

    /**
     * Modifica el valor VIP del cliente.
     * @param dni
     * @throws ElementUnmodifiedException
     */
    @Override
    public void modificar(int dni) throws ElementUnmodifiedException {

    }
    public void modificar()
    {

    }

    /**
     * Retorna la información de todos los objetos de tipo Cliente que se encuentren en la colección. Recorre y concatena un String con la información.
     *
     * @return String
     * @see Iterator
     */
    @Override
    public String listar() {
        String aux = "";
        Iterator<Cliente> it = listaDeClientes.iterator();
        while (it.hasNext()) {
            Cliente nuevoCliente = (Cliente) it.next();
            aux = aux + "\n" + nuevoCliente.toString();
        }
        return aux;
    }

    /**
     * Recorre la coleccion para buscar si existe un determinado Cliente, si existe lo retorna.
     * Lanza una excepcion personalizada si no encuentra al cliente.
     *
     * @param dni numero dni del Cliente.
     * @return Objeto Cliente si fue encontrado, sino retornara un Objeto nulo.
     */
    public Cliente buscar(int dni) throws ElementNotFoundException {

        Iterator<Cliente> it = listaDeClientes.iterator();

        int flag = 0;
        Cliente aux = null;

        while (it.hasNext() && flag == 0) {
            Cliente nuevo = (Cliente) it.next();
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
