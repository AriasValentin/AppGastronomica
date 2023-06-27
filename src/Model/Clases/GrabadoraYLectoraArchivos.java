package Model.Clases;

import java.io.*;
import java.util.*;

public class GrabadoraYLectoraArchivos {

    //Metodos de persistencia.
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
            e.getMessage();
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
                    e.getMessage();
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
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                if (file != null){
                    file.close();
                }

                if (object != null){
                    object.close();
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    //Metodos de lectura.
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
        } catch (EOFException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.getMessage();
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
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        } finally {
            try {
                if (file != null){
                    file.close();
                }

                if (object != null){
                    object.close();
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }

        return setClientes;
    }
}
