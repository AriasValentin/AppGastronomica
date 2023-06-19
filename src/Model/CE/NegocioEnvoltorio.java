package Model.CE;

import Model.CE.EnvoltoriaClientes;
import Model.CE.EnvoltoriaProductos;
import Model.CE.EnvoltoriaVentas;
import Model.Clases.Cliente;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;

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

        System.out.println("Bienvenidos al panel de negocio.");
        System.out.println("Como desea operar?: \n");

        System.out.println("1 - Empleado.");
        System.out.println("2 - Administrador.");

        System.out.printf("\nOpcion: ");

        opcion = enter.nextInt();

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

                case 8:{
                    boolean borrado = false;

                    System.out.printf("Ingrese el DNI del cliente a eliminar: ");
                    int dni = enter.nextInt();

                    try{
                        borrado = lista_clientes.eliminar(dni);

                        if (borrado == true){
                            System.out.println("\nEl cliente se elimino exitosamente.\n");
                        }
                    }catch (ElementNotFoundException e){
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case 10:{

                    int subOpcion = 0;

                    System.out.println("PRODUCTOS EN SISTEMA:");

                    System.out.println("\n1 - COMIDAS DULCES.");
                    System.out.println("2 - COMIDAS SALADAS.");
                    System.out.println("3 - BEBIDAS FRIAS.");
                    System.out.println("4 - BEBIDAS CALIENTES.");
                    System.out.println("5 - MOSTRAR TODOS LOS PRODUCTOS.");

                    System.out.println("\n6 - ATRAS.\n");

                    System.out.printf("Opcion: ");
                    subOpcion = enter.nextInt();

                    do {
                        switch (subOpcion){
                            case 1:{
                                lista_productos.consumoComidasDulces();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            case 2:{
                                lista_productos.consumoComidasSaladas();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            case 3:{
                                lista_productos.consumoBebidasFriasPERSONAL();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            case 4:{
                                lista_productos.consumoBebidasCalientesPersonal();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            case 5:{
                                lista_productos.consumoJSON();
                                System.out.println(lista_productos.listar());
                                break;
                            }

                            default:{
                                System.out.println("\nERROR - Opcion no valida.\n");
                                break;
                            }
                        }

                    }while ((rta == 's') && (subOpcion != 0));

                    break;
                }
            }

            if(opcion != 0){
                System.out.printf("Desea volver al menu? (s/n): ");
                enter.nextLine();
                rta = enter.nextLine().charAt(0);
            }

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
}
