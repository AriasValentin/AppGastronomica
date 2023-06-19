package Model.CE;

import Model.Clases.Cliente;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import Model.Interfaces.IABM;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Clase que envuelve todas los objetos de tipo Cliente almacenados en una coleccion de tipo LinkedHashSet. Dicha coleecion es manipulada por la interfaz implementada IABM.
 *
 * @see LinkedHashSet
 * @see Cliente
 */

public class EnvoltoriaClientes implements IABM<Cliente> {

    Scanner scan = new Scanner(System.in);
    private LinkedHashSet<Cliente> listaDeClientes;

    public EnvoltoriaClientes() {
        this.listaDeClientes = new LinkedHashSet<>();
    }

    /**
     * Añade un objeto de tipo Cliente a la LinkedHashSet.
     *
     * @param unCliente Objeto de tipo cliente.
     *                  Lanza una excepcion si el cliente es nulo.
     */
    @Override
    public void agregar(Cliente unCliente) throws ElementNotLoadedException {
        if (unCliente != null) {
            listaDeClientes.add(unCliente);
        } else {
            throw new ElementNotLoadedException("\nERROR - El cliente no tiene datos, o no existe.\n");
        }
    }

    /**
     * Elimina un objeto de tipo Cliente a la LinkedHashSet. Recorre con un iterador la collecion y verifica si se cumple igualdad de atributo DNI para encontrar dicho cliente.
     *
     * @param dni
     * @return true si encuentra y elimina, false si no lo encuentra.
     * Lanza una excepcion personalizada si no encuentra al cliente.
     * @see Iterator
     * @see Boolean
     */
    @Override
    public boolean eliminar(int dni) throws ElementNotFoundException {
        boolean rta = false;
        Iterator<Cliente> it = listaDeClientes.iterator();
        int flag = 0;
        while (it.hasNext() && flag == 0) {
            Cliente aux = (Cliente) it.next();
            if (aux.getDni() == dni) {
                it.remove();
                flag = 1;
                rta = true;
            }
        }

        if (flag == 0) {
            throw new ElementNotFoundException("\nERROR - El cliente no fue encontrado o ya fue eliminado.\n");
        }

        return rta;
    }

    /**
     * Modifica atributos del cliente creado.
     * @param dni
     * @throws ElementUnmodifiedException
     */
    @Override
    public void modificar(int dni) throws ElementUnmodifiedException {
        try {
            Cliente aux = buscar(dni);
            if(aux != null)
            {
                eliminar(dni);

                char seguir = 0;
                int opcion = 0;
                do {
                    System.out.println("Cliente: ");
                    System.out.println("1.Nombre: "+aux.getNombre());
                    System.out.println("2.Apellido: "+aux.getApellido());
                    System.out.println("3.DNI: "+aux.getDni());
                    System.out.println("4.VIP: "+aux.isEsVip());
                    System.out.println("¿Que desea modificar?: ");
                    opcion = scan.nextInt();
                    switch (opcion)
                    {
                        case 1:
                            System.out.println("Ingrese el nombre: ");
                            scan.nextLine();
                            aux.setNombre(scan.nextLine());
                            break;
                        case 2:
                            System.out.println("Ingrese el apellido: ");
                            scan.nextLine();
                            aux.setApellido(scan.nextLine());
                            break;
                        case 3:
                            System.out.println("Ingrese el DNI: ");
                            aux.setDni(scan.nextInt());
                            break;
                        case 4:
                            int vip = 0;
                            do {
                                System.out.println("1.Hacerlo VIP. ");
                                System.out.println("2.Sacarle VIP. ");
                                System.out.println("Opcion: ");
                                vip = scan.nextInt();
                                switch (vip)
                                {
                                    case 1:
                                        scan.nextLine();
                                        aux.setEsVip(true);
                                        break;
                                    case 2:
                                        scan.nextLine();
                                        aux.setEsVip(false);
                                        break;
                                    default:
                                        System.out.println("Opcion invalida.");
                                }
                            }while(vip <= 0 || vip >= 3);
                            break;
                        default:
                            System.out.println("Opcion invalida.");
                    }

                    System.out.println("Desea modificar otro atributo del cliente? (s/n): ");
                    seguir = scan.nextLine().charAt(0);

                }while(seguir == 's');

                agregar(aux);

            }
            else
            {
                throw new ElementUnmodifiedException("El Cliente no existe.");
            }

        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ElementNotLoadedException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retorna la información de todos los objetos de tipo Cliente que se encuentren en la colección. Recorre y concatena un String con la información.
     *
     * @return String
     * @see Iterator
     */
    @Override
    public String listar() {
        String aux = "";
        Iterator<Cliente> it = listaDeClientes.iterator();
        while (it.hasNext()) {
            Cliente nuevoCliente = (Cliente) it.next();
            aux = aux + "\n" + nuevoCliente.toString();
        }
        return aux;
    }

    /**
     * Recorre la coleccion para buscar si existe un determinado Cliente, si existe lo retorna.
     * Lanza una excepcion personalizada si no encuentra al cliente.
     *
     * @param dni numero dni del Cliente.
     * @return Objeto Cliente si fue encontrado, sino retornara un Objeto nulo.
     */
    public Cliente buscar(int dni) throws ElementNotFoundException {

        Iterator<Cliente> it = listaDeClientes.iterator();

        int flag = 0;
        Cliente aux = null;

        while (it.hasNext() && flag == 0) {
            Cliente nuevo = (Cliente) it.next();
            if (nuevo.getDni() == dni) {
                flag = 1;
                aux = nuevo;
            }
        }

        if (aux == null) {
            throw new ElementNotFoundException("\nERROR - Cliente no encontrado.\n");
        }

        return aux;
    }
}
