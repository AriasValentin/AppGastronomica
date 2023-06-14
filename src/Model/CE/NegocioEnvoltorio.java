package Model.CE;

public class NegocioEnvoltorio {
    private EnvoltoriaClientes clientes;
    private EnvoltoriaProductos productos;
    private EnvoltoriaVentas ventas;

    public NegocioEnvoltorio()
    {
        clientes = new EnvoltoriaClientes();
        productos = new EnvoltoriaProductos();
        ventas = new EnvoltoriaVentas();
    }

}
