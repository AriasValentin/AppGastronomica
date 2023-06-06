import Model.CE.EnvoltoriaProductos;
import Model.Clases.LugarConsumo;
import Model.Clases.Venta;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EnvoltoriaProductos claseENVproductos = new EnvoltoriaProductos();
        claseENVproductos.consumoBebidasComidasJSON();
        System.out.println(claseENVproductos.listar());


    }
}