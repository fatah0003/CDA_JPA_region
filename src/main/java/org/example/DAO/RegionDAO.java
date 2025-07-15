package org.example.DAO;

import org.example.entities.Region;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RegionDAO {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_exo_region");

    public void create(Region region) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(region);
        em.getTransaction().commit();
        em.close();
    }

    public Region findById(int id) {
        EntityManager em = emf.createEntityManager();
        Region region = em.find(Region.class, id);
        em.close();
        return region;
    }

    public List<Region> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Region> regions = em.createQuery("SELECT r FROM Region r", Region.class).getResultList();
        em.close();
        return regions;
    }

    public void update(Region region) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(region);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        Region region = em.find(Region.class, id);
        if (region != null) {
            em.getTransaction().begin();
            em.remove(region);
            em.getTransaction().commit();
        }
        em.close();
    }
}
