package Model.Clases;

import java.io.Serializable;


/**
 * Clase que representa las personas clientes del sistema con sus propios atributos.
 */
public class Cliente implements Comparable, Serializable {

    //Atributos.
    private String nombre;
    private String apellido;
    private int dni;
    private boolean esVip;

    //Constructor.
    public Cliente() {
        this.nombre = "";
        this.apellido = "";
        this.dni = 0;
        this.esVip = false;
    }

    //Getters && Setters.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public boolean getEsVip() {
        return esVip;
    }

    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    //Metodos.

    /**
     * Compara si el cliente es igual a un objeto que llega por parametro.
     *
     * @param o Objeto a comparar con el cliente.
     * @return true si es el mismo cliente, false distinto.
     */
    @Override
    public boolean equals(Object o) {
        boolean rta = false;
        if (o != null) {
            if (o instanceof Cliente) {
                if (this.dni == ((Cliente) o).dni) {
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
     * Retorna la información completa del cliente.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "\n---------------------------------\n" + "Nombre del cliente: " + this.nombre + " " + this.apellido + "\nDNI: " + this.dni + "\nVIP: " + this.esVip + "\n---------------------------------";
    }

    /**
     * Compara si el cliente es menor, igual o mayor que un objeto que llega por parametro.
     *
     * @param o Objeto a comparar con el cliente.
     * @return 0 si son iguales, 1 si el cliente es mayor, -1 si el cliente es menor.
     */
    @Override
    public int compareTo(Object o) {
        int rta = 999;
        if (o != null) {
            if (o instanceof Cliente) {
                if (this.dni < ((Cliente) o).dni) {
                    rta = -1;
                } else if (this.dni > ((Cliente) o).dni) {
                    rta = 1;
                } else if (this.dni == ((Cliente) o).dni) {
                    rta = 0;
                }
            }
        }
        return rta;
    }
}
