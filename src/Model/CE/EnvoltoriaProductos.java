package Model.CE;

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

import java.util.HashSet;
import java.util.Iterator;


/**
 * Clase que envuelve todos los objetos de tipo Productos almacenados en una coleccion de tipo HashSet. Dicha coleecion es manipulada por la interfaz implementada IABM.
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
     * @param nuevoPrecio
     * @param unProducto
     * @throws ElementUnmodifiedException
     */
    @Override
    public void modificar(Producto unProducto, float nuevoPrecio) throws ElementUnmodifiedException, ElementNotFoundException, ElementNotLoadedException {
        if (unProducto != null) {

            Producto aux = unProducto;
            eliminar(unProducto.getId());
            aux.setPrecio(nuevoPrecio);
            agregar(aux);

        } else {
            throw new ElementUnmodifiedException("\nERROR - El elemento no pudo ser modificado.\n");
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

    public String listarNombreID() {
        String aux = "";
        Iterator<Producto> it = listaDeProductos.iterator();
        while (it.hasNext()) {
            Producto nuevoProducto = (Producto) it.next();
            aux = aux + "\n" + nuevoProducto.toStringNombreID() + "\n";
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
            Producto nuevo = it.next();
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
    public void consumoBebidasFriasPersonal() {
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
        consumoBebidasFriasPersonal();
        consumoBebidasCalientesPersonal();
    }

    public void agregarComidaAJson(Comida nueva) throws JSONException {

        HashSet<Producto> listaComida = new HashSet<>();

        if (nueva != null) {
            if (nueva.getTipoComida() == TipoComida.COMIDA_DULCE) {
                //me traigo el json
                String jsonResponse = JsonUtiles.leer("ComidasDulces");

                //lo leo
                JSONArray jsonArray = new JSONArray(jsonResponse);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Comida aux = new Comida();
                    aux.setNombre(jsonObject.getString("tittle"));
                    aux.setDescripcion(jsonObject.getString("description"));
                    aux.setPrecio(jsonObject.getInt("price"));
                    aux.setTipoComida(TipoComida.COMIDA_DULCE);
                    aux.setId(contadorId);
                    contadorId++;

                    listaComida.add(aux);
                }
                //agrego el nuevo producto a la lista
                listaComida.add(nueva);

                //paso la lista al json para que se actualize
                JSONArray jsonArray_ComidasDulces = new JSONArray();


                for (Producto aux : listaComida) {
                    JSONObject jo_comidaDulce = new JSONObject();
                    jo_comidaDulce.put("tittle", aux.getNombre());
                    jo_comidaDulce.put("description", aux.getDescripcion());
                    jo_comidaDulce.put("price", aux.getPrecio());

                    jsonArray_ComidasDulces.put(jo_comidaDulce);
                }

                JsonUtiles.grabar(jsonArray_ComidasDulces, "ComidasDulces");

            } else if (nueva.getTipoComida() == TipoComida.COMIDA_SALADA) {
                //me traigo el json
                String jsonResponse = JsonUtiles.leer("ComidasSaladas");

                //lo leo
                JSONArray jsonArray = new JSONArray(jsonResponse);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Comida aux = new Comida();
                    aux.setNombre(jsonObject.getString("tittle"));
                    aux.setDescripcion(jsonObject.getString("description"));
                    aux.setPrecio(jsonObject.getInt("price"));
                    aux.setTipoComida(TipoComida.COMIDA_SALADA);
                    aux.setId(contadorId);
                    contadorId++;

                    listaComida.add(aux);
                }
                //agrego el nuevo producto a la lista
                listaComida.add(nueva);

                //paso la lista al json para que se actualize
                JSONArray jsonArray_ComidasDulces = new JSONArray();


                for (Producto aux : listaComida) {
                    JSONObject jo_comidaDulce = new JSONObject();

                    jo_comidaDulce.put("tittle", aux.getNombre());
                    jo_comidaDulce.put("description", aux.getDescripcion());
                    jo_comidaDulce.put("price", aux.getPrecio());

                    jsonArray_ComidasDulces.put(jo_comidaDulce);
                }

                JsonUtiles.grabar(jsonArray_ComidasDulces, "ComidasDulces");
            }
        }

    }

    public void agregarBebidaAJson(Bebida nueva) throws JSONException {

        HashSet<Producto> listaBebida = new HashSet<>();

        if (nueva != null) {
            if (nueva.getTipoBebida() == TipoBebida.BEBIDA_FRIA) {
                //me traigo el json
                String jsonResponse = JsonUtiles.leer("BebidasFrias");

                //lo leo
                JSONArray jsonArray = new JSONArray(jsonResponse);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Bebida aux = new Bebida();
                    aux.setNombre(jsonObject.getString("tittle"));
                    aux.setDescripcion(jsonObject.getString("description"));
                    aux.setPrecio(jsonObject.getInt("price"));
                    aux.setTipoBebida(TipoBebida.BEBIDA_FRIA);
                    aux.setId(contadorId);
                    contadorId++;

                    listaBebida.add(aux);
                }
                //agrego el nuevo producto a la lista
                listaBebida.add(nueva);

                //paso la lista al json para que se actualize
                JSONArray jsonArray_BebidasFrias = new JSONArray();

                for (Producto aux : listaBebida) {
                    JSONObject jo_bebidaFria = new JSONObject();
                    jo_bebidaFria.put("tittle", aux.getNombre());
                    jo_bebidaFria.put("description", aux.getDescripcion());
                    jo_bebidaFria.put("price", aux.getPrecio());

                    jsonArray_BebidasFrias.put(jo_bebidaFria);
                }

                JsonUtiles.grabar(jsonArray_BebidasFrias, "BebidasFrias");

            } else if (nueva.getTipoBebida() == TipoBebida.BEBIDA_CALIENTE) {
                //me traigo el json
                String jsonResponse = JsonUtiles.leer("BebidasCalientes");

                //lo leo
                JSONArray jsonArray = new JSONArray(jsonResponse);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    Bebida aux = new Bebida();
                    aux.setNombre(jsonObject.getString("tittle"));
                    aux.setDescripcion(jsonObject.getString("description"));
                    aux.setPrecio(jsonObject.getInt("price"));
                    aux.setTipoBebida(TipoBebida.BEBIDA_CALIENTE);
                    aux.setId(contadorId);
                    contadorId++;

                    listaBebida.add(aux);
                }
                //agrego el nuevo producto a la lista
                listaBebida.add(nueva);

                //paso la lista al json para que se actualize
                JSONArray jsonArray_BebidasCalientes = new JSONArray();


                for (Producto aux : listaBebida) {
                    JSONObject jo_bebidaCaliente = new JSONObject();

                    jo_bebidaCaliente.put("tittle", aux.getNombre());
                    jo_bebidaCaliente.put("description", aux.getDescripcion());
                    jo_bebidaCaliente.put("price", aux.getPrecio());

                    jsonArray_BebidasCalientes.put(jo_bebidaCaliente);
                }

                JsonUtiles.grabar(jsonArray_BebidasCalientes, "BebidasCalientes");
            }
        }
    }

    public void eliminarComidaJson(Comida unaComida) throws JSONException {

        HashSet<Producto> listaComida = new HashSet<>();

        if (unaComida != null) {
            if (unaComida.getTipoComida() == TipoComida.COMIDA_DULCE) {
                //traigo el json
                String jsonResponse = JsonUtiles.leer("ComidasDulces");

                JSONArray ja_comidas = new JSONArray(jsonResponse);
                for (int i = 0; i < ja_comidas.length(); i++) {
                    JSONObject jsonObject = ja_comidas.getJSONObject(i);

                    Comida aux = new Comida();
                    aux.setNombre(jsonObject.getString("tittle"));
                    aux.setDescripcion(jsonObject.getString("description"));
                    aux.setPrecio(jsonObject.getInt("price"));
                    aux.setTipoComida(TipoComida.COMIDA_DULCE);
                    aux.setId(contadorId);
                    contadorId++;

                    //si es distinto el nombre y descripcion a la comida que me llega por parametro, la agrego a mi lista local...
                    if (!aux.getNombre().equals(unaComida.getNombre()) && !aux.getDescripcion().equals(unaComida.getDescripcion())) {
                        listaComida.add(aux);
                    }

                }
                //sobreescribo el json
                JSONArray ja_actualizada = new JSONArray();

                for (Producto aux : listaComida) {
                    JSONObject jo_comidaDulce = new JSONObject();

                    jo_comidaDulce.put("tittle", aux.getNombre());
                    jo_comidaDulce.put("description", aux.getDescripcion());
                    jo_comidaDulce.put("price", aux.getPrecio());

                    ja_actualizada.put(jo_comidaDulce);
                }

                JsonUtiles.grabar(ja_actualizada, "ComidasDulces");

            } else if (unaComida.getTipoComida() == TipoComida.COMIDA_SALADA) {
                //traigo el json
                String JsonResponse = JsonUtiles.leer("ComidasSaladas");

                JSONArray ja_comidas = new JSONArray(JsonResponse);
                for (int i = 0; i < ja_comidas.length(); i++) {
                    JSONObject jsonObject = ja_comidas.getJSONObject(i);

                    Comida aux = new Comida();
                    aux.setNombre(jsonObject.getString("tittle"));
                    aux.setDescripcion(jsonObject.getString("description"));
                    aux.setPrecio(jsonObject.getInt("price"));
                    aux.setTipoComida(TipoComida.COMIDA_SALADA);
                    aux.setId(contadorId);
                    contadorId++;

                    //si es distinto el nombre y descripcion a la comida que me llega por parametro, la agrego a mi lista local...
                    if (!aux.getNombre().equals(unaComida.getNombre()) && !aux.getDescripcion().equals(unaComida.getDescripcion())) {
                        listaComida.add(aux);
                    }
                }
                //sobreescribo el json
                JSONArray ja_actualizada = new JSONArray();

                for (Producto aux2 : listaComida) {
                    JSONObject jo_comidaDulce = new JSONObject();

                    jo_comidaDulce.put("tittle", aux2.getNombre());
                    jo_comidaDulce.put("description", aux2.getDescripcion());
                    jo_comidaDulce.put("price", aux2.getPrecio());

                    ja_actualizada.put(jo_comidaDulce);
                }

                JsonUtiles.grabar(ja_actualizada, "ComidasSaladas");

            }
        }
    }


    public void eliminarBebidaJson(Bebida unaBebida) throws JSONException {

        HashSet<Producto> listaBebida = new HashSet<>();
        if (unaBebida != null) {
            if (unaBebida.getTipoBebida() == TipoBebida.BEBIDA_FRIA) {
                String JsonResponse = JsonUtiles.leer("BebidasFrias");

                JSONArray ja_bebidasFrias = new JSONArray(JsonResponse);

                for (int i = 0; i < ja_bebidasFrias.length(); i++) {
                    JSONObject jo_bebidas = ja_bebidasFrias.getJSONObject(i);

                    Bebida aux = new Bebida();
                    aux.setNombre(jo_bebidas.getString("tittle"));
                    aux.setDescripcion(jo_bebidas.getString("description"));
                    aux.setPrecio(jo_bebidas.getInt("price"));
                    aux.setTipoBebida(TipoBebida.BEBIDA_FRIA);
                    aux.setId(contadorId);
                    contadorId++;

                    //si es distinto el nombre y descripcion a la comida que me llega por parametro, la agrego a mi lista local...
                    if (!aux.getNombre().equals(unaBebida.getNombre()) && !aux.getDescripcion().equals(unaBebida.getDescripcion())) {
                        listaBebida.add(aux);
                    }
                }
                JSONArray ja_actualizada = new JSONArray();

                for (Producto aux2 : listaBebida) {
                    JSONObject jo_bebidaFria = new JSONObject();

                    jo_bebidaFria.put("tittle", aux2.getNombre());
                    jo_bebidaFria.put("description", aux2.getDescripcion());
                    jo_bebidaFria.put("price", aux2.getPrecio());

                    ja_actualizada.put(jo_bebidaFria);
                }

                JsonUtiles.grabar(ja_actualizada, "BebidasFrias");
            } else if (unaBebida.getTipoBebida() == TipoBebida.BEBIDA_CALIENTE) {
                String JsonResponse = JsonUtiles.leer("BebidasCalientes");

                JSONArray ja_BebidasCalientes = new JSONArray(JsonResponse);

                for (int i = 0; i < ja_BebidasCalientes.length(); i++) {
                    JSONObject jo_bebidas = ja_BebidasCalientes.getJSONObject(i);

                    Bebida aux = new Bebida();
                    aux.setNombre(jo_bebidas.getString("tittle"));
                    aux.setDescripcion(jo_bebidas.getString("description"));
                    aux.setPrecio(jo_bebidas.getInt("price"));
                    aux.setTipoBebida(TipoBebida.BEBIDA_CALIENTE);
                    aux.setId(contadorId);
                    contadorId++;

                    //si es distinto el nombre y descripcion a la comida que me llega por parametro, la agrego a mi lista local...
                    if (!aux.getNombre().equals(unaBebida.getNombre()) && !aux.getDescripcion().equals(unaBebida.getDescripcion())) {
                        listaBebida.add(aux);
                    }
                }
                JSONArray ja_actualizada = new JSONArray();

                for (Producto aux2 : listaBebida) {
                    JSONObject jo_bebidaCaliente = new JSONObject();

                    jo_bebidaCaliente.put("tittle", aux2.getNombre());
                    jo_bebidaCaliente.put("description", aux2.getDescripcion());
                    jo_bebidaCaliente.put("price", aux2.getPrecio());

                    ja_actualizada.put(jo_bebidaCaliente);
                }

                JsonUtiles.grabar(ja_actualizada, "BebidasCalientes");
            }
        }
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
                if (((Comida) nuevo).getTipoComida().equals(TipoComida.COMIDA_SALADA)) {
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
                if (((Comida) nuevo).getTipoComida().equals(TipoComida.COMIDA_DULCE)) {
                    aux += nuevo.toString() + "\n";
                }
            }
        }
        return aux;
    }

}
