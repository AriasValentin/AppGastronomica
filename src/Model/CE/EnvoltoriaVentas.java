package Model.CE;

import Model.Clases.Venta;
import Model.Interfaces.IABM;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Clase que envuelve todas los objetos de tipo Venta almacenados en una ArrayList manipulada por la interfaz implementada IABM.
 * @see ArrayList
 * @see Venta
 */

public class EnvoltoriaVentas implements IABM<Venta> {
    private ArrayList<Venta> listaDeVentas;

    public EnvoltoriaVentas() {
        this.listaDeVentas = new ArrayList<>();
    }

    /**
     * Añade un objeto de tipo Venta al ArrayList.
     * @param unaVenta Objeto de tipo Venta
     */
    @Override
    public void agregar(Venta unaVenta) {
        listaDeVentas.add(unaVenta);
    }

    /**
     * Elimina un objeto de tipo Venta al ArrayList. Utiliza el metodo buscarVenta para verificar si existe o no dicha venta. Si es asi, elimina de la lista.
     * @param numTicket numero de ticket de la venta
     * @return true si elimina, false si no.
     */
    @Override
    public boolean eliminar(int numTicket) {
        boolean rta = false;
        int indice = buscarVenta(numTicket);
        if(indice != -1)
        {
            listaDeVentas.remove(indice);
            rta = true;
        }
        return rta;
    }

    @Override
    public void modificar(int elemento) {

    }

    /**
     * Retorna la información de todos los objetos de tipo Venta que se encuentren en la lista. Recorre y concatena un String con la información.
     * @return String
     */
    @Override
    public String listar() {
        String aux = "";
        for(int i=0;i<listaDeVentas.size();i++)
        {
            aux = "\n" + listaDeVentas.get(i).toString();
        }

        return aux;
    }

    /**
     * Recorre la lista para buscar si existe una determinada venta
     * @param nroTicket numero de ticket de la venta
     * @return indice con la posicion de la venta, -1 si no existe.
     */
    public int buscarVenta(int nroTicket) {
        int i = 0, indice = -1;
        while (i < (listaDeVentas.size()-1) && listaDeVentas.get(i).getNumTicket() != nroTicket) {
            i++;
        }
        if(i< (listaDeVentas.size()-1) && listaDeVentas.get(i).getNumTicket() == nroTicket)
        {
            indice = i;
        }
        return indice;
    }
}
