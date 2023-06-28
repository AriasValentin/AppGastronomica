package Model.ExcepcionesPersonalizadas;

/**
 * Excepcion personalizada para avisar de un error de busqueda de un determinado dato.
 */
public class ElementNotFoundException extends Exception{
    public ElementNotFoundException(String message){
        super(message);
    }
}
