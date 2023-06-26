package Model.Clases;

import Model.Clases.Producto.Producto;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Venta particular, con su lista de productos añadidos a su carrito.
 */
public class Venta implements Serializable {

    private ArrayList<Producto> carrito;
    private float total;
    private float descuento;
    private LugarConsumo lugarConsumo;
    private Cliente UnCliente;
    private int numTicket;


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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getNumTicket() {
        return numTicket;
    }

    public float getDescuento() {
        return descuento;
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

    public LugarConsumo getLugarConsumo() {
        return lugarConsumo;
    }

    public void setLugarConsumo(LugarConsumo lugarConsumo) {
        this.lugarConsumo = lugarConsumo;
    }

    /**
     * calcula el precio final de la venta.
     *
     * @return El precio final.
     */
    public float PrecioFinalVenta() {

        for (int i = 0; i < carrito.size(); i++) {
            this.total = this.total + carrito.get(i).getPrecio();
        }

        this.total = this.total - ((this.total * this.descuento) / 100);

        return total;
    }

    // lista todo los productos que lleva mas los atributos propios de esa venta.
    public String listarVenta() {
        String productos = "";
        for (int i = 0; i < carrito.size(); i++) {
            productos = productos + "\n" + carrito.get(i).toStringSimple(); //acumula los nombres de los productos comprados
        }
        return "Venta: " + "\nnumTicket: " + numTicket + "\nProductos: "+"\n-------------------------------------------------------------------------------------------" + productos + "\nPrecio total: " + total + "\n-------------------------------------------------------------------------------------------\n";
    }

    public String listarVentaSinTicket() {
        String productos = "";
        for (int i = 0; i < carrito.size(); i++) {
            productos = productos + "\n" + carrito.get(i).toStringSimple(); //acumula los nombres de los productos comprados
        }
        return "Venta: " + "\nProductos: "+"\n-------------------------------------------------------------------------------------------" + productos + "-------------------------------------------------------------------------------------------\n";
    }

    public String listarVentaConCliente(){
        String productos = "";
        for (int i = 0; i < carrito.size(); i++) {
            productos = productos + "\n" + carrito.get(i).toStringSimple(); //acumula los nombres de los productos comprados
        }
        return "Venta: " + "\n---------------------------------" + "\nnumTicket: " + numTicket + "\n---------------------------------" + "\n\nCliente: " + UnCliente.toString() + "\n\nProductos: "+"\n-------------------------------------------------------------------------------------------" + productos + "\nPrecio total: " + total + "\n-------------------------------------------------------------------------------------------\n" + "\n---------------------------------" + "\nLugar de consumo: " + lugarConsumo + "\n---------------------------------\n";
    }

    /**
     * agrega un objeto de tipo Producto a la lista carrito
     *
     * @param aux Producto.
     */
    public void agregarProductosAlcarrito(Producto aux) throws ElementNotLoadedException {
        if(aux != null){
            carrito.add(aux);
        } else {
            throw new ElementNotLoadedException("\nERROR - El producto no pudo agregarse a su carrito.\n");
        }
    }

    /**
     * elimina por medio de un indice un producto de la lista carrito
     *
     * @param index int.
     */
    public void eliminarProductoDelcarrito(int index) throws ElementNotFoundException {

        if(index >= 0){
            carrito.remove(index);
        }else {
            throw new ElementNotFoundException("\nERROR - El producto no fue encontrado o ya fue eliminado.\n");
        }
    }
}
