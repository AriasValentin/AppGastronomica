package Model.Clases;

import Model.Clases.Producto.Producto;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa una venta particular, con sus productos añadidos a su carrito.
 *
 * @see ArrayList
 * @see LugarConsumo
 * @see Cliente
 */
public class Venta implements Serializable {

    //Atributos.
    private ArrayList<Producto> carrito;
    private float total;
    private float descuento;
    private LugarConsumo lugarConsumo;
    private Cliente UnCliente;
    private int numTicket;

    //Constructor.
    public Venta()
    {
        this.carrito = new ArrayList<>();
        this.total = 0;
        if(this.UnCliente != null && UnCliente.getEsVip() == true)
        {
            this.descuento = 10;
        }
        else
        {
            this.descuento = 0;
        }
        this.lugarConsumo = LugarConsumo.TakeAway;
        this.UnCliente = null;

    }

    //Getters && Setters.
    public void setTotal(float total) {
        this.total = total;
    }

    public int getNumTicket() {
        return numTicket;
    }

    public void setUnCliente(Cliente unCliente) {
        this.UnCliente = unCliente;
        if (UnCliente.getEsVip() == true) {
            this.descuento = 10;

        } else {
            this.descuento = 0;
        }
    }

    public void setNumTicket(int numTicket) {
        this.numTicket = numTicket;
    }

    public void setLugarConsumo(LugarConsumo lugarConsumo) {
        this.lugarConsumo = lugarConsumo;
    }

    //Metodos.

    /**
     * Calcula el precio final de la venta, iterando sobre los productos que dispone su coleccion carrito.
     *
     * @return Total de la venta.
     */
    public float PrecioFinalVenta() {

        for (int i = 0; i < carrito.size(); i++) {
            this.total = this.total + carrito.get(i).getPrecio();
        }

        this.total = this.total - ((this.total * this.descuento) / 100);

        return total;
    }

    /**
     * Retorna la informacion el numero de ticket, los productos y el total de la venta.
     *
     * @return String
     */
    public String listarVenta() {
        String productos = "";
        for (int i = 0; i < carrito.size(); i++) {
            productos = productos + "\n" + carrito.get(i).toStringSimple(); //acumula los nombres de los productos comprados
        }
        return "Venta: " + "\nnumTicket: " + numTicket + "\nProductos: "+"\n-------------------------------------------------------------------------------------------" + productos + "\nPrecio total: " + total + "\n-------------------------------------------------------------------------------------------\n";
    }

    /**
     * Retorna la informacion de los productos de la venta.
     *
     * @return String
     */
    public String listarVentaSinTicket() {
        String productos = "";
        for (int i = 0; i < carrito.size(); i++) {
            productos = productos + "\n" + carrito.get(i).toStringSimple(); //acumula los nombres de los productos comprados
        }
        return "Venta: " + "\nProductos: "+"\n-------------------------------------------------------------------------------------------" + productos + "-------------------------------------------------------------------------------------------\n";
    }

    /**
     * Retorna la informacion completa de la venta.
     * @return
     */
    public String listarVentaConCliente(){
        String productos = "";
        for (int i = 0; i < carrito.size(); i++) {
            productos = productos + "\n" + carrito.get(i).toStringSimple(); //acumula los nombres de los productos comprados
        }
        return "Venta: " + "\n---------------------------------" + "\nnumTicket: " + numTicket + "\n---------------------------------" + "\n\nCliente: " + UnCliente.toString() + "\n\nProductos: "+"\n-------------------------------------------------------------------------------------------" + productos + "\nPrecio total: " + total + "\n-------------------------------------------------------------------------------------------\n" + "\n---------------------------------" + "\nLugar de consumo: " + lugarConsumo + "\n---------------------------------\n";
    }

    /**
     * Agrega un objeto de tipo Producto a la coleccion carrito.
     *
     * @param aux Producto a agregar.
     */
    public void agregarProductosAlcarrito(Producto aux) throws ElementNotLoadedException {
        if(aux != null){
            carrito.add(aux);
        } else {
            throw new ElementNotLoadedException("\nERROR - El producto no pudo agregarse a su carrito.\n");
        }
    }
}
