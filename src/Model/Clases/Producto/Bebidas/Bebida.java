package Model.Clases.Producto.Bebidas;

import Model.Clases.Producto.Producto;

import java.util.ArrayList;

public  class Bebida extends Producto {
    private String descripcion;

    public Bebida()
    {
        super();

    }
    public Bebida(String nombre, float precio, int id, String descripcion) {
        super(nombre, precio, id);
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return super.toString()+ "Acerca de : " +descripcion;
    }
}
