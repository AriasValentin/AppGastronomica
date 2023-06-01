package Model.CE;

import Model.Clases.Venta;
import Model.Interfaces.IABM;

import java.util.ArrayList;

public class EnvoltoriaVentas implements IABM<Venta> {
    private ArrayList<Venta> listaDeVentas;

    public EnvoltoriaVentas() {
        this.listaDeVentas = new ArrayList<>();
    }

    @Override
    public void agregar(Venta elemento) {
        listaDeVentas.add(elemento);
    }

    @Override
    public boolean eliminar(int elemento) {
    }

    @Override
    public void modificar(int elemento) {

    }

    @Override
    public String listar() {
        String aux = "";

        return aux;
    }

    public int buscarVenta(int nroTicket) {
        int i = 0;
        while (i < listaDeVentas.size() - 1 && listaDeVentas.get(i).getNumTicket() != nroTicket) {
            i++;
        }

    }
}
