package Model.Interfaces;

import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;

/**
 * Interfaz generica para colecciones con metodos agregar, eliminar, modificar, buscar y listar.
 *
 * @param <T> Clase
 */
public interface IABM<T> {
    void agregar(T elemento) throws ElementNotLoadedException;

    boolean eliminar(int elemento) throws ElementNotFoundException;

    void modificar(T elemento, float numero) throws ElementUnmodifiedException, ElementNotFoundException, ElementNotLoadedException;

    T buscar(int elemento) throws ElementNotFoundException;

    String listar();
}
