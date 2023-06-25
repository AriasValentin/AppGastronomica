package Model.Clases;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;

public class GrabadoraYLectoraArchivos {

    public static void persistirVentas(ArrayList<Venta> listaVenta) {

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("ventas.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Venta aux : listaVenta) {
                objectOutputStream.writeObject(aux);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            {
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (objectOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void persistirClientes(HashMap<Integer,Cliente> mapaCliente){
        FileOutputStream file = null;
        ObjectOutputStream object = null;

        try {
            file = new FileOutputStream("clientes.dat");
            object = new ObjectOutputStream(file);


            Iterator<Map.Entry<Integer,Cliente>> it = mapaCliente.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<Integer,Cliente> entrada = it.next();

                object.writeObject(entrada.getValue());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (file != null){
                    file.close();
                }

                if (object != null){
                    object.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<Venta> leerVentas() {
        ArrayList<Venta> arrayList = new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream("ventas.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

            while (true) {
                Venta venta = (Venta) objectInputStream.readObject();
                arrayList.add(venta);

            }
        } catch (EOFException ex1) {
            System.out.println(ex1.getMessage());
        } catch (ClassNotFoundException ex2) {
            System.out.println(ex2.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException ex5) {
                System.out.println(ex5.getMessage());
            }
        }
        return arrayList;
    }

    public static HashMap<Integer,Cliente> leerClientes(){
        HashMap<Integer,Cliente> setClientes = new HashMap<>();
        FileInputStream file = null;
        ObjectInputStream object = null;

        try {
            file = new FileInputStream("clientes.dat");
            object = new ObjectInputStream(file);

            while (true){
                Cliente unCliente = (Cliente) object.readObject();
                setClientes.put(unCliente.getDni(), unCliente);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (file != null){
                    file.close();
                }

                if (object != null){
                    object.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return setClientes;
    }
}
