package Model.ExcepcionesPersonalizadas;

/**
 * Excepcion personalizada para avisar de un error de carga de un determinado dato.
 */
public class ElementNotLoadedException extends Exception{
    public ElementNotLoadedException(String message){
        super("SE CARGO MAL EL ELEMENTO.."+message);
    }
}
