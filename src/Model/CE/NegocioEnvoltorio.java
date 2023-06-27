package Model.CE;

import Model.Clases.Cliente;
import Model.Clases.LugarConsumo;
import Model.Clases.Producto.Bebidas.Bebida;
import Model.Clases.Producto.Bebidas.TipoBebida;
import Model.Clases.Producto.Comidas.Comida;
import Model.Clases.Producto.Comidas.TipoComida;
import Model.Clases.Producto.Producto;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import org.json.JSONException;

public class NegocioEnvoltorio {

    //Atributos.
    private EnvoltoriaVentas lista_ventas;
    private EnvoltoriaProductos lista_productos;
    private EnvoltoriaClientes lista_clientes;

    //Constructor.
    public NegocioEnvoltorio() {
        lista_ventas = new EnvoltoriaVentas();
        lista_productos = new EnvoltoriaProductos();
        lista_clientes = new EnvoltoriaClientes();
        lista_productos.consumoJSON(); //consumo de productos
    }

    //METODOS VENTA

    public String listarVentas() {
        String cadena = "";
        cadena = lista_ventas.listar();
        return cadena;
    }

    public void guardarVentas(Venta unaVenta) throws ElementNotLoadedException {

        if (unaVenta != null) {
            lista_ventas.agregar(unaVenta);
        } else {
            throw new ElementNotLoadedException("\nERROR - La venta no pudo ser cargada.\n");
        }
    }

    public String lugarAConsumir(Venta unaVenta, int opcion) {

        String aux = "";

        switch (opcion) {
            case 1: {
                unaVenta.setLugarConsumo(LugarConsumo.MESA);
                break;
            }

            case 2: {
                unaVenta.setLugarConsumo(LugarConsumo.BARRA);
                break;
            }

            case 3: {
                unaVenta.setLugarConsumo(LugarConsumo.TakeAway);
                break;
            }

            default: {
                aux = "\nERROR - Opcion no valida.\n";
            }
        }
        return aux;
    }

    public boolean eliminarUnaVenta(int nroTicket) throws ElementNotFoundException {
        return lista_ventas.eliminar(nroTicket);
    }

    public boolean modificarUnaVenta(Venta unaVenta, float nuevoTotal) throws ElementNotFoundException, ElementUnmodifiedException, ElementNotLoadedException {

        boolean retorno = false;

        if (unaVenta != null){
            lista_ventas.modificar(unaVenta, nuevoTotal);
            retorno = true;
        }

        return retorno;
    }

    public Venta buscarVenta(int nroTicket) throws ElementNotFoundException {
        return lista_ventas.buscar(nroTicket);
    }

    public int numeroTicket() {
        return lista_ventas.getIndexNroTicket();
    }

    //METODOS PRODUCTOS.

    public Producto buscarProducto(int id) throws ElementNotFoundException {
        return lista_productos.buscar(id);
    }

    public String cartaProductos(int opcion) {
        String aux = "";

        switch (opcion) {
            case 1: {
                aux = lista_productos.listarComidasDulces();
                break;
            }

            case 2: {
                aux = lista_productos.listarComidasSaladas();
                break;
            }

            case 3: {
                aux = lista_productos.listarBebidasFrias();
                break;
            }

            case 4: {
                aux = lista_productos.listarBebidasCalientes();
                break;
            }

            case 5: {
                aux = lista_productos.listar();
                break;
            }
        }

        return aux;
    }

    public String listarProductosNombreID(){
        String aux = "";

        aux = lista_productos.listarNombreID();

        return aux;
    }

    public void agregarProducto(Producto unProducto) throws JSONException {
        if (unProducto != null){
            if (unProducto instanceof Comida){
                lista_productos.agregarComidaAJson((Comida) unProducto);
            }

            if (unProducto instanceof Bebida){
                lista_productos.agregarBebidaAJson((Bebida) unProducto);
            }
        }

        //vuelvo a leer los archivos actualizados
        lista_productos.vaciarListaProductos();
        lista_productos.consumoJSON();
    }

    public void eliminarProducto(Producto unProducto) throws JSONException {

        if (unProducto != null){
            if (unProducto instanceof Comida){
                lista_productos.eliminarComidaAJson((Comida) unProducto);
            }

            if (unProducto instanceof Bebida){
                lista_productos.eliminarBebidaAJson((Bebida) unProducto);
            }
        }

        //vuelvo a leer los archivos actualizados
        lista_productos.vaciarListaProductos();
        lista_productos.consumoJSON();
    }

    //METODOS CLIENTE

    public String guardarCliente(Cliente unCliente) {

        String aux = "";

        try {

            lista_clientes.agregar(unCliente);

        } catch (ElementNotLoadedException e) {
            aux = e.getMessage();
        }

        return aux;
    }

    public Cliente buscarClienteExistente(int dni) throws ElementNotFoundException {
        return lista_clientes.buscar(dni);
    }

    public String listarClientes() {
        return lista_clientes.listar();
    }


    public Cliente ClienteDefault() {

        Cliente unCliente = new Cliente();

        unCliente.setNombre("n/a");
        unCliente.setApellido("n/a");
        unCliente.setDni(0);
        unCliente.setEsVip(false);

        try {
            lista_clientes.agregar(unCliente);
        } catch (ElementNotLoadedException e) {
            System.out.println(e.getMessage());
        }

        return unCliente;
    }

    public boolean eliminarUnCliente(int dni) throws ElementNotFoundException {
        boolean rta = false;
        Cliente aux = buscarClienteExistente(dni);
        if (aux != null) {
            lista_clientes.eliminar(aux.getDni());
            rta = true;
        }
        return rta;
    }

    public void modificarUnCliente(Cliente unCliente, int opcion) throws ElementUnmodifiedException, ElementNotFoundException, ElementNotLoadedException {
        lista_clientes.modificar(unCliente, opcion);
    }
}


