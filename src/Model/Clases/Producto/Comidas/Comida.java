package Model.Clases.Producto.Comidas;

import Model.Clases.Producto.Bebidas.TipoBebida;
import Model.Clases.Producto.Producto;

/**
 * Clase Comida que extiende de la clase Producto, posee su atributo tipo de comida.
 *
 * @see TipoComida
 */
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
