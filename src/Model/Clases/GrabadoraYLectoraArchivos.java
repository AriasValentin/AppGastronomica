package Model.Clases;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class GrabadoraYLectoraArchivos{

    public static void persistirVentas(ArrayList<Venta> listaVenta, String nombreArchivo) {

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(nombreArchivo);
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



}
