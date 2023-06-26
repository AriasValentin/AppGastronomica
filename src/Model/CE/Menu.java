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
                    System.out.println("\nERROR - Opcion no valida, presione Enter para intentar nuevamente.\n");
                    enter.nextLine();
                    enter.nextLine();
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

            System.out.println("1. AGREGAR CLIENTE "); //Echo
            System.out.println("2. ELIMINAR CLIENTE"); //Echo
            System.out.println("3. MODIFICAR CLIENTE"); //Echo
            System.out.println("4. MOSTRAR TODOS LOS CLIENTES EN SISTEMA");
            System.out.println("5. BUSCAR CLIENTE ");


            System.out.println("\n---OPCIONES PARA VENTAS---\n");

            System.out.println("6. VENDER"); //Echo
            System.out.println("7. ELIMINAR VENTA");
            System.out.println("8. MODIFICAR VENTA");
            System.out.println("9. LISTAR VENTAS"); //Echo
            System.out.println("10. MOSTRAR VENTAS DEL SISTEMA");
            System.out.println("11. BUSCAR VENTA");

            System.out.println("\n---OPCIONES PARA PRODUCTOS---\n");

            System.out.println("12. AGREGAR PRODUCTO");
            System.out.println("13. ELIMINAR PRODUCTO");
            System.out.println("14. MODIFICAR PRODUCTO");
            System.out.println("15. BUSCAR PRODUCTO");
            System.out.println("16. MOSTRAR TODOS LOS PRODUCTOS DEL SISTEMA"); //Echo
            System.out.println("\n-------------------------------------------------------------------------\n");
            System.out.println("0 SALIR");

            System.out.printf("\nOpcion: ");
            opcion = enter.nextInt();

            switch (opcion) {

                case 1: {
                    enter.nextLine();
                    crearCliente();
                    break;
                }
                case 2: {
                    eliminarCliente();
                    enter.nextLine();
                    break;
                }

                case 3: {
                    modificarCliente();
                    enter.nextLine();
                    break;
                }

                case 6: {
                    generarVenta();
                    enter.nextLine();

                    break;
                }

                case 9: {
                    System.out.println(negocioEnvoltorio.listarVentas());
                    enter.nextLine();
                    break;
                }

                case 16: {
                    int retornoOpcion = mostrarProductos();

                    if (retornoOpcion == 0) {
                        opcion = -1;
                        rta = 's';
                    }
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
            System.out.println(negocioEnvoltorio.cartaProductos(5));

            System.out.println("\n\nIngrese el ID del producto a agregar: ");
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

        System.out.println(unaVenta.listarVentaSinTicket());
        System.out.println("Precio total: " + unaVenta.PrecioFinalVenta() + "\n");
        try {
            negocioEnvoltorio.guardarVentas(unaVenta);
        } catch (ElementNotLoadedException ex) {
            System.out.println(ex.getMessage());
        }

        return unaVenta;
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
        boolean respuesta;
        int dni = 0;
        System.out.println("CLIENTES: ");
        System.out.println(negocioEnvoltorio.listarClientes());

        System.out.println("\n\nIngrese el dni del cliente que desea eliminar: ");
        dni = enter.nextInt();
        try {
            if (dni != 0) {
                respuesta = negocioEnvoltorio.eliminarUnCliente(dni);
                if (respuesta == true) {
                    System.out.println("\nCliente eliminado correctamente.");
                } else {
                    System.out.println("\nCliente no se pudo eliminar.");
                }
            } else {
                System.out.println("\nERROR - No se puede eliminar el cliente Default.\n");
            }


        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }

        enter.nextLine();
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

    /**
     * Limpieza de pantalla.
     */
    public void clScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
}
