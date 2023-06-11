package Model.ExcepcionesPersonalizadas;

/**
 * Excepcion personalizada para avisar de un error de carga en una coleccion de clientes.
 */
public class ElementNotLoadedException extends Exception{
    public ElementNotLoadedException(String message){
        super(message);
    }
}
