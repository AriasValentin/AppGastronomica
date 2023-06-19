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

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Main {
    public static void main(String[] args) {
        EnvoltoriaClientes envoltoriaClientes = new EnvoltoriaClientes();
        Cliente cliente1 = new Cliente("Valentin", "Arias", 38395628, true);
        Cliente cliente2 = new Cliente("Federico", "Falotiche", 39415623, true);
        try {
            envoltoriaClientes.agregar(cliente1);
            envoltoriaClientes.agregar(cliente2);
        } catch (ElementNotLoadedException ex) {
            ex.getMessage();
        }
        System.out.println(envoltoriaClientes.listar());
        try {
            envoltoriaClientes.eliminar(39415623);
        } catch (ElementNotFoundException ex) {
            ex.getMessage();
        }

        System.out.println(envoltoriaClientes.listar());
    }

}