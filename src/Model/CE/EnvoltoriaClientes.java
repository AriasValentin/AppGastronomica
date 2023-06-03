package Model.CE;
import Model.Clases.Cliente;
import Model.Interfaces.IABM;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Clase que envuelve todas los objetos de tipo Cliente almacenados en una coleccion de tipo LinkedHashSet. Dicha coleecion es manipulada por la interfaz implementada IABM.
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
     * @param unCliente Objeto de tipo cliente
     */
    @Override
    public void agregar(Cliente unCliente) {
        listaDeClientes.add(unCliente);
    }

    /**
     * Elimina un objeto de tipo Cliente a la LinkedHashSet. Recorre con un iterador la collecion y verifica si se cumple igualdad de atributo DNI para encontrar dicho cliente.
     * @param dni
     * @see Iterator
     * @see Boolean
     * @return true si encuentra y elimina, false si no lo encuentra.
     */
    @Override
    public boolean eliminar(int dni) {
        boolean rta = false;
        Iterator<Cliente> it = listaDeClientes.iterator();
        int flag = 0;
        while(it.hasNext() && flag == 0)
        {
            Cliente aux = (Cliente) it.next();
            if(aux.getDni() == dni)
            {
                it.remove();
                flag = 1;
                rta = true;
            }
        }
        return rta;
    }

    @Override
    public void modificar(int dni) {

    }

    /**
     * Retorna la información de todos los objetos de tipo Cliente que se encuentren en la colección. Recorre y concatena un String con la información.
     * @see Iterator
     * @return String
     */
    @Override
    public String listar() {
        String aux = "";
        Iterator<Cliente> it = listaDeClientes.iterator();
        while(it.hasNext())
        {
            Cliente nuevoCliente = (Cliente) it.next();
            aux = aux + "\n" + nuevoCliente.toString();
        }
        return aux;
    }

    /**
     * Recorre la coleccion para buscar si existe un determinado Cliente.
     * @param dni numero dni del Cliente.
     * @return true si encuentra el cliente, false si no.
     */
    public boolean buscar(int dni)
    {
        boolean rta = false;
        Iterator<Cliente> it = listaDeClientes.iterator();
        int flag = 0;
        while(it.hasNext() && flag == 0)
        {
            Cliente nuevo = (Cliente) it.next();
            if(nuevo.getDni() == dni)
            {
                flag = 1;
                rta = true;
            }
        }
        return rta;
    }
}
