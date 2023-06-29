package Model.CE;

import Model.Clases.Cliente;
import Model.Clases.LugarConsumo;
import Model.Clases.Producto.Bebidas.Bebida;
import Model.Clases.Producto.Comidas.Comida;
import Model.Clases.Producto.Producto;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import org.json.JSONException;

/**
 * Clase que envuelve las envoltorias de las colecciones de Ventas, Productos y Clientes.
 *
 * @see Venta
 * @see EnvoltoriaVentas
 * @see Producto
 * @see EnvoltoriaProductos
 * @see Cliente
 * @see EnvoltoriaClientes
 */
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

    /**
     * Retorna una cadena de informacion de todas las ventas que retorna la funcion listar de su respectiva envoltoria.
     *
     * @return String
     */
    public String listarVentas() {
        String cadena = "";
        cadena = lista_ventas.listar();
        return cadena;
    }

    /**
     * Agrega un objeto de tipo Venta a su coleccion
     *
     * @param unaVenta - Venta a agregar a la coleccion.
     * @throws ElementNotLoadedException Lanza la excepcion si la venta es nula.
     */
    public void guardarVentas(Venta unaVenta) throws ElementNotLoadedException {

        if (unaVenta != null) {
            lista_ventas.agregar(unaVenta);
        } else {
            throw new ElementNotLoadedException("\nERROR - La venta no pudo ser cargada.\n");
        }
    }

    /**
     * Setea el lugar de consumo de una venta especifica que llega por parametro por medio de un switch, con una opcion que corresponde a la seleccion del usuario.
     *
     * @param unaVenta Venta a setear lugar de consumo.
     * @param opcion   Condicion del switch de lugar de consumo.
     * @return String - Si la opcion es invalida concatena un mensaje.
     */
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

    /**
     * Elimina un objeto de tipo Venta de su coleccion, llamando por parametro el numero de ticket como referencia de la venta a eliminar.
     *
     * @param nroTicket ID de la venta a eliminar.
     * @return boolean - true si elimina la venta, false si no.
     * @throws ElementNotFoundException Lanza la excepcion si no se encontro la venta.
     */
    public boolean eliminarUnaVenta(int nroTicket) throws ElementNotFoundException {
        return lista_ventas.eliminar(nroTicket);
    }

    /**
     * Modifica el valor total de un objeto de tipo Venta en su coleccion, llamando por parametro el nuevo total que se setea.
     *
     * @param unaVenta   Venta a modificar total.
     * @param nuevoTotal Valor del total a modificar.
     * @return boolean - true si modifica la venta, false si no.
     * @throws ElementNotFoundException   Lanza la excepcion si no se encontro la venta.
     * @throws ElementUnmodifiedException Lanza la excepcion si la venta no pudo ser modificada.
     * @throws ElementNotLoadedException  Lanza la excepcion si no se pudo agregar la venta modificada.
     */
    public boolean modificarUnaVenta(Venta unaVenta, float nuevoTotal) throws ElementNotFoundException, ElementUnmodifiedException, ElementNotLoadedException {

        boolean retorno = false;

        if (unaVenta != null) {
            lista_ventas.modificar(unaVenta, nuevoTotal);
            retorno = true;
        }
        return retorno;
    }

    /**
     * Buscar y retorna un objeto de tipo Venta por medio de su coleccion, llamando por parametro el numero de ticket de referencia.
     *
     * @param nroTicket Valor para buscar la venta.
     * @return Venta
     * @throws ElementNotFoundException Lanza la excepcion si no se encontro la venta.
     */
    public Venta buscarVenta(int nroTicket) throws ElementNotFoundException {
        return lista_ventas.buscar(nroTicket);
    }

    /**
     * Retorna el numero de ticket de la ultima venta ingresada a la coleccion.
     *
     * @return int - Numero de ticket
     */
    public int numeroTicket() {
        return lista_ventas.getIndexNroTicket();
    }

    //METODOS PRODUCTOS.

    /**
     * Buscar y retorna un objeto de tipo Producto por medio de su coleccion, llamando por parametro el numero de ID de referencia.
     *
     * @param id Valor para buscar el producto.
     * @return Producto
     * @throws ElementNotFoundException Lanza la excepcion si no se encontro el producto.
     */
    public Producto buscarProducto(int id) throws ElementNotFoundException {
        return lista_productos.buscar(id);
    }

    /**
     * Lista por medio de un switch y una opcion que llega por parametro, todos o los distintos tipos de productos.
     *
     * @param opcion Valor de ingreso a los case del switch.
     * @return String
     */
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

    /**
     * Retorna en un string con todos los productos de su coleccion, solo con su nombre e ID.
     *
     * @return String
     */
    public String listarProductosNombreID() {
        String aux = "";
        aux = lista_productos.listarNombreID();
        return aux;
    }

    /**
     * Agrega un objeto de tipo Producto a su coleccion y su archivo JSON correspondiente.
     *
     * @param unProducto Producto a agregar.
     * @throws JSONException Lanza la excepcion si la apertura o los metodos put/get fueron incorrectos.
     */
    public void agregarProducto(Producto unProducto) throws JSONException {
        if (unProducto != null) {
            if (unProducto instanceof Comida) {
                lista_productos.agregarComidaAJson((Comida) unProducto);
            }
            if (unProducto instanceof Bebida) {
                lista_productos.agregarBebidaAJson((Bebida) unProducto);
            }
        }
        //vuelvo a leer los archivos actualizados
        lista_productos.vaciarListaProductos();
        lista_productos.consumoJSON();
    }

    /**
     * Elimina un objeto de tipo Producto a su coleccion y su archivo JSON correspondiente.
     *
     * @param unProducto Producto a eliminar.
     * @throws JSONException Lanza la excepcion si la apertura o los metodos put/get fueron incorrectos.
     */
    public void eliminarProducto(Producto unProducto) throws JSONException {
        if (unProducto != null) {
            if (unProducto instanceof Comida) {
                lista_productos.eliminarComidaAJson((Comida) unProducto);
            }
            if (unProducto instanceof Bebida) {
                lista_productos.eliminarBebidaAJson((Bebida) unProducto);
            }
        }

        //vuelvo a leer los archivos actualizados
        lista_productos.vaciarListaProductos();
        lista_productos.consumoJSON();
    }

    //METODOS CLIENTE

    /**
     * Agrega un objeto de tipo Cliente a su coleccion.
     *
     * @param unCliente Cliente a agregar.
     * @return String con informacion.
     */
    public String guardarCliente(Cliente unCliente) {

        String aux = "";

        try {
            lista_clientes.agregar(unCliente);
        } catch (ElementNotLoadedException e) {
            aux = e.getMessage();
        }

        return aux;
    }

    /**
     * Busca un objeto de tipo Cliente en su coleccion, llamando por parametro un DNI como referencia.
     *
     * @param dni Valor para buscar el cliente.
     * @return Cliente
     * @throws ElementNotFoundException Lanza la excepcion si el cliente no se encuentra.
     */
    public Cliente buscarClienteExistente(int dni) throws ElementNotFoundException {
        return lista_clientes.buscar(dni);
    }

    /**
     * Retorna una cadena de informacion de todas los objetos de tipo Cliente que retorna la funcion listar de su respectiva envoltoria.
     *
     * @return String
     */
    public String listarClientes() {
        return lista_clientes.listar();
    }

    /**
     * Crea, agrega a su coleccion y retorna un cliente default con sus atributos ya seteados.
     *
     * @return Cliente
     */
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

    /**
     * Elimina un objeto de tipo Cliente de su coleccion, llamando por parametro el DNI como referencia de la venta a eliminar.
     *
     * @param dni DNI del cliente a eliminar.
     * @return boolean - true si elimina el cliente, false si no.
     * @throws ElementNotFoundException Lanza la excepcion si no se encontro el cliente.
     */
    public boolean eliminarUnCliente(int dni) throws ElementNotFoundException {
        boolean rta = false;
        Cliente aux = buscarClienteExistente(dni);
        if (aux != null) {
            lista_clientes.eliminar(aux.getDni());
            rta = true;
        }
        return rta;
    }

    /**
     * Modifica la membresia VIP de un objeto de tipo Cliente en su coleccion, llamando por parametro la opcion para setear.
     *
     * @param unCliente Cliente a modificar.
     * @param opcion    Opcion para setear membresia del cliente.
     * @throws ElementNotFoundException   Lanza la excepcion si no se encontro el cliente.
     * @throws ElementUnmodifiedException Lanza la excepcion si el cliente no pudo ser modificado.
     * @throws ElementNotLoadedException  Lanza la excepcion si no se pudo agregar el cliente modificado.
     */
    public void modificarUnCliente(Cliente unCliente, int opcion) throws ElementUnmodifiedException, ElementNotFoundException, ElementNotLoadedException {
        lista_clientes.modificar(unCliente, opcion);
    }
}


