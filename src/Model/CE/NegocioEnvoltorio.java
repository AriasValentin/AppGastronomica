package Model.CE;

import Model.CE.EnvoltoriaClientes;
import Model.CE.EnvoltoriaProductos;
import Model.CE.EnvoltoriaVentas;
import Model.Clases.Cliente;
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

    public EnvoltoriaVentas getLista_ventas() {
        return lista_ventas;
    }

    public EnvoltoriaProductos getLista_productos() {
        return lista_productos;
    }

    public EnvoltoriaClientes getLista_clientes() {
        return lista_clientes;
    }

    public void menuAplicacion(int contraseña) {

        if (contraseña == 123) {

            int opcion = 0;

            System.out.println("Bienvenidos al panel de negocio.");
            System.out.println("Como desea operar?: ");

            System.out.println("1 - Empleado.");
            System.out.println("2 - Administrador.");

            opcion = enter.nextInt();

            switch (opcion) {
                case 1: {
                    menuEmpleado();
                    break;
                }

            }

        }
    }

    public void menuEmpleado() {

        int opcion = 0;
        char rta = 0;

        do {
            System.out.println("\n---OPCIONES PARA VENTAS---\n");
            System.out.println("1. VENDER");
            System.out.println("3. MODIFICAR VENTA");
            System.out.println("4. LISTAR VENTAS");
            System.out.println("\n---OPCIONES PARA CLIENTES---");
            System.out.println("7. AGREGAR CLIENTE ");
            System.out.println("8. ELIMINAR CLIENTE");
            System.out.println("9. MODIFICAR CLIENTE");
            System.out.println("\n---OPCIONES PARA PRODUCTOS---\n");
            System.out.println("16. MOSTRAR TODOS LOS PRODUCTOS DEL SISTEMA");
            System.out.println("\n-------------------------------------------------------------------------\n");
            System.out.println("0 SALIR");

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
                    }catch (ElementNotLoadedException e){
                        System.out.println(e.getMessage());
                    }

                    System.out.println(lista_clientes.listar());

                    break;
                }
            }

            System.out.printf("Desea volver al menu? (s/n): ");
            rta = enter.nextLine().charAt(0);

        } while (rta == 's');
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


