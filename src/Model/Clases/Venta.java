package Model.Clases;

import Model.Interfaces.IABM;

import java.util.ArrayList;

public class Venta {

    private ArrayList<Producto> carrito;
    private float descuento;

    //private LugarConsumo consumo;
    private Cliente UnCliente;

    private int numTicket;

    public Venta(ArrayList<Producto> carrito, Cliente unCliente, int numTicket) {
        this.carrito = carrito;
        UnCliente = unCliente;
        this.numTicket = numTicket;

        //consultar esto!
        if(UnCliente.isEsVip() == true)
        {
            this.descuento = 15;

        }
        else
        {
            this.descuento = 0;
        }

    }

    public int getNumTicket() {
        return numTicket;
    }

    public float getDescuento() {
        return descuento;
    }

    public float PrecioFinalVenta()
    {
        float total = 0;
        for(int i=0;i<carrito.size();i++)
        {
            total = total + carrito.get(i).getPrecio();
        }

        total = total-((total*descuento)/100);
        return total;
    }

    public String listarCarrito()
    {
        String productos = "";
        for(int i=0;i<carrito.size();i++)
        {
            productos = productos + carrito.get(i).toString()+ "\n"; //acumula los nombres de los productos comprados
        }

        return "Venta: N°Ticket:"+this.numTicket+ ", Productos: "+productos+ ", Precio: "+PrecioFinalVenta();
    }
}
