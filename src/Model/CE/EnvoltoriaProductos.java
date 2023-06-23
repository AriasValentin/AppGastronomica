package Model.CE;

import Model.APIS.ConsumoAPIcafeCaliente;
import Model.APIS.ConsumoAPIcafeFrio;
import Model.Clases.Producto.Bebidas.Bebida;
import Model.Clases.Producto.Bebidas.TipoBebida;
import Model.Clases.Producto.Comidas.Comida;
import Model.Clases.Producto.Comidas.TipoComida;
import Model.Clases.Producto.Producto;
import Model.ExcepcionesPersonalizadas.ElementNotFoundException;
import Model.ExcepcionesPersonalizadas.ElementNotLoadedException;
import Model.ExcepcionesPersonalizadas.ElementUnmodifiedException;
import Model.Interfaces.IABM;
import Model.Clases.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Clase que envuelve todos los objetos de tipo Productos almacenados en una coleccion de tipo HashSet. Dicha coleecion es manipulada por la interfaz implementada IABM.
 *
 * @see HashSet
 * @see Producto
 */
public class EnvoltoriaProductos implements IABM<Producto> {

    Scanner scan = new Scanner(System.in);
    private HashSet<Producto> listaDeProductos;
    private int contadorId;

    public EnvoltoriaProductos() {

        this.listaDeProductos = new HashSet<>();
        this.contadorId = 0;
    }

