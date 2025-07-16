package org.example.DAO;

import org.example.entities.Specie;
import org.example.util.DatabaseManager;

import javax.persistence.EntityManager;
import java.util.List;

public class SpecieDAO {

    private final EntityManager em;

    public SpecieDAO() {
        this.em = DatabaseManager.getEntityManager();
    }

    public Specie save(Specie specie) {
        try {
            em.getTransaction().begin();
            em.persist(specie);
            em.getTransaction().commit();
            return specie;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erreur lors de la sauvegarde de la Specie : " + e.getMessage());
            return null;
        }
    }

    public Specie get(long id) {
        return em.find(Specie.class, id);
    }

    public List<Specie> getAll() {
        return em.createQuery("SELECT s FROM Specie s", Specie.class).getResultList();
    }

    public Specie update(Specie specie, long id) {
        try {
            Specie existing = get(id);
            if (existing != null) {
                em.getTransaction().begin();
                existing.setCommonName(specie.getCommonName());
                existing.setScientificName(specie.getScientificName());
                existing.setCategory(specie.getCategory());
                existing.setRegions(specie.getRegions());
                em.getTransaction().commit();
                return existing;
            }
            return null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erreur lors de la mise à jour de la Specie : " + e.getMessage());
            return null;
        }
    }

    public boolean delete(long id) {
        try {
            Specie specie = get(id);
            if (specie != null) {
                em.getTransaction().begin();
                em.remove(specie);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erreur lors de la suppression de la Specie : " + e.getMessage());
            return false;
        }
    }
}
