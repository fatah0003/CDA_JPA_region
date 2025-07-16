package org.example.DAO;



import org.example.entities.Region;
import org.example.util.DatabaseManager;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class RegionDAO {

    private EntityManager em;

    public RegionDAO() {
        this.em = DatabaseManager.getEntityManager();
    }

    public Region save (Region region){
        try{
            em.getTransaction().begin();
            em.persist(region);
            em.getTransaction().commit();
            return region;
        }catch (Exception e){
            em.getTransaction().rollback();
            return null;
        }
    }

    public Region get (long id){
        return em.find(Region.class,id);
    }

    public List<Region> get (){
        return em.createQuery("select r from Region r ", Region.class).getResultList();
    }

    public Region update (Region region , long id){
        try{
            Region regionFound = get(id);
            if(regionFound != null){
                em.getTransaction().begin();
                regionFound.setNom(region.getNom());
                regionFound.setSurface(region.getSurface());
                regionFound.setClimat(region.getClimat());
                em.getTransaction().commit();
                return regionFound;
            }
            return null;
        }catch (Exception e){
            em.getTransaction().rollback();
            return null;
        }
    }

    public boolean delete (long id){
        try{
            Region regionFound = get(id);
            if(regionFound != null){
                em.getTransaction().begin();
                em.remove(regionFound);
                em.getTransaction().commit();
                return true;
            }
            return false;
        }catch (Exception e){
            em.getTransaction().rollback();
            return false;
        }
    }
}
