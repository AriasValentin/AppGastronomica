package Model.Clases;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

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

    public static void persistirClientes(LinkedHashSet<Cliente> setCliente){
        FileOutputStream file = null;
        ObjectOutputStream object = null;

        try {
            file = new FileOutputStream("clientes.dat");
            object = new ObjectOutputStream(file);

            for (Cliente aux : setCliente){
                object.writeObject(aux);
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

    public static LinkedHashSet<Cliente> leerClientes(){
        LinkedHashSet<Cliente> setClientes = new LinkedHashSet<>();
        FileInputStream file = null;
        ObjectInputStream object = null;

        try {
            file = new FileInputStream("clientes.dat");
            object = new ObjectInputStream(file);

            while (true){
                Cliente unCliente = (Cliente) object.readObject();
                setClientes.add(unCliente);
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
