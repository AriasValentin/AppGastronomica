package Model.Clases;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GrabadoraYLectoraArchivos{

    public static void persistirVentas(ArrayList<Venta> listaVenta) {

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream("ventas.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for(Venta aux : listaVenta)
            {

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
    public static  ArrayList<Venta> leerVentas()
    {
        ArrayList<Venta> arrayList = new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try
        {
            fileInputStream = new FileInputStream("ventas.dat");
            objectInputStream = new ObjectInputStream(fileInputStream);

           while(true)
           {
               Venta venta = (Venta) objectInputStream.readObject();
               arrayList.add(venta);

           }
        }
        catch (EOFException ex1)
        {
            System.out.println(ex1.getMessage());
        }
        catch (ClassNotFoundException ex2)
        {
            System.out.println(ex2.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        finally {
            try
            {
                if(fileInputStream != null)
                {
                    fileInputStream.close();
                }
                if(objectInputStream != null)
                {
                    objectInputStream.close();
                }

            }
            catch (IOException ex5)
            {
                System.out.println(ex5.getMessage());
            }
        }
        return arrayList;
    }



}
