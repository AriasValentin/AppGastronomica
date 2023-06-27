package Model.Clases.Producto.Comidas;

import Model.Clases.Producto.Producto;

public class Comida extends Producto {

    //Atributos.
    private TipoComida tipoComida;

    //Constructor.
    public Comida() {
        super();
        this.tipoComida = null;
    }

    //Getters && Setters.
    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    //Metodos.
    @Override
    public String toString() {
        return super.toString();
    }
}
