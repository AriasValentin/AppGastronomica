package Model.CE;

import Model.Clases.Cliente;
import Model.Clases.GrabadoraYLectoraArchivos;
import Model.Clases.LugarConsumo;
import Model.Clases.Producto.Producto;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;


import java.io.Serializable;
import java.util.Iterator;
import java.util.Scanner;


public class NegocioEnvoltorio  {
    private Scanner enter = new Scanner(System.in);
    private GrabadoraYLectoraArchivos miGrabadora = new GrabadoraYLectoraArchivos();

    //Atributos.
    private EnvoltoriaVentas lista_ventas;
    private EnvoltoriaProductos lista_productos;
    private EnvoltoriaClientes lista_clientes;


    public EnvoltoriaVentas getLista_ventas() {
        return lista_ventas;
    }

    public NegocioEnvoltorio() {
        lista_ventas = new EnvoltoriaVentas();
        lista_productos = new EnvoltoriaProductos();
        lista_clientes = new EnvoltoriaClientes();
        lista_productos.consumoJSON(); //consumo de productos
    }


    public String listarVentas(){
        String cadena = "";
        cadena = lista_ventas.listar();
        return cadena;
    }
/*
    public void grabarArchivoCliente(Cliente unCliente){
        Grabadora<Cliente> miGrabadora = new Grabadora<>();
        Iterator<Cliente> it = lista_clientes.devolverIterador();

        miGrabadora.persistirObjeto(it,"clientes.dat");
    }*/




    public String guardarCliente(Cliente unCliente){

        String aux = "";

        try {

            lista_clientes.agregar(unCliente);
            //grabarArchivoCliente(unCliente);

        }catch (ElementNotLoadedException e){
            aux = e.getMessage();
        }

        return aux;
    }


    public void guardarVentas(Venta unaVenta)throws ElementNotLoadedException {

        if (unaVenta != null) {
            lista_ventas.agregar(unaVenta);
        }
        else
        {
            throw new ElementNotLoadedException("\nERROR - La venta no pudo ser cargada.\n");
        }
    }


    public String lugarAConsumir(Venta unaVenta, int opcion) {

        String aux = "";

        switch (opcion) {
            case 1:{
                unaVenta.setLugarConsumo(LugarConsumo.MESA);
                break;
            }

            case 2:{
                unaVenta.setLugarConsumo(LugarConsumo.BARRA);
                break;
            }

            case 3:{
                unaVenta.setLugarConsumo(LugarConsumo.TakeAway);
                break;
            }

            default:{
                aux = "\nERROR - Opcion no valida.\n";
            }
        }
        return aux;
    }

    public Producto buscarProducto(int id) throws ElementNotFoundException {
        return lista_productos.buscar(id);
    }

    public String cartaProductos(int opcion){
        String aux = "";

        switch (opcion){
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

    public Cliente buscarClienteExistente(int dni) throws ElementNotFoundException {
        return lista_clientes.buscar(dni);
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
}


