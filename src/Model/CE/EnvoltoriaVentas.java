package Model.CE;

import Model.Clases.Producto.Producto;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import Model.Interfaces.IABM;
import jdk.jfr.EventType;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase que envuelve todas los objetos de tipo Venta almacenados en una ArrayList manipulada por la interfaz implementada IABM.
 *
 * @see ArrayList
 * @see Venta
 */

public class EnvoltoriaVentas implements IABM<Venta> {

    Scanner scan = new Scanner(System.in);

    //atributos
    private ArrayList<Venta> listaDeVentas;
    private int indexNroTicket;

    //constructor
    public EnvoltoriaVentas() {
        this.listaDeVentas = new ArrayList<>();
        this.indexNroTicket = 0;
    }


    /**
     * Añade un objeto de tipo Venta al ArrayList.
     *
     * @param unaVenta Objeto de tipo Venta
     */
    @Override
    public void agregar(Venta unaVenta) throws ElementNotLoadedException {

        if (unaVenta != null) {
            listaDeVentas.add(unaVenta);
            this.indexNroTicket++;
        } else {
            throw new ElementNotLoadedException("\nERROR - La venta no pudo ser cargada.\n");
        }
    }

    /**
     * Elimina un objeto de tipo Venta al ArrayList. Utiliza el metodo buscarVenta para verificar si existe o no dicha venta. Si es asi, elimina de la lista.
     *
     * @param numTicket numero de ticket de la venta
     * @return true si elimina, false si no.
     */
    @Override
    public boolean eliminar(int numTicket) throws ElementNotFoundException{
        boolean rta = false;

        try {
            Venta aux = buscar(numTicket);

            if ((aux.getNumTicket() == numTicket) && (aux != null)) {
                listaDeVentas.remove(aux);
                rta = true;
            }else {
                throw new ElementNotFoundException("\nERROR - El producto no fue encontrado o ya fue eliminado.\n");
            }

        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return rta;
    }

    /**
     * Modifica el atributo valor de una venta especifica que exista.
     *
     * @param unaVenta
     * @param nuevoTotal
     * @throws ElementUnmodifiedException
     */

    @Override
    public void modificar(Venta unaVenta, float nuevoTotal) throws ElementUnmodifiedException, ElementNotLoadedException, ElementNotFoundException {

        if (unaVenta != null) {
            Venta aux = unaVenta;
            eliminar(unaVenta.getNumTicket());
            aux.setTotal(nuevoTotal);
            agregar(aux);
        } else {
            throw new ElementUnmodifiedException("\nERROR - El elemento no pudo ser modificado.\n");
        }
    }

    /**
     * Retorna la información de todos los objetos de tipo Venta que se encuentren en la lista. Recorre y concatena un String con la información.
     *
     * @return String
     */
    @Override
    public String listar() {
        String aux = "";

        for (int i = 0; i < listaDeVentas.size() -1; i++) {
            aux = "\n" + listaDeVentas.get(i).listarVenta();
        }

        return aux;
    }

    /**
     * Recorre la lista de ventas y retorna una venta en especifico por medio de un numero de ticket.
     *
     * @param nroTicket numero de ticket de la venta
     * @return un objeto de tipo Venta si se encuentra, sino retorna un objeto nulo.
     */


    @Override
    public Venta buscar(int nroTicket) throws ElementNotFoundException {

        int i = 0, indice = -1;
        Venta aux = null;

        while (i < (listaDeVentas.size() - 1) && listaDeVentas.get(i).getNumTicket() != nroTicket) {
            i++;
        }

        if (i < (listaDeVentas.size() - 1) && listaDeVentas.get(i).getNumTicket() == nroTicket) {
            indice = i;
            aux = listaDeVentas.get(i);
        } else {
            throw new ElementNotFoundException("\nERROR - La venta no fue encontrada.\n");
        }

        return aux;
    }

    @Override
    public Iterator<Venta> devolverIterador() {
        Iterator<Venta> it = listaDeVentas.iterator();
        return it;
    }
}


