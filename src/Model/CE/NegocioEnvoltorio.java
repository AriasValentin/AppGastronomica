package Model.CE;

import Model.Clases.Cliente;
import Model.Clases.Grabadora;
import Model.Clases.LugarConsumo;
import Model.Clases.Producto.Producto;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;

import java.util.Iterator;
import java.util.Scanner;


public class NegocioEnvoltorio {
    private Scanner enter = new Scanner(System.in);

    //Atributos.
    private EnvoltoriaVentas lista_ventas;
    private EnvoltoriaProductos lista_productos;
    private EnvoltoriaClientes lista_clientes;

    public NegocioEnvoltorio() {
        lista_ventas = new EnvoltoriaVentas();
        lista_productos = new EnvoltoriaProductos();
        lista_clientes = new EnvoltoriaClientes();
        lista_productos.consumoJSON(); //consumo de productos
    }

    /*
    public EnvoltoriaVentas getLista_ventas() {
        return lista_ventas;
    }

    public EnvoltoriaProductos getLista_productos() {
        return lista_productos;
    }

    public EnvoltoriaClientes getLista_clientes() {
        return lista_clientes;
    }
     */

    public void menuAplicacion() {

        int opcion = 0;


        System.out.println("\nBIENVENIDO.");
        System.out.println("\nINGRESE FORMA DE OPERAR: \n");

        System.out.println("1 - Empleado.");
        System.out.println("2 - Administrador.");

        System.out.printf("\nOpcion: ");

        opcion = enter.nextInt();

        clScreen();

        switch (opcion) {
            case 1: {
                menuEmpleado();
                break;
            }

        }
    }

    public void menuEmpleado() {

        int opcion = 0;
        char rta = 0;

        do {
            System.out.println("\n---OPCIONES PARA VENTAS---\n");

            System.out.println("3 - MODIFICAR VENTA");
            System.out.println("4 - LISTAR VENTAS");

            System.out.println("\n---OPCIONES PARA CLIENTES---\n");

            System.out.println("6 - ELIMINAR CLIENTE");
            System.out.println("7 - MODIFICAR CLIENTE");

            System.out.println("\n---OPCIONES PARA PRODUCTOS---\n");

            System.out.println("\n------------------------------------------------\n");

            System.out.println("0 - SALIR\n");

            System.out.printf("Opcion: ");
            opcion = enter.nextInt();

            clScreen();

            switch (opcion) {

                case 6: {
                    boolean borrado = false;

                    System.out.printf("Ingrese el DNI del cliente a eliminar: ");
                    int dni = enter.nextInt();

                    try {
                        borrado = lista_clientes.eliminar(dni);

                        if (borrado == true) {
                            System.out.println("\nEl cliente se elimino exitosamente.\n");
                        }
                    } catch (ElementNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    enter.nextLine();

                    break;
                }

                case 7: {
                    System.out.printf("Ingrese DNI de la persona a modificar: ");
                    int dni = enter.nextInt();

                    /*
                    if (dni != 0) {
                        try {
                            lista_clientes.modificar(dni);
                        } catch (ElementUnmodifiedException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                     */

                    enter.nextLine();

                    break;
                }
            }

            if ((opcion != 0) && (opcion != -1)) {
                System.out.printf("Desea volver al menu? (s/n): ");
                rta = enter.nextLine().charAt(0);
            }

            clScreen();

        } while ((rta == 's') && (opcion != 0));
    }

    public void clScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public String listarVentas(){
        String cadena = "";
        cadena = lista_ventas.listar();
        return cadena;
    }

    public void grabarArchivoCliente(Cliente unCliente){
        Grabadora<Cliente> miGrabadora = new Grabadora<>();
        Iterator<Cliente> it = lista_clientes.devolverIterador();

        miGrabadora.persistirObjeto(it,"clientes.dat");
    }

    public void grabarArchivoVenta(Venta unaVenta){
        Grabadora<Venta> miGrabadora = new Grabadora<>();
        Iterator<Venta> it = lista_ventas.devolverIterador();

        miGrabadora.persistirObjeto(it,"ventas.dat");
    }

    public String guardarCliente(Cliente unCliente){

        String aux = "";

        try {

            lista_clientes.agregar(unCliente);
            grabarArchivoCliente(unCliente);

        }catch (ElementNotLoadedException e){
            aux = e.getMessage();
        }

        return aux;
    }

    public String guardarVentas(Venta unaVenta){

        String aux = "";

        try{
            if (unaVenta != null){
                lista_ventas.agregar(unaVenta);
                grabarArchivoVenta(unaVenta);
            }
        }catch (ElementNotLoadedException e){
            aux = e.getMessage();
        }

        return aux;
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


