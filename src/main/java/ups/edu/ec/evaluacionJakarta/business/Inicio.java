package ups.edu.ec.evaluacionJakarta.business;

import java.util.List;



import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import ups.edu.ec.evaluacionJakarta.dao.ClienteDAO;
import ups.edu.ec.evaluacionJakarta.model.Cliente;
import ups.edu.ec.evaluacionJakarta.model.Consumo;

@Singleton
@Startup
public class Inicio {
	@Inject
    private ClienteDAO daoCliente;

    @Inject
    private GestionConsumo gestionConsumo;
    
    @PostConstruct
    public void init() {
        System.out.println("Inicializando datos...");

        Cliente cliente = new Cliente();
        cliente.setCedula("0106717671");
        cliente.setNombre("Tania Lojano");
        cliente.setDireccion("Cuenca");
        daoCliente.insert(cliente);
        
        Consumo consumo = new Consumo();
        consumo.setCliente(cliente);
        consumo.setFecha(new java.util.Date());
        consumo.setConsumo(150.75);
        consumo.setDeuda(20.50);
        
        try {
            gestionConsumo.createConsumo(consumo);
        } catch (Exception e) {
            System.err.println("Error al crear el consumo: " + e.getMessage());
        }
        
        List<Cliente> listadoClientes = daoCliente.getAll();
        for (Cliente cli : listadoClientes) {
            System.out.println(cli.getCedula() + " " + cli.getNombre() + " " + cli.getDireccion());
        }
        
        List<Consumo> listadoConsumos = gestionConsumo.getAllConsumos();
        for (Consumo con : listadoConsumos) {
            System.out.println(con.getId() + " " + con.getCliente().getCedula() + " " + con.getFecha() + " " + con.getConsumo() + " " + con.getDeuda());
        }
    }

}
