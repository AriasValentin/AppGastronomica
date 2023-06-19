package Model.ExcepcionesPersonalizadas;

/**
 * Excepcion personalizada para avisar de un error de modificacion de un cliente particular.
 */
public class ElementUnmodifiedException extends Exception{
    public ElementUnmodifiedException(String message){
        super("ELEMENTO NO SE PUDO MODIFICAR.."+message);
    }
}
