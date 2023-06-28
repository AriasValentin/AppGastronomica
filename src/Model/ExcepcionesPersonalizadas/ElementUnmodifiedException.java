package Model.ExcepcionesPersonalizadas;

/**
 * Excepcion personalizada para avisar de un error de carga de un determinado dato.
 */
public class ElementUnmodifiedException extends Exception{
    public ElementUnmodifiedException(String message){
        super("ELEMENTO NO SE PUDO MODIFICAR.."+message);
    }
}
