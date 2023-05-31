package Model.Clases;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Nombre del cliente completo:" + this.nombre + " - " + this.apellido + " DNI : " + this.dni + " vip :" + this.esVip;
    }

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
