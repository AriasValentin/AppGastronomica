package Model.Clases.Producto.Bebidas;

import Model.Clases.Producto.Producto;

import java.util.ArrayList;

public  class Bebida extends Producto {

    private TipoBebida tipoBebida;
    public Bebida()
    {
        super();
        this.tipoBebida = null;
    }
    public Bebida(String nombre, float precio, int id, String descripcion) {
        super(nombre, precio, id, descripcion);
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }


    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }


    @Override
    public String toString() {
        return super.toString();
    }


}
