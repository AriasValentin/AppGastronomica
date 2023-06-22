package Model.CE;

import Model.CE.EnvoltoriaClientes;
import Model.CE.EnvoltoriaProductos;
import Model.CE.EnvoltoriaVentas;
import Model.Clases.Cliente;
import Model.Clases.LugarConsumo;
import Model.Clases.Producto.Producto;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class NegocioEnvoltorio {

    private static Scanner enter = new Scanner(System.in);

    //Atributos.
    private EnvoltoriaVentas lista_ventas;
    private EnvoltoriaProductos lista_productos;
    private EnvoltoriaClientes lista_clientes;

    public NegocioEnvoltorio() {
        lista_ventas = new EnvoltoriaVentas();
        lista_productos = new EnvoltoriaProductos();
        lista_clientes = new EnvoltoriaClientes();
    }

    public void menuAplicacion() {

        int opcion = 0;
        lista_productos.consumoJSON();

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

            System.out.println("7 - AGREGAR CLIENTE ");
            System.out.println("8 - ELIMINAR CLIENTE");
            System.out.println("9 - MODIFICAR CLIENTE");

            System.out.println("\n---OPCIONES PARA PRODUCTOS---\n");

            System.out.println("10 - MOSTRAR PRODUCTOS EN SISTEMA");

            System.out.println("\n------------------------------------------------\n");

            System.out.println("0 - SALIR\n");

            System.out.printf("Opcion: ");
            opcion = enter.nextInt();

            clScreen();

            switch (opcion) {


                case 7: {

                    Cliente unCliente = new Cliente();

                    System.out.printf("\nIngrese nombre del cliente: ");
                    enter.nextLine();
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

                    try {
                        lista_clientes.agregar(unCliente);
                    } catch (ElementNotLoadedException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case 8: {
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

                case 9: {
                    System.out.printf("Ingrese DNI de la persona a modificar: ");
                    int dni = enter.nextInt();

                    if (dni != 0) {
                        try {
                            lista_clientes.modificar(dni);
                        } catch (ElementUnmodifiedException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    enter.nextLine();

                    break;
                }

                case 10: {

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
                            case 1: {
                                lista_productos.vaciarLista();
                                lista_productos.consumoComidasDulces();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            case 2: {
                                lista_productos.vaciarLista();
                                lista_productos.consumoComidasSaladas();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            case 3: {
                                lista_productos.vaciarLista();
                                lista_productos.consumoBebidasFriasPERSONAL();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            case 4: {
                                lista_productos.vaciarLista();
                                lista_productos.consumoBebidasCalientesPersonal();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            case 5: {
                                lista_productos.vaciarLista();
                                lista_productos.consumoJSON();
                                System.out.println(lista_productos.listar());
                                break;
                            }

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

    public void menuAdministrador() {

        int opcion = 0;

        System.out.println("\n\n---OPCIONES PARA CLIENTES---");
        System.out.println("1. AGREGAR CLIENTE ");
        System.out.println("2. ELIMINAR CLIENTE");
        System.out.println("3. MODIFICAR CLIENTE");
        System.out.println("4. MOSTRAR TODOS LOS CLIENTES EN SISTEMA");
        System.out.println("5. BUSCAR CLIENTE ");


        System.out.println("\n---OPCIONES PARA VENTAS---\n");
        System.out.println("6. VENDER");
        System.out.println("7. ELIMINAR VENTA");
        System.out.println("8. MODIFICAR VENTA");
        System.out.println("9. LISTAR VENTAS");
        System.out.println("10. MOSTRAR VENTAS DEL SISTEMA");
        System.out.println("11. BUSCAR VENTA");

        System.out.println("\n---OPCIONES PARA PRODUCTOS---\n");
        System.out.println("12. AGREGAR PRODUCTO");
        System.out.println("13. ELIMINAR PRODUCTO");
        System.out.println("14. MODIFICAR PRODUCTO");
        System.out.println("15. BUSCAR PRODUCTO");
        System.out.println("16. MOSTRAR TODOS LOS PRODUCTOS DEL SISTEMA");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("0 SALIR");
    }

    public void clScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public Venta unaVenta() throws ElementNotLoadedException,ElementNotFoundException {

        Venta nuevaVenta = new Venta();
        Cliente nuevoCliente = new Cliente();

        int opcionSeguirComprando = 0, idProducto = 0, opcionCliente = 0, opcionDni = 0, opcionLugarConsumo = 0;

        do {
            System.out.println("ES CLIENTE? \n1.SI   \n2.NO \nIngrese una opcion: ");
            opcionCliente = enter.nextInt();
            switch(opcionCliente)
            {
                case 1:
                {
                    //existe el cliente, se busca por dni..
                    System.out.println("INGRESE DNI DEL CLIENTE: ");
                    opcionDni = enter.nextInt();
                    nuevoCliente = lista_clientes.buscar(opcionDni); //retorna cliente y lo almaceno en mi variable local
                    if(nuevoCliente != null)
                    {
                        nuevaVenta.setUnCliente(nuevoCliente); //lo agrego a mi venta
                    }
                    else
                    {
                        System.out.println("Cliente inexistente con DNI ingresado .");
                    }
                    break;
                }
                case 2:
                {
                    //crear nuevo cliente
                    System.out.printf("\nINGRESE NOMBRE DEL CLIENTE: ");
                    enter.nextLine();
                    nuevoCliente.setNombre(enter.nextLine());

                    System.out.printf("\nINGRESE APELLIDO DEL CLIENTE: ");
                    nuevoCliente.setApellido(enter.nextLine());

                    System.out.printf("\nINGRESE DNI DEL CLIENTE: ");
                    nuevoCliente.setDni(enter.nextInt());

                    System.out.printf("\nEL CLIENTE ES VIP? (s/n): ");
                    enter.nextLine();
                    char aux = enter.nextLine().charAt(0);

                    if (aux == 's') {
                        nuevoCliente.setEsVip(true);
                    } else {
                        nuevoCliente.setEsVip(false);
                    }

                    try {
                        lista_clientes.agregar(nuevoCliente); //lo agrego a la lista de clientes
                        nuevaVenta.setUnCliente(nuevoCliente); //lo agrego a mi venta
                        System.out.println("\nNuevo cliente agregado al sistema.");

                    } catch (ElementNotLoadedException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }
                default:
                    System.out.println("\nERROR - Opcion no valida.\n");
            }
        }while(opcionCliente<=0 || opcionCliente>=3);

        //productos que va a llevar el cliente

        do {
            System.out.println(lista_productos.listar());

            System.out.println("\n\nIngrese el ID del producto a agregar: ");
            idProducto = enter.nextInt();
            try {
                Producto nuevo = lista_productos.buscar(idProducto);
                if (nuevo != null) {
                    nuevaVenta.agregarProductosAlcarrito(nuevo);
                }
            } catch (ElementNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println("\nAGREGAR OTRO PRODUCTO? \n1: SI   \n2:NO");
            opcionSeguirComprando = enter.nextInt();
        } while (opcionSeguirComprando == 1);

        //lugar donde va a consumir el cliente

        do {
            System.out.println("LUGAR DE CONSUMO DEL CLIENTE: ");
            System.out.println("1.MESA");
            System.out.println("2.BARRA");
            System.out.println("3.TAKEAWAY");
            opcionLugarConsumo = enter.nextInt();
            switch (opcionLugarConsumo)
            {
                case 1:
                    //consultar!
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

        }while(opcionLugarConsumo<=0 || opcionLugarConsumo>=4);

        //calculo el total de esa venta (el atributo del total esta dentro de la misma | tambien se puede retornar)
        nuevaVenta.PrecioFinalVenta();

        return  nuevaVenta;
    }
}
