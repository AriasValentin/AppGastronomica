package Model.Clases;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Clase que lee y persiste archivos de colecciones de distinto tipos.
 */
public class GrabadoraYLectoraArchivos {

    //Metodos de persistencia.

    /**
     * Persiste la coleccion de tipo Venta en un archivo.
     *
     * @param listaVenta Coleccion de tipo Venta.
     */
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

    /**
     * Persiste la coleccion de tipo Cliente en un archivo.
     *
     * @param mapaCliente Coleccion de tipo Venta.
     */
    public static void persistirClientes(HashMap<Integer, Cliente> mapaCliente) {
        FileOutputStream file = null;
        ObjectOutputStream object = null;

        try {
            file = new FileOutputStream("clientes.dat");
            object = new ObjectOutputStream(file);


            Iterator<Map.Entry<Integer, Cliente>> it = mapaCliente.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Cliente> entrada = it.next();

                object.writeObject(entrada.getValue());
            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                if (file != null) {
                    file.close();
                }

                if (object != null) {
                    object.close();
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Persiste las claves usuario y password del administrador en un archivo.
     *
     * @param user     String que almacena el usuaria.
     * @param password String que almacena la constraseña.
     */
    public static void persistirClaveAdmin(String user, String password) {

        FileOutputStream file = null;
        DataOutputStream data = null;

        try {
            file = new FileOutputStream("admin.dat");
            data = new DataOutputStream(file);

            data.writeUTF(user);
            data.writeUTF(password);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (file != null) {
                    file.close();
                }

                if (data != null) {
                    data.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Metodos de lectura.

    /**
     * Lee los datos de un archivo y los agrega a una coleccion del mismo tipo que envoltoriaVenta.
     *
     * @return ArrayList de tipo Venta.
     */
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

    /**
     * Lee los datos de un archivo y los agrega a una coleccion del mismo tipo que envoltoriaCliente.
     *
     * @return HashMap de tipo Integer,Cliente.
     */
    public static HashMap<Integer, Cliente> leerClientes() {
        HashMap<Integer, Cliente> setClientes = new HashMap<>();
        FileInputStream file = null;
        ObjectInputStream object = null;

        try {
            file = new FileInputStream("clientes.dat");
            object = new ObjectInputStream(file);

            while (true) {
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
                if (file != null) {
                    file.close();
                }

                if (object != null) {
                    object.close();
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }

        return setClientes;
    }

    /**
     * Lee los datos de un archivo y los agrega a una coleccion del mismo tipo que userPassword.
     *
     * @return ArrayList de tipo String.
     */
    public static ArrayList<String> leerClaveAdmin() {

        ArrayList<String> arrayList = new ArrayList<>();

        FileInputStream file = null;
        DataInputStream data = null;

        try {
            file = new FileInputStream("admin.dat");
            data = new DataInputStream(file);

            while (true) {
                arrayList.add(data.readUTF());
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (file != null) {
                    file.close();
                }

                if (data != null) {
                    data.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        
        return arrayList;
    }
}
