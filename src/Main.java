
import Model.CE.EnvoltoriaProductos;
import Model.CE.NegocioEnvoltorio;
import Model.Clases.Venta;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;

public class Main {
    public static void main(String[] args) {

        EnvoltoriaProductos envoltoriaProductos = new EnvoltoriaProductos();
        envoltoriaProductos.consumoJSON();
      /*  System.out.println(envoltoriaProductos.listarBebidasFrias());
        System.out.println(envoltoriaProductos.listarBebidasCalientes());
        System.out.println(envoltoriaProductos.listarComidasSaladas());
        System.out.println(envoltoriaProductos.listarComidasDulces());*/


        //pureba de venta
        NegocioEnvoltorio nuevoNegocio = new NegocioEnvoltorio();

        Venta nueva = new Venta();
        try {
            nueva = nuevoNegocio.unaVenta();
            System.out.println("PRECIO TOTAL: "+nueva.getTotal());
        } catch (ElementNotLoadedException e) {
            System.out.println(e.getMessage());
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}