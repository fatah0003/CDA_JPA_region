package org.example.service;



import org.example.DAO.RegionDAO;
import org.example.entities.Region;

import java.util.List;

public class RegionService {

    private RegionDAO regionDAO;

    public RegionService() {
        this.regionDAO = new RegionDAO();
    }

    public Region save (Region region){
        return regionDAO.save(region);
    }

    public Region save (Region region, double longueur, double largeur) {
        region.setSurface(longueur * largeur);
        return regionDAO.save(region);
    }


    public Region get (long id){
        return regionDAO.get(id);
    }

    public List<Region> get (){
        return regionDAO.get();
    }

    public Region update (Region region , long id){
        return regionDAO.update(region,id);
    }

    public boolean delete (long id){
        return regionDAO.delete(id);
    }



}
