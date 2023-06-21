
import Model.CE.EnvoltoriaProductos;
import Model.CE.NegocioEnvoltorio;

public class Main {
    public static void main(String[] args) {

        EnvoltoriaProductos envoltoriaProductos = new EnvoltoriaProductos();
        envoltoriaProductos.consumoJSON();
        System.out.println(envoltoriaProductos.listarBebidasFrias());
        System.out.println(envoltoriaProductos.listarBebidasCalientes());
        System.out.println(envoltoriaProductos.listarComidasSaladas());
        System.out.println(envoltoriaProductos.listarComidasDulces());
    }
}