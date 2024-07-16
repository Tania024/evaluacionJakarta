package ups.edu.ec.evaluacionJakarta.dao;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import ups.edu.ec.evaluacionJakarta.model.Cliente;
import ups.edu.ec.evaluacionJakarta.model.Consumo;

@Stateless
public class ConsumoDAO implements Serializable{
	@PersistenceContext
    private EntityManager em;

    public void insert(Consumo consumo) {
        em.persist(consumo);
    }

    public void update(Consumo consumo) {
        em.merge(consumo);
    }

    public Consumo read(Long id) {
        return em.find(Consumo.class, id);
    }

    public void delete(Long id) {
        Consumo consumo = em.find(Consumo.class, id);
        if (consumo != null) {
            em.remove(consumo);
        }
    }

    public List<Consumo> getAll() {
        String jpql = "SELECT c FROM Consumo c";
        Query query = em.createQuery(jpql, Consumo.class);
        return query.getResultList();
    }

    public List<Consumo> findByCliente(Cliente cliente) {
        String jpql = "SELECT c FROM Consumo c WHERE c.cliente = :cliente";
        Query query = em.createQuery(jpql, Consumo.class);
        query.setParameter("cliente", cliente);
        return query.getResultList();
    }

}
