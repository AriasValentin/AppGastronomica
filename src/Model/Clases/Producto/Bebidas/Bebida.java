package Model.Clases.Producto.Bebidas;

import Model.Clases.Producto.Producto;

import java.util.ArrayList;

public  class Bebida extends Producto {
    private String descripcion;
    private TipoBebida tipoBebida;
    public Bebida()
    {
        super();
        this.descripcion = "";
        this.tipoBebida = null;
    }
    public Bebida(String nombre, float precio, int id, String descripcion) {
        super(nombre, precio, id);
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    @Override
    public String toString() {
        return super.toString()+ " \n Acerca de : " +descripcion;
    }
}