    /**
     * Añade un objeto de tipo producto al HashSet.
     * Lanza una excepcion de tipo ElementNotLoadedException si el producto no pudo cargarse.
     *
     * @param unProducto objeto de tipo Producto.
     */
    @Override
    public void agregar(Producto unProducto) throws ElementNotLoadedException {

        if (unProducto != null) {
            listaDeProductos.add(unProducto);
        } else {
            throw new ElementNotLoadedException("\nERROR - El producto no pudo cargarse.\n");
        }
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
    public boolean eliminar(int id) throws ElementNotFoundException {
        boolean rta = false;
        Iterator<Producto> it = listaDeProductos.iterator();
        int flag = 0;
        while (it.hasNext() && flag == 0) {
            Producto aux = it.next();
            if (aux.getId() == id) {
                it.remove();
                flag = 1;
                rta = true;
            }
        }

        if (flag == 0) {
            throw new ElementNotFoundException("\nERROR - El producto no fue encontrado o ya fue eliminado.\n");
        }

        return rta;
    }


    /**
     * Modifica el precio de un producto.
     *
     * @param idProducto
     * @throws ElementUnmodifiedException
     */
    @Override
    public void modificar(int idProducto) throws ElementUnmodifiedException {
        try {
            Producto aux = buscar(idProducto);
            if (aux != null) {
                eliminar(idProducto);

                System.out.println(aux.toString());

                System.out.println("Ingrese el precio del producto a modificar: ");
                aux.setPrecio(scan.nextFloat());

                agregar(aux);

            }
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ElementNotLoadedException e) {
            System.out.println(e.getMessage());
        }
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
     * Recorre la coleccion para buscar si existe un determinado producto, si lo encuentra, se retorna.
     *
     * @param id numero id del producto.
     * @return Objeto Producto si fue encontrado, sino retornara un Objeto nulo.
     */
    public Producto buscar(int id) throws ElementNotFoundException {

        Iterator<Producto> it = listaDeProductos.iterator();

        int flag = 0;
        Producto aux = null;

        while (it.hasNext() && flag == 0) {
            Producto nuevo =  it.next();
            if (nuevo.getId() == id) {
                flag = 1;
                aux = nuevo;
            }
        }
        if (aux == null) {
            throw new ElementNotFoundException("\nERROR - Producto no encontrado.\n");
        }

        return aux;
    }

    /**
     * Ingresa a la coleccion los productos de tipo BEBIDA CALIENTE consumidos desde una API.
     *
     * @see Producto
     * @see TipoBebida
     * @see JSONArray
     * @see JSONObject
     */

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
        } catch (ElementNotLoadedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Ingresa a la coleccion los productos de tipo BEBIDA FRIA consumidos desde una API.
     *
     * @see Producto
     * @see TipoBebida
     * @see JSONArray
     * @see JSONObject
     */
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
        } catch (ElementNotLoadedException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Ingresa a la coleccion los productos de tipo COMIDA DULCE consumidos desde un archivo local JSON.
     *
     * @see Producto
     * @see TipoComida
     * @see JsonUtiles
     * @see JSONArray
     * @see JSONObject
     */
    public void consumoComidasDulces() {
        String jsonResponse = JsonUtiles.leer("ComidasDulces");

        try {
            JSONArray ja_raiz = new JSONArray(jsonResponse);
            for (int i = 0; i < ja_raiz.length(); i++) {
                JSONObject jo_comida = ja_raiz.getJSONObject(i);

                Comida nueva = new Comida();
                nueva.setNombre(jo_comida.getString("tittle"));
                nueva.setDescripcion(jo_comida.getString("description"));
                nueva.setPrecio(jo_comida.getInt("price"));
                nueva.setTipoComida(TipoComida.COMIDA_DULCE);
                nueva.setId(contadorId);
                contadorId++;
                agregar(nueva);
            }

        } catch (JSONException ex) {
            System.out.println("JSON mal creado " + ex.getMessage());
        } catch (ElementNotLoadedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Ingresa a la coleccion los productos de tipo COMIDA SALADA consumidos desde un archivo local JSON.
     *
     * @see Producto
     * @see TipoComida
     * @see JsonUtiles
     * @see JSONArray
     * @see JSONObject
     */
    public void consumoComidasSaladas() {
        String jsonResponse = JsonUtiles.leer("ComidasSaladas");

        try {
            JSONArray ja_raiz = new JSONArray(jsonResponse);
            for (int i = 0; i < ja_raiz.length(); i++) {
                JSONObject jo_comida = ja_raiz.getJSONObject(i);

                Comida nueva = new Comida();
                nueva.setNombre(jo_comida.getString("tittle"));
                nueva.setDescripcion(jo_comida.getString("description"));
                nueva.setPrecio(jo_comida.getInt("price"));
                nueva.setTipoComida(TipoComida.COMIDA_SALADA);
                nueva.setId(contadorId);
                contadorId++;
                agregar(nueva);
            }
        } catch (JSONException ex) {
            System.out.println("JSON mal creado " + ex.getMessage());
        } catch (ElementNotLoadedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Ingresa a la coleccion los productos de tipo BEBIDA FRIA consumidos desde un archivo local JSON.
     *
     * @see Producto
     * @see TipoBebida
     * @see JsonUtiles
     * @see JSONArray
     * @see JSONObject
     */
    public void consumoBebidasFriasPERSONAL() {
        String jsonResponse = JsonUtiles.leer("BebidasFrias");
        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Bebida nueva = new Bebida();
                nueva.setNombre(jsonObject.getString("tittle"));
                nueva.setDescripcion(jsonObject.getString("description"));
                nueva.setPrecio(jsonObject.getInt("price"));
                nueva.setTipoBebida(TipoBebida.BEBIDA_FRIA);
                nueva.setId(contadorId);
                contadorId++;
                agregar(nueva);
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
        } catch (ElementNotLoadedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Ingresa a la coleccion los productos de tipo BEBIDA CALIENTE consumidos desde un archivo local JSON.
     *
     * @see Producto
     * @see TipoBebida
     * @see JsonUtiles
     * @see JSONArray
     * @see JSONObject
     */
    public void consumoBebidasCalientesPersonal() {
        String jsonResponse = JsonUtiles.leer("BebidasCalientes");
        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Bebida nueva = new Bebida();
                nueva.setNombre(jsonObject.getString("tittle"));
                nueva.setDescripcion(jsonObject.getString("description"));
                nueva.setPrecio(jsonObject.getInt("price"));
                nueva.setTipoBebida(TipoBebida.BEBIDA_CALIENTE);
                nueva.setId(contadorId);
                contadorId++;
                agregar(nueva);

            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());

        } catch (ElementNotLoadedException ex) {
            System.out.println(ex.getMessage());
        }


    }


    public void consumoJSON() {
        consumoComidasDulces();
        consumoComidasSaladas();
        consumoBebidasFriasPERSONAL();
        consumoBebidasCalientesPersonal();

        //consumoBebidasCalientes();
        //consumoBebidasFrias();

    }

    public void vaciarLista() {
        listaDeProductos.clear();
    }

    public String listarBebidasFrias() {
        String aux = "BEBIDAS FRIAS : \n";
        Iterator<Producto> it = listaDeProductos.iterator();
        while (it.hasNext()) {
            Producto nuevo = it.next();
            if (nuevo instanceof Bebida) {
                if (((Bebida) nuevo).getTipoBebida().equals(TipoBebida.BEBIDA_FRIA)) {
                    aux += nuevo.toString() + "\n";

                }

            }
        }
        return aux;
    }

    public String listarBebidasCalientes() {
        String aux = "BEBIDAS CALIENTES : \n";
        Iterator<Producto> it = listaDeProductos.iterator();
        while (it.hasNext()) {
            Producto nuevo = it.next();
            if (nuevo instanceof Bebida) {
                if (((Bebida) nuevo).getTipoBebida().equals(TipoBebida.BEBIDA_CALIENTE)) {
                    aux += nuevo.toString() + "\n";

                }

            }
        }
        return aux;
    }

    public String listarComidasSaladas() {
        String aux = "Comidas Saladas : \n";
        Iterator<Producto> it = listaDeProductos.iterator();
        while (it.hasNext()) {
            Producto nuevo = it.next();
            if (nuevo instanceof Comida) {
                if (((Comida) nuevo).getTipoComida().equals(TipoComida.COMIDA_SALADA))
                {
                    aux += nuevo.toString() + "\n";

                }

            }
        }
        return aux;
    }

    public String listarComidasDulces() {
        String aux = "Comidas Dulces : \n";
        Iterator<Producto> it = listaDeProductos.iterator();
        while (it.hasNext()) {
            Producto nuevo = it.next();
            if (nuevo instanceof Comida) {
                if ( ((Comida) nuevo).getTipoComida().equals(TipoComida.COMIDA_DULCE))
                {
                    aux += nuevo.toString() + "\n";

                }

            }
        }
        return aux;
    }

    @Override
    public Iterator<Producto> devolverIterador() {
        Iterator<Producto> it = listaDeProductos.iterator();
        return it;
    }
}
