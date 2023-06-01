package Model.Clases;

import java.util.Objects;

/**
 * Representa las personas en el sistema.
 */
public class Cliente implements Comparable {
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

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }

    public boolean isEsVip() {
        return esVip;
    }

    public void setEsVip(boolean esVip) {
        this.esVip = esVip;
    }

    /**
     * verifica si los dni de los clientes a comparar son iguales.
     * @param o Cliente
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
     *
     * @return los atributos de cliente.
     */
    @Override
    public String toString() {
        return "Nombre del cliente completo:" + this.nombre + " - " + this.apellido + " DNI : " + this.dni + " vip :" + this.esVip;
    }

    /**
     * Verifica si es distinto a null, luego si es un cliente, y finalmente si tienen el mismo dni, mayor o menor en su respectivo caso.
     * @param o objeto a comparar es un cliente.
     * @return  0 si son iguales - 1 si el primero es mayor.  -  -1 si el primero es menor. -  999 si es invalido.
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
