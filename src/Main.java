import Model.CE.EnvoltoriaProductos;
import Model.Clases.LugarConsumo;
import Model.Clases.Venta;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EnvoltoriaProductos claseENVproductos = new EnvoltoriaProductos();
        claseENVproductos.consumoApisBebidas();
        System.out.println(claseENVproductos.listar());
    }
}