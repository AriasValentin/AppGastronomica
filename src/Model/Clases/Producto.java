package Model.Clases;

import java.util.Objects;

/**
 * Representa los productos para la venta del sistema.
 */

public abstract class Producto implements Comparable {
    private String nombre;
    private float precio;
    private int id;

    public Producto(String nombre, float precio, int id) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    /**
     * compara el producto con un objeto
     * @param o Objeto a comparar con Producto.
     * @return true si son el mismo objeto, false distinto.
     */
    @Override
    public boolean equals(Object o) {
        boolean rta = false;
        if (o != null) {
            if (o instanceof Producto) {
                if (this.id == ((Producto) o).id && this.nombre.equals(((Producto) o).getNombre())) {
                    rta = true;
                }

            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Nombre del producto : " + this.nombre + " Precio : " + this.precio + "Id: " + this.id;
    }

    /**
     * Compara si un objeto es mayor menor o igual al otro objeto.
     * @param o Produto
     * @return 0 si son el mismo id, -1 si el primero es menor, 1 si el primero es mayor.
     */
    @Override
    public int compareTo(Object o) {
        int rta = 99999;
        if (o != null) {
            if (o instanceof Producto) {
                if (this.id == ((Producto) o).id ) {
                    rta = 0;
                }
                if (this.id < ((Producto) o).id) {
                    rta = -1;
                }
                if (this.id > ((Producto) o).id) {
                    rta = 1;
                }
            }
        }

        return rta;
    }
}
