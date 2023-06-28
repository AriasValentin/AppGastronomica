package Model.Clases.Producto.Bebidas;

import Model.Clases.Producto.Producto;

/**
 * Clase Bebida que extiende de la clase Producto, posee su atributo tipo de bebida.
 *
 * @see TipoBebida
 */
public class Bebida extends Producto {

    //Atributos
    private TipoBebida tipoBebida;

    //Constructor
    public Bebida() {
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

    /**
     * Retorna la informacion de la clase padre Producto por medio de la llamada super.
     *
     * @return String
     */
    @Override
    public String toString() {
        return super.toString();
    }


}
