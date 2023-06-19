import Model.CE.EnvoltoriaClientes;
import Model.CE.EnvoltoriaProductos;
import Model.CE.EnvoltoriaVentas;
import Model.CE.NegocioEnvoltorio;
import Model.Clases.Cliente;
import Model.Clases.Producto.Comidas.Comida;
import Model.Clases.Producto.Comidas.TipoComida;
import Model.Clases.Producto.Producto;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Main {
    public static void main(String[] args) {

        EnvoltoriaClientes clientes = new EnvoltoriaClientes();
        Cliente nuevo = new Cliente("Fede","Falo",40095867,false);

        try {
            clientes.agregar(nuevo);
            System.out.println(clientes.listar());

            clientes.modificar(40095867);

        } catch (ElementNotLoadedException e) {
            System.out.println(e.getMessage());
        } catch (ElementUnmodifiedException e)
        {
            System.out.println(e.getMessage());
        }

    }
}