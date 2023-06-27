package Model.Clases.Producto.Bebidas;

import Model.Clases.Producto.Producto;


public  class Bebida extends Producto {

    //Atributos
    private TipoBebida tipoBebida;

    //Constructor
    public Bebida()
    {
        super();
        this.tipoBebida = null;
    }

    //Getters && Setters
    public TipoBebida getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(TipoBebida tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    //Metodos
    @Override
    public String toString() {
        return super.toString();
    }


}
