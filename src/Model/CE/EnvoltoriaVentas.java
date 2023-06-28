package Model.CE;

import Model.Clases.GrabadoraYLectoraArchivos;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import Model.Interfaces.IABM;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Clase que envuelve todas los objetos de tipo Venta almacenados en una coleccion ArrayList. Dicha coleccion es manipulada por la interfaz implementada IABM.
 *
 * @see ArrayList
 * @see Venta
 */

public class EnvoltoriaVentas implements IABM<Venta>, Serializable {

    //Atributos
    private ArrayList<Venta> listaDeVentas;
    private int indexNroTicket;

    //Constructor
    public EnvoltoriaVentas() {
        this.listaDeVentas = new ArrayList<>();
        this.indexNroTicket = 0;
    }

    //Getters.
    public int getIndexNroTicket() {
        return indexNroTicket;
    }

    //Metodos.

    /**
     * Añade un objeto de tipo Venta a la coleccion.
     *
     * @param unaVenta Objeto de tipo Venta.
     * @throws ElementNotLoadedException Lanza la excepcion si la venta no pudo ser agregada.
     * @see GrabadoraYLectoraArchivos Utilizada para leer y persistir los datos.
     */
    @Override
    public void agregar(Venta unaVenta) throws ElementNotLoadedException {

        if (unaVenta != null) {
            Venta aux = null;
            listaDeVentas = GrabadoraYLectoraArchivos.leerVentas();

            aux = buscarIndexMayor();

            if (aux != null) {
                unaVenta.setNumTicket(aux.getNumTicket() + 1);
            } else {
                unaVenta.setNumTicket(indexNroTicket);
            }

            listaDeVentas.add(unaVenta);
            indexNroTicket++;
            GrabadoraYLectoraArchivos.persistirVentas(listaDeVentas);
        } else {
            throw new ElementNotLoadedException("\nERROR - La venta no pudo ser cargada.\n");
        }
    }

    /**
     * Busca en la coleccion y retorna el objeto de tipo Venta que posea el numeor de ticket mayor.
     *
     * @return Objeto de tipo Venta.
     * @see Venta
     */
    public Venta buscarIndexMayor() {

        Venta aux = null;

        if (listaDeVentas.size() != 0) {
            aux = listaDeVentas.get(0);

            for (int i = 0; i < listaDeVentas.size(); i++) {
                if (aux.getNumTicket() < listaDeVentas.get(i).getNumTicket()) {
                    aux = listaDeVentas.get(i);
                }
            }
        }

        return aux;
    }

    /**
     * Elimina un objeto de tipo Venta de la coleccion. Utiliza el metodo buscarVenta para verificar si existe o no dicha venta. Si es asi, elimina de la lista.
     *
     * @param numTicket numero de ticket de la venta.
     * @return true si elimina la venta, false si no.
     * @throws ElementNotFoundException Lanza la excepcion si no se encuentra la venta.
     * @see GrabadoraYLectoraArchivos Utilizado para leer y persistir los datos.
     */
    @Override
    public boolean eliminar(int numTicket) throws ElementNotFoundException {
        boolean rta = false;
        listaDeVentas = GrabadoraYLectoraArchivos.leerVentas();

        try {
            Venta aux = buscar(numTicket);

            if (aux != null) {
                listaDeVentas.remove(aux);
                rta = true;

            } else {
                throw new ElementNotFoundException("\nERROR - El producto no fue encontrado o ya fue eliminado.\n");
            }

        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }

        GrabadoraYLectoraArchivos.persistirVentas(listaDeVentas);

        return rta;
    }

    /**
     * Modifica el atributo  total de un objeto de tipo Venta de la coleccion.
     *
     * @param unaVenta Venta a modificar.
     * @param nuevoTotal Valor del total que se modifica en la venta.
     * @throws ElementUnmodifiedException Lanza la excepcion si la venta no se pudo modificar.
     * @throws ElementNotFoundException   Si no se elimina la venta con su precio original a la coleccion.
     * @throws ElementNotLoadedException  Si no se pudo agregar la venta modificada a la coleccion.
     * @see GrabadoraYLectoraArchivos Utilizada para persistir los datos.
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

        GrabadoraYLectoraArchivos.persistirVentas(listaDeVentas);
    }

    /**
     * Retorna la información de todos los objetos de tipo Venta que se encuentren en la lista. Recorre y concatena un String con la información.
     *
     * @return String
     * @see GrabadoraYLectoraArchivos Utilizada para leer los datos.
     */
    @Override
    public String listar() {
        String aux = "";

        ArrayList<Venta> listaAux = GrabadoraYLectoraArchivos.leerVentas();

        for (int i = 0; i < listaAux.size(); i++) {
            aux += "\n" + listaAux.get(i).listarVenta();
        }

        return aux;
    }

    /**
     * Recorre la coleccion de tipo Ventas y retorna una venta en especifico por medio de un numero de ticket.
     *
     * @param nroTicket Valor de referencia para buscar la venta.
     * @return un objeto de tipo Venta si se encuentra, sino retorna un objeto nulo.
     * @throws ElementNotFoundException Lanza la excepcion si no se encuentra la venta.
     * @see GrabadoraYLectoraArchivos Utilizada para leer los datos.
     */

    @Override
    public Venta buscar(int nroTicket) throws ElementNotFoundException {

        listaDeVentas = GrabadoraYLectoraArchivos.leerVentas();
        int i = 0, indice = -1;
        Venta aux = null;

        while (i < listaDeVentas.size() && listaDeVentas.get(i).getNumTicket() != nroTicket) {
            i++;
        }

        if (i < listaDeVentas.size() && listaDeVentas.get(i).getNumTicket() == nroTicket) {
            indice = i;
            aux = listaDeVentas.get(i);
        } else {
            throw new ElementNotFoundException("\nERROR - La venta no fue encontrada.\n");
        }

        return aux;
    }
}


