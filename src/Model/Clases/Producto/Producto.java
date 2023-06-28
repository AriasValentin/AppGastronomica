package Model.Clases.Producto;

import java.io.Serializable;

/**
 * Clase que representa los productos para la venta del sistema con sus propios atributos.
 *
 * @see String
 */

public abstract class Producto implements Comparable, Serializable {

    //Atributos.
    private String nombre;
    private float precio;
    private int id;
    private String descripcion;

    //Constructor.
    public Producto() {
        this.nombre = "";
        this.precio = 0;
        this.id = 0;
        this.descripcion = "";
    }

    //Getters && Setters.
    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Metodos.

    /**
     * Compara si el producto es igual a un objeto que llega por parametro.
     *
     * @param o Objeto a comparar con el producto.
     * @return true si son el mismo producto, false distinto.
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

    /**
     * Retorna la informacion completa del producto.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                "Nombre del producto: " + this.nombre +
                "\nPrecio: " + this.precio +
                "\nId: " + this.id +
                "\nDescripcion: " + this.descripcion +
                "\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
    }

    /**
     * Retorna la informacion de nombre y precio del producto.
     *
     * @return String
     */
    public String toStringSimple() {
        return "Nombre del producto: " + this.nombre +
                "\nPrecio: " + this.precio + "\n";
    }

    /**
     * Retorna la informacion del nombre e ID del producto
     *
     * @return String
     */
    public String toStringNombreID() {
        return "----------------------------------------------------------------" +
                "\nNombre del producto: " + this.nombre +
                "\nID : " + this.id +
                "\n----------------------------------------------------------------";
    }

    /**
     * Compara si el producto es menor, igual o mayor que un objeto que llega por parametro.
     *
     * @param o Objeto a comparar con el producto.
     * @return 0 si son iguales, 1 si el producto es mayor, -1 si el producto es menor.
     */
    @Override
    public int compareTo(Object o) {
        int rta = 99999;
        if (o != null) {
            if (o instanceof Producto) {
                if (this.id == ((Producto) o).id) {
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
