package ups.edu.ec.evaluacionJakarta.business;

import java.util.List;


import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.evaluacionJakarta.dao.ClienteDAO;
import ups.edu.ec.evaluacionJakarta.model.Cliente;

@Stateless
public class GestionCliente {
	@Inject
    private ClienteDAO daoCliente;

    public Cliente getCliente(String cedula) throws Exception {
        if (cedula.length() != 10) {
            throw new Exception("Cedula incorrecta");
        }
        Cliente cliente = daoCliente.read(cedula);
        if (cliente == null) {
            throw new Exception("Cedula no existe");
        }
        return cliente;
    }
    
    public List<Cliente> getClientes() {
        return daoCliente.getAll();
    }
    
    public void createCliente(Cliente cliente) throws Exception {
        if (cliente.getCedula().length() != 10) {
            throw new Exception("Cedula incorrecta");
        }
        daoCliente.insert(cliente);
    }
    
    public void actualizarCliente(Cliente cliente) throws Exception {
        if (cliente.getCedula().length() != 10) {
            throw new Exception("Cedula incorrecta");
        }
        daoCliente.update(cliente);
    }
    
    public void borrarCliente(String cedula) throws Exception {
        Cliente cliente = daoCliente.read(cedula);
        if (cliente == null) {
            throw new Exception("Cliente no existe");
        }
        daoCliente.delete(cedula);
    }

}
