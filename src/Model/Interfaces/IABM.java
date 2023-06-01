package Model.Interfaces;

public interface IABM <T>{
    void agregar(T elemento);
    boolean eliminar(int elemento);
    void modificar(int elemento);
    String listar();
}
