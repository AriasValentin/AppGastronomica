package Model.ExcepcionesPersonalizadas;

/**
 * Excepcion personalizada para avisar de un error de busqueda en una coleccion de clientes.
 */
public class ElementNotFoundException extends Exception{
    public ElementNotFoundException(String message){
        super(message);
    }
}
