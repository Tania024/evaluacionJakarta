package ups.edu.ec.evaluacionJakarta.business;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import ups.edu.ec.evaluacionJakarta.dao.ConsumoDAO;
import ups.edu.ec.evaluacionJakarta.model.Cliente;
import ups.edu.ec.evaluacionJakarta.model.Consumo;

@Stateless
public class GestionConsumo {
	@Inject
    private ConsumoDAO daoConsumo;

    public List<Consumo> getConsumos(Cliente cliente) {
        return daoConsumo.findByCliente(cliente);
    }

    public void createConsumo(Consumo consumo) throws Exception {
        if (consumo.getCliente() == null || consumo.getCliente().getCedula().length() != 10) {
            throw new Exception("Cliente no valido");
        }
        daoConsumo.insert(consumo);
    }
    
    public void updateConsumo(Consumo consumo) throws Exception {
        if (consumo.getCliente() == null || consumo.getCliente().getCedula().length() != 10) {
            throw new Exception("Cliente no valido");
        }
        daoConsumo.update(consumo);
    }
    
    public void deleteConsumo(Long id) throws Exception {
        Consumo consumo = daoConsumo.read(id);
        if (consumo == null) {
            throw new Exception("Consumo no existe");
        }
        daoConsumo.delete(id);
    }
    
    public List<Consumo> getAllConsumos() {
        return daoConsumo.getAll();
    }

}
