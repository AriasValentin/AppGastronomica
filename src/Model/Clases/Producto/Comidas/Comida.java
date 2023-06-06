package Model.Clases.Producto.Comidas;

import Model.Clases.Producto.Producto;

public class Comida extends Producto {

    private TipoComida tipoComida;

    public Comida() {
        super();
        this.tipoComida = null;
    }

    public Comida(String nombre, float precio, int id, String descripcion, TipoComida tipoComida) {
        super(nombre, precio, id, descripcion);
        this.tipoComida = tipoComida;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
