package Model.CE;

import Model.Clases.Cliente;
import Model.Clases.Grabadora;
import Model.Clases.LugarConsumo;
import Model.Clases.Producto.Producto;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;

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

            System.out.println("1 - VENDER");
            System.out.println("3 - MODIFICAR VENTA");
            System.out.println("4 - LISTAR VENTAS");

            System.out.println("\n---OPCIONES PARA CLIENTES---\n");

            System.out.println("5 - AGREGAR CLIENTE ");
            System.out.println("6 - ELIMINAR CLIENTE");
            System.out.println("7 - MODIFICAR CLIENTE");

            System.out.println("\n---OPCIONES PARA PRODUCTOS---\n");

            System.out.println("8 - MOSTRAR PRODUCTOS EN SISTEMA");

            System.out.println("\n------------------------------------------------\n");

            System.out.println("0 - SALIR\n");

            System.out.printf("Opcion: ");
            opcion = enter.nextInt();

            clScreen();

            switch (opcion) {

                case 1: {
                    crearVenta();
                    try {
                        lista_ventas.agregar(crearVenta());
                    } catch (ElementNotLoadedException e) {
                        System.out.printf(e.getMessage());
                    }

                    break;
                }

                case 5: {

                    Cliente unCliente = null;

                    try {
                        Grabadora<Cliente> grabadoraCliente = new Grabadora<>();
                        lista_clientes.agregar(unCliente);


                    } catch (ElementNotLoadedException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

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

                case 8: {

                    int subOpcion = 0;
                    char subRta = 0;

                    do {
                        System.out.println("PRODUCTOS EN SISTEMA:");

                        System.out.println("\n1 - COMIDAS DULCES.");
                        System.out.println("2 - COMIDAS SALADAS.");
                        System.out.println("3 - BEBIDAS FRIAS.");
                        System.out.println("4 - BEBIDAS CALIENTES.");
                        System.out.println("5 - MOSTRAR TODOS LOS PRODUCTOS.");

                        System.out.println("\n0 - ATRAS.\n");

                        System.out.printf("Opcion: ");
                        subOpcion = enter.nextInt();


                        switch (subOpcion) {


                            case 0: {
                                opcion = -1;
                                rta = 's';
                                subRta = 'n';
                                break;
                            }

                            default: {
                                System.out.println("\nERROR - Opcion no valida.\n");
                                break;
                            }
                        }

                        if (subOpcion != 0) {
                            System.out.printf("Desea ver otro listado? (s/n): ");
                            enter.nextLine();
                            subRta = enter.nextLine().charAt(0);
                        }

                        clScreen();

                    } while ((subRta == 's') || (subOpcion != 0));

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

    public Venta crearVenta() {

        Venta nuevaVenta = new Venta();
        Cliente nuevoCliente = null;

        int opcionSeguirComprando = 0, idProducto = 0, opcionCliente = 0, opcionDni = 0, opcionLugarConsumo = 0, flag = 0;
        char rta = 0;

        //crear un cliente para la venta o usar uno existente

        //productos que va a llevar el cliente

        //lugar donde va a consumir el cliente

        do {
            System.out.println("LUGAR DE CONSUMO DEL CLIENTE: ");
            System.out.println("1.MESA");
            System.out.println("2.BARRA");
            System.out.println("3.TAKEAWAY");
            opcionLugarConsumo = enter.nextInt();
            switch (opcionLugarConsumo) {
                case 1:
                    //consultar! hacer switch
                    break;
                case 2:
                    nuevaVenta.setLugarConsumo(LugarConsumo.BARRA);
                    break;
                case 3:
                    nuevaVenta.setLugarConsumo(LugarConsumo.TakeAway);
                    break;
                default:
                    System.out.println("\nERROR - Opcion no valida.\n");
            }

            enter.nextLine();

        } while (opcionLugarConsumo <= 0 || opcionLugarConsumo >= 4);

        //calculo el total de esa venta (el atributo del total esta dentro de la misma | tambien se puede retornar)
        nuevaVenta.setTotal(nuevaVenta.PrecioFinalVenta());

        return nuevaVenta;
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


