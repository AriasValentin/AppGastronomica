package Model.Clases;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

public class Grabadora <T>{


    public void persistirObjeto(Iterator<T> it, String nombreArchivo) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(nombreArchivo);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            while(it.hasNext())
            {
                objectOutputStream.writeObject(it.next());
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
