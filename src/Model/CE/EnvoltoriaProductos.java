package Model.CE;

import Model.APIS.ConsumoAPIcafeCaliente;
import Model.APIS.ConsumoAPIcafeFrio;
import Model.Clases.Producto.Bebidas.Bebida;
import Model.Clases.Producto.Bebidas.TipoBebida;
import Model.Clases.Producto.Comidas.Comida;
import Model.Clases.Producto.Comidas.TipoComida;
import Model.Clases.Producto.Producto;
import Model.Interfaces.IABM;
import Model.CE.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Clase que envuelve todas los objetos de tipo Productos almacenados en una coleccion de tipo HashSet. Dicha coleecion es manipulada por la interfaz implementada IABM.
 *
 * @see HashSet
 * @see Producto
 */
public class EnvoltoriaProductos implements IABM<Producto> {

    private HashSet<Producto> listaDeProductos;
    private int contadorId;

    public EnvoltoriaProductos() {

        this.listaDeProductos = new HashSet<>();
        this.contadorId = 0;
    }

    /**
     * Añade un objeto de tipo producto al HashSet.
     *
     * @param unProducto objeto de tipo Producto.
     */
    @Override
    public void agregar(Producto unProducto) {
        listaDeProductos.add(unProducto);
    }

    /**
     * Elimina un objeto de tipo Producto al HashSet. Recorre con un iterador la collecion y verifica si se cumple igualdad de atributo ID para encontrar dicho producto.
     *
     * @param id
     * @return true si encuentra y elimina, false si no lo encuentra.
     * @see Iterator
     * @see Boolean
     */
    @Override
    public boolean eliminar(int id) {
        boolean rta = false;
        Iterator<Producto> it = listaDeProductos.iterator();
        int flag = 0;
        while (it.hasNext() && flag == 0) {
            Producto aux = (Producto) it.next();
            if (aux.getId() == id) {
                it.remove();
                flag = 1;
                rta = true;
            }
        }
        return rta;
    }

    @Override
    public void modificar(int elemento) {

    }

    /**
     * Retorna la información de todos los objetos de tipo Producto que se encuentren en la colección. Recorre y concatena un String con la información.
     *
     * @return String
     * @see Iterator
     */
    @Override
    public String listar() {
        String aux = "";
        Iterator<Producto> it = listaDeProductos.iterator();
        while (it.hasNext()) {
            Producto nuevoProducto = (Producto) it.next();
            aux = aux + "\n" + nuevoProducto.toString() + "\n";
        }
        return aux;
    }

    /**
     * Recorre la coleccion para buscar si existe un determinado producto.
     *
     * @param id numero id del producto.
     * @return true si encuentra el producto, false si no.
     */
    public boolean buscar(int id) {
        boolean rta = false;
        Iterator<Producto> it = listaDeProductos.iterator();
        int flag = 0;
        while (it.hasNext() && flag == 0) {
            Producto nuevo = (Producto) it.next();
            if (nuevo.getId() == id) {
                flag = 1;
                rta = true;
            }
        }
        return rta;
    }

    private void consumoBebidasCalientes() {
        try {
            JSONArray jsonArray = new JSONArray(ConsumoAPIcafeCaliente.getInfo());
            for (int i = 0; i < jsonArray.length(); i++) {
                Bebida nueva = new Bebida();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nueva.setNombre(jsonObject.getString("title"));
                nueva.setDescripcion(jsonObject.getString("description"));
                nueva.setTipoBebida(TipoBebida.BEBIDA_CALIENTE);
                nueva.setId(contadorId);
                contadorId++;
                agregar(nueva);
            }
        } catch (JSONException e) {
            System.out.println("API MAL PROCESADA." + e.getMessage());
        }
    }

    private void consumoBebidasFrias() {
        try {
            JSONArray jsonArray = new JSONArray(ConsumoAPIcafeFrio.getInfo());
            for (int i = 0; i < jsonArray.length(); i++) {
                Bebida nueva = new Bebida();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nueva.setNombre(jsonObject.getString("title"));
                nueva.setDescripcion(jsonObject.getString("description"));
                nueva.setTipoBebida(TipoBebida.BEBIDA_FRIA);
                nueva.setId(contadorId);
                contadorId++;
                agregar(nueva);
            }
        } catch (JSONException e) {
            System.out.println("API MAL PROCESADA." + e.getMessage());
        }

    }

    private void consumoComidasDulces()
    {
        String jsonResponse = JsonUtiles.leer("ComidasDulces");

        try
        {
            JSONArray ja_raiz = new JSONArray(jsonResponse);
            for(int i=0;i<ja_raiz.length();i++)
            {
                JSONObject jo_comida = ja_raiz.getJSONObject(i);

                Comida nueva = new Comida();
                nueva.setNombre(jo_comida.getString("tittle"));
                nueva.setDescripcion(jo_comida.getString("description"));
                nueva.setTipoComida(TipoComida.COMIDA_DULCE);
                nueva.setId(contadorId);
                contadorId++;
                agregar(nueva);
            }

        }catch(JSONException ex)
        {
            System.out.println("JSON mal creado "+ex.getMessage());
        }
    }

    private void consumoComidasSaladas()
    {
        String jsonResponse = JsonUtiles.leer("ComidasSaladas");

        try
        {
            JSONArray ja_raiz = new JSONArray(jsonResponse);
            for(int i=0;i< ja_raiz.length();i++)
            {
                JSONObject jo_comida = ja_raiz.getJSONObject(i);

                Comida nueva = new Comida();
                nueva.setNombre(jo_comida.getString("tittle"));
                nueva.setDescripcion(jo_comida.getString("description"));
                nueva.setTipoComida(TipoComida.COMIDA_SALADA);
                nueva.setId(contadorId);
                contadorId++;
                agregar(nueva);
            }
        }catch (JSONException ex)
        {
            System.out.println("JSON mal creado "+ex.getMessage());
        }
    }

    public void consumoBebidasComidasJSON() {
        consumoComidasDulces();
        consumoComidasSaladas();
        consumoBebidasCalientes();
        consumoBebidasFrias();

    }


//TESTEO DE IMPRIMIR ENUM
    /*public String listarBebidasCalientes() {
        String aux = "";
        Iterator <Producto> it = listaDeProductos.iterator();
        while(it.hasNext())
        {
            Producto aux2 = it.next();
            if(((Bebida)aux2).getTipoBebida() == TipoBebida.BEBIDA_CALIENTE)
            {
                aux = aux+ aux2.toString() +"\n";

            }
        }
        return aux;

    }

     */
}
