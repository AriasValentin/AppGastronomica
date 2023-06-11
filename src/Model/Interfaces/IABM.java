package Model.Interfaces;

import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;

public interface IABM <T>{
    void agregar(T elemento) throws ElementNotLoadedException;
    boolean eliminar(int elemento) throws ElementNotFoundException;
    void modificar(int elemento) throws ElementUnmodifiedException;
    String listar();
}
