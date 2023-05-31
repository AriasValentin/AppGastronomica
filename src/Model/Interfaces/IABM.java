package Model.Interfaces;

public interface IABM <T>{
    boolean agregar(T elemento);
    boolean eliminar(int elemento);
    void modificar(int elemento);

}
