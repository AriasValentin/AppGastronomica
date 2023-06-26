package Model.Clases;

import java.io.Serializable;


/**
 * Clase que representa las personas clientes en el sistema.
 */
public class Cliente implements Comparable, Serializable {
    private String nombre;
    private String apellido;
    private int dni;
    private boolean esVip;

    public Cliente(String nombre, String apellido, int dni, boolean esVip) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.esVip = esVip;
    }

    public Cliente() {
        this.nombre = "";
        this.apellido = "";
        this.dni = 0;
        this.esVip = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
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

    /**
     * verifica si los dni de los clientes a comparar son iguales.
     * @param o Objeto a comparar con cliente.
     * @return true si es la misma persona.
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
     *Retorna la información del Cliente
     * @return String
     */
    @Override
    public String toString() {
        return "\n---------------------------------\n" + "Nombre del cliente: " + this.nombre + " " + this.apellido + "\nDNI: " + this.dni + "\nVIP: " + this.esVip + "\n---------------------------------";
    }

    /**
     * Verifica si es distinto a null, luego si es un cliente, y finalmente si tienen el mismo dni, mayor o menor en su respectivo caso.
     * @param o Objeto a comparar con cliente.
     * @return  0 si son iguales, 1 si el primero es mayor, -1 si el primero es menor, 999 si es invalido.
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
