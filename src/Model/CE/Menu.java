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

public class Menu {

    //Atributos.
    private Scanner enter = new Scanner(System.in);
    private NegocioEnvoltorio negocioEnvoltorio;
    Cliente clienteDefault;

    //Constructor.
    public Menu() {
        negocioEnvoltorio = new NegocioEnvoltorio();
        clienteDefault = negocioEnvoltorio.ClienteDefault(); //cliente seteado en 0 nullo
    }

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
            System.out.println("2 - MODIFICAR VENTA");
            System.out.println("3 - LISTAR VENTAS");

            System.out.println("\n---OPCIONES PARA CLIENTES---\n");

            System.out.println("4 - AGREGAR CLIENTE ");
            System.out.println("5 - ELIMINAR CLIENTE");
            System.out.println("6 - MODIFICAR CLIENTE");

            System.out.println("\n---OPCIONES PARA PRODUCTOS---\n");

            System.out.println("7 - MOSTRAR PRODUCTOS EN SISTEMA");

            System.out.println("\n------------------------------------------------\n");

            System.out.println("0 - SALIR\n");

            System.out.printf("Opcion: ");
            opcion = enter.nextInt();

            clScreen();

            switch (opcion) {

                case 1: {
                    Venta unaVenta = generarVenta();

                    System.out.println("\nPrecio total: " + unaVenta.PrecioFinalVenta());

                    break;
                }
            }

            if ((opcion != 0) && (opcion != -1)) {
                System.out.printf("Desea volver al menu? (s/n): ");
                enter.nextLine();
                rta = enter.nextLine().charAt(0);
            }

            clScreen();

        } while ((rta == 's') && (opcion != 0));
    }

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

        return unaVenta;
    }

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

        return unCliente;
    }

    public void clScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }


    /*
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
     */
}
