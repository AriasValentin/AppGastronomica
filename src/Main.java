import Model.CE.EnvoltoriaClientes;
import Model.CE.EnvoltoriaProductos;
import Model.CE.EnvoltoriaVentas;
import Model.CE.NegocioEnvoltorio;
import Model.Clases.Producto.Comidas.Comida;
import Model.Clases.Producto.Comidas.TipoComida;
import Model.Clases.Producto.Producto;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Main {
    public static void main(String[] args) {

      EnvoltoriaProductos envoltoriaProductos = new EnvoltoriaProductos();
        EnvoltoriaVentas envoltoriaVentas = new EnvoltoriaVentas();
        EnvoltoriaClientes envoltoriaClientes = new EnvoltoriaClientes();
        Producto p1 = new Comida("pizza",1,0,"rico", TipoComida.COMIDA_SALADA);
        try
        {
            envoltoriaProductos.agregar(p1);
        }
        catch (ElementNotLoadedException ex)
        {
            System.out.println(ex.getMessage());

        }
        System.out.println(envoltoriaProductos.listar());

    }
}