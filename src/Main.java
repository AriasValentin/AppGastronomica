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

        EnvoltoriaProductos envoltoriaProductos = new EnvoltoriaProductos();
        envoltoriaProductos.consumoJSON();
        System.out.println(envoltoriaProductos.listar());

    }
}