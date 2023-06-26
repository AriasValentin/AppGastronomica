package Model.CE;

import Model.Clases.Cliente;
import Model.Clases.Producto.Producto;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;

import java.util.Scanner;

public class Menu {

    //Atributos.
    private Scanner enter = new Scanner(System.in);
    private NegocioEnvoltorio negocioEnvoltorio;
    private Cliente clienteDefault;


    //Constructor.
    public Menu() {
        this.negocioEnvoltorio = new NegocioEnvoltorio();
        this.clienteDefault = negocioEnvoltorio.ClienteDefault(); //cliente seteado en 0 nullo

    }

    public void menuAplicacion() {

        int opcion = 0;

        do {

            clScreen();

            System.out.println("\nBIENVENIDO.");
            System.out.println("\nINGRESE FORMA DE OPERAR: \n");

            System.out.println("1 - Empleado.");
            System.out.println("2 - Administrador.");

            System.out.println("\n0 - SALIR.");

            System.out.printf("\nOpcion: ");

            opcion = enter.nextInt();

            clScreen();

            switch (opcion) {
                case 1: {
                    menuEmpleado();
                    break;
                }

                case 2: {
                    menuAdministrador();
                    break;
                }

                default: {
                    if (opcion != 0) {
                        System.out.println("\nERROR - Opcion no valida, presione Enter para intentar nuevamente.\n");
                        enter.nextLine();
                        enter.nextLine();
                    }
                }
            }
        } while (opcion != 0);
    }

    public void menuEmpleado() {

        int opcion = 0;
        char rta = 0;

        do {
            System.out.println("\n---OPCIONES EMPLEADO---\n");

            System.out.println("1 - VENDER");
            System.out.println("2 - AGREGAR CLIENTE ");
            System.out.println("3 - MOSTRAR PRODUCTOS EN SISTEMA");

            System.out.println("\n0 - ATRAS\n");

            System.out.printf("Opcion: ");
            opcion = enter.nextInt();

            clScreen();

            switch (opcion) {

                case 1: {
                    generarVenta();
                    enter.nextLine();

                    break;
                }

                case 2: {
                    enter.nextLine();
                    crearCliente();

                    break;
                }

                case 3: {
                    int retornoOpcion = mostrarProductos();

                    if (retornoOpcion == 0) {
                        opcion = -1;
                        rta = 's';
                    }
                    enter.nextLine();
                    break;
                }

                default: {
                    if (opcion != 0) {
                        System.out.println("\nERROR - Opcion no valida, presione Enter para intentar nuevamente.\n");
                        enter.nextLine();
                        enter.nextLine();
                        opcion = -1;
                        rta = 's';
                    }
                }
            }

            if ((opcion != 0) && (opcion != -1)) {
                System.out.printf("Desea volver al menu? (s/n): ");
                rta = enter.nextLine().charAt(0);
            }

            clScreen();

        } while ((rta == 's') && (opcion != 0));
    }

    public void menuAdministrador() {

        int opcion = 0;
        char rta = 0;

        do {
            System.out.println("\n\n---OPCIONES PARA CLIENTES---");

            System.out.println("1 - AGREGAR CLIENTE "); //Echo
            System.out.println("2 - ELIMINAR CLIENTE"); //Echo
            System.out.println("3 - MODIFICAR CLIENTE"); //Echo
            System.out.println("4 - MOSTRAR TODOS LOS CLIENTES EN SISTEMA"); //Echo
            System.out.println("5 - BUSCAR CLIENTE "); //Echo

            System.out.println("\n---OPCIONES PARA VENTAS---\n");

            System.out.println("6 - VENDER"); //Echo
            System.out.println("7 - ELIMINAR VENTA"); //Echo
            System.out.println("8 - MODIFICAR VENTA"); //Echo
            System.out.println("9 - MOSTRAR VENTAS DEL SISTEMA");//Echo
            System.out.println("10 - BUSCAR VENTA"); //Echo

            System.out.println("\n---OPCIONES PARA PRODUCTOS---\n");

            System.out.println("11 - AGREGAR PRODUCTO"); /////////// SE ESTAN UTILIZANDO DENTRO DE LA CLASE.
            System.out.println("12 - ELIMINAR PRODUCTO");///////////
            System.out.println("13 - MODIFICAR PRODUCTO");////////// INNECESARIO HACERLO EN EL MENU, MENOS COMPLICACIONES.
            System.out.println("14 - BUSCAR PRODUCTO"); //Echo
            System.out.println("15 - MOSTRAR TODOS LOS PRODUCTOS DEL SISTEMA"); //Echo
            System.out.println("\n-------------------------------------------------------------------------\n");
            System.out.println("0 - ATRAS");

            System.out.printf("\nOpcion: ");
            opcion = enter.nextInt();

            switch (opcion) {

                case 1: {
                    clScreen();
                    enter.nextLine();
                    crearCliente();
                    break;
                }
                case 2: {
                    clScreen();
                    eliminarCliente();
                    enter.nextLine();
                    break;
                }

                case 3: {
                    modificarCliente();
                    enter.nextLine();
                    break;
                }

                case 4: {
                    clScreen();
                    System.out.println(negocioEnvoltorio.listarClientes());
                    enter.nextLine();
                    break;
                }

                case 5: {
                    clScreen();
                    buscarCliente();
                    enter.nextLine();
                    break;
                }

                case 6: {
                    clScreen();
                    generarVenta();
                    enter.nextLine();

                    break;
                }

                case 7: {
                    clScreen();
                    eliminarVenta();
                    enter.nextLine();
                    break;
                }

                case 8: {
                    clScreen();
                    modificarVenta();
                    enter.nextLine();
                    break;
                }

                case 9: {
                    clScreen();
                    System.out.println(negocioEnvoltorio.listarVentas());
                    enter.nextLine();
                    break;
                }

                case 10: {
                    clScreen();
                    buscarUnaVenta();
                    enter.nextLine();
                    break;
                }

                case 14: {
                    clScreen();
                    buscarProducto();
                    enter.nextLine();
                    break;
                }

                case 15: {
                    clScreen();
                    int retornoOpcion = mostrarProductos();

                    if (retornoOpcion == 0) {
                        opcion = -1;
                        rta = 's';
                    }
                    enter.nextLine();
                    break;
                }

                default: {
                    if (opcion != 0) {
                        clScreen();
                        System.out.println("\nERROR - Opcion no valida, presione Enter para intentar nuevamente.\n");
                        enter.nextLine();
                        enter.nextLine();
                        opcion = -1;
                        rta = 's';
                    }
                }
            }

            if ((opcion != 0) && (opcion != -1)) {
                System.out.printf("\nDesea volver al menu? (s/n): ");
                rta = enter.nextLine().charAt(0);
            }

            clScreen();

        } while ((rta == 's') && (opcion != 0));
    }

    /**
     * @return
     */
    public Venta generarVenta() {

        Venta unaVenta = new Venta();
        Cliente aux = null;
        Producto unProducto = null;
        int DNIcliente = 0, IDproducto = 0, opcionLugarConsumo = 0;
        char rtaCliente = 0;

        System.out.println("Ingrese DNI del cliente: ");
        DNIcliente = enter.nextInt();

        clScreen();

        try {
            aux = negocioEnvoltorio.buscarClienteExistente(DNIcliente);
            unaVenta.setUnCliente(aux);

        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (aux == null) {
                System.out.println("Desea registrar al cliente? (s/n): ");
                enter.nextLine();
                rtaCliente = enter.nextLine().charAt(0);
                clScreen();

                if (rtaCliente == 's') {
                    aux = crearCliente();
                    unaVenta.setUnCliente(aux);
                } else {
                    unaVenta.setUnCliente(clienteDefault);
                }
            }
        }

        do {
            System.out.println(negocioEnvoltorio.listarProductosNombreID());

            System.out.println("Ingrese el ID del producto a agregar: ");
            IDproducto = enter.nextInt();

            try {
                unProducto = negocioEnvoltorio.buscarProducto(IDproducto);
            } catch (ElementNotFoundException e) {
                System.out.println(e.getMessage());
            } finally {
                if (unProducto == null) {
                    rtaCliente = 's';
                } else {
                    try {
                        unaVenta.agregarProductosAlcarrito(unProducto);
                        unProducto = null;
                    } catch (ElementNotLoadedException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("Desea agregar otro producto? (s/n): ");
                        enter.nextLine();
                        rtaCliente = enter.nextLine().charAt(0);
                        clScreen();
                    }
                }
            }
        } while (rtaCliente == 's');

        do {
            System.out.println("LUGAR DE CONSUMO DEL CLIENTE: ");
            System.out.println("1.MESA");
            System.out.println("2.BARRA");
            System.out.println("3.TAKEAWAY");
            opcionLugarConsumo = enter.nextInt();

            System.out.println(negocioEnvoltorio.lugarAConsumir(unaVenta, opcionLugarConsumo));

        } while (opcionLugarConsumo <= 0 || opcionLugarConsumo >= 4);

        unaVenta.setNumTicket(negocioEnvoltorio.numeroTicket());

        clScreen();

        System.out.println(unaVenta.listarVentaSinTicket());

        System.out.println("Precio total: " + unaVenta.PrecioFinalVenta() + "\n");
        try {
            negocioEnvoltorio.guardarVentas(unaVenta);
        } catch (ElementNotLoadedException ex) {
            System.out.println(ex.getMessage());
        }

        return unaVenta;
    }

    public void buscarUnaVenta() {
        int nroTicket = 0;

        System.out.println(negocioEnvoltorio.listarVentas());

        System.out.printf("\nIngrese numero de ticket a buscar: ");
        nroTicket = enter.nextInt();

        clScreen();

        try {
            Venta aux = negocioEnvoltorio.buscarVenta(nroTicket);

            if (aux != null){
                System.out.println(aux.listarVentaConCliente());
            }

        }catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    public void eliminarVenta() {
        int nroTicket = 0;

        System.out.println(negocioEnvoltorio.listarVentas());

        System.out.printf("\nIngrese numero de ticket a eliminar: ");
        nroTicket = enter.nextInt();

        try {
            boolean rta = negocioEnvoltorio.eliminarUnaVenta(nroTicket);

            clScreen();

            if (rta == true) {
                System.out.println("\nVenta eliminada correctamente.\n");
            } else {
                System.out.println("La venta no se pudo eliminar.");
            }
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarVenta() {
        int nroTicket = 0;
        float nuevoTotal = 0;
        boolean rta = false;

        System.out.println(negocioEnvoltorio.listarVentas());

        System.out.printf("\nIngrese numero de ticket a modificar: ");
        nroTicket = enter.nextInt();

        clScreen();

        try {
            Venta aux = negocioEnvoltorio.buscarVenta(nroTicket);

            System.out.println(aux.listarVentaConCliente());

            if (aux != null) {

                System.out.printf("\nIngrese el nuevo total a modificar: ");
                nuevoTotal = enter.nextFloat();

                rta = negocioEnvoltorio.modificarUnaVenta(aux, nuevoTotal);

                clScreen();
            }

            if (rta == true) {
                System.out.println("\nVenta modificada correctamente.\n");
            } else {
                System.out.println("La venta no se pudo modificar.");
            }
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ElementUnmodifiedException e) {
            System.out.println(e.getMessage());
        } catch (ElementNotLoadedException e) {
            System.out.println(e.getMessage());
        }
    }

    public int mostrarProductos() {
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

            clScreen();

            System.out.println(negocioEnvoltorio.cartaProductos(subOpcion));

            if (subOpcion == 0) {
                switch (subOpcion) {

                    case 0: {
                        subRta = 'n';
                        break;
                    }
                }
            }

            if (subOpcion < 0 || subOpcion > 5) {
                System.out.println("ERROR - Opcion invalida.\n");
            }

            if (subOpcion != 0) {
                System.out.printf("Desea ver otro listado? (s/n): ");
                enter.nextLine();
                subRta = enter.nextLine().charAt(0);
            }

            clScreen();

        } while ((subRta == 's') || (subOpcion != 0));

        return subOpcion;
    }

    public void buscarCliente() {

        System.out.println("\nIngrese DNI de cliente a buscar: ");
        int dni = enter.nextInt();
        try {
            clScreen();
            Cliente aux = negocioEnvoltorio.buscarClienteExistente(dni);
            if (aux != null) {
                System.out.println(aux.toString());
            }
        } catch (ElementNotFoundException e) {
            clScreen();
            System.out.println(e.getMessage());
        }
    }

    public void modificarCliente() {
        int dni = 0;

        System.out.println("CLIENTES: ");
        System.out.println(negocioEnvoltorio.listarClientes());

        System.out.println("\n\nIngrese DNI del cliente para modificar su estado VIP: ");
        dni = enter.nextInt();

        try {
            if (dni != 0) {
                Cliente clienteAModificar = negocioEnvoltorio.buscarClienteExistente(dni);
                int opcionVIP = 0;
                if (clienteAModificar != null) {
                    do {
                        System.out.println("1.HACER VIP.");
                        System.out.println("2.SACAR VIP.");
                        System.out.println("\nOpcion: ");
                        opcionVIP = enter.nextInt();
                        switch (opcionVIP) {
                            case 1: {
                                negocioEnvoltorio.modificarUnCliente(clienteAModificar, 1);
                                break;
                            }
                            case 2: {
                                negocioEnvoltorio.modificarUnCliente(clienteAModificar, 2);
                                break;
                            }
                            default:
                                System.out.println("\nOpcion invalida...\n");
                        }
                    } while (opcionVIP <= 0 || opcionVIP >= 3);
                }
            } else {
                System.out.println("\nERROR - No se puede modificar el cliente Default.\n");
            }
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ElementUnmodifiedException e) {
            System.out.println(e.getMessage());
        } catch (ElementNotLoadedException e) {
            System.out.println(e.getMessage());
        }

        enter.nextLine();
    }

    public void eliminarCliente() {
        int dni = 0;
        System.out.println("CLIENTES: ");
        System.out.println(negocioEnvoltorio.listarClientes());

        System.out.println("\n\nIngrese el dni del cliente que desea eliminar: ");
        dni = enter.nextInt();
        try {
            if (dni != 0) {
                boolean respuesta = negocioEnvoltorio.eliminarUnCliente(dni);

                clScreen();

                if (respuesta == true) {
                    System.out.println("\nCliente eliminado correctamente.\n");
                } else {
                    System.out.println("\nEl cliente no se pudo eliminar.\n");
                }
            } else {
                System.out.println("\nERROR - No se puede eliminar el cliente Default.\n");
            }


        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return
     */
    public Cliente crearCliente() {

        Cliente unCliente = new Cliente();

        System.out.printf("\nIngrese nombre del cliente: ");
        unCliente.setNombre(enter.nextLine());

        System.out.printf("\nIngrese apellido del cliente: ");
        unCliente.setApellido(enter.nextLine());

        System.out.printf("\nIngrese DNI del cliente: ");
        unCliente.setDni(enter.nextInt());

        System.out.printf("\nEl cliente es VIP? (s/n): ");
        enter.nextLine();
        char aux = enter.nextLine().charAt(0);

        if (aux == 's') {
            unCliente.setEsVip(true);
        } else {
            unCliente.setEsVip(false);
        }

        negocioEnvoltorio.guardarCliente(unCliente);

        return unCliente;
    }

    public void buscarProducto(){
        int id = 0;

        System.out.println(negocioEnvoltorio.listarProductosNombreID());

        System.out.println("\nIngrese ID del producto para ver su descripcion.");
        id = enter.nextInt();

        clScreen();

        try {
            Producto aux = negocioEnvoltorio.buscarProducto(id);

            if (aux != null){
                System.out.println(aux.toString());
            }

        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Limpieza de pantalla.
     */
    public void clScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
