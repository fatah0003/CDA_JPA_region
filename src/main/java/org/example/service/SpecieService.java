package org.example.service;

import org.example.DAO.SpecieDAO;
import org.example.entities.Specie;

import java.util.List;

public class SpecieService {

    private final SpecieDAO specieDAO;

    public SpecieService() {
        this.specieDAO = new SpecieDAO();
    }

    public Specie save(Specie specie) {
        return specieDAO.save(specie);
    }

    public Specie get(long id) {
        return specieDAO.get(id);
    }

    public List<Specie> get() {
        return specieDAO.getAll();
    }

    public Specie update(Specie specie, long id) {
        return specieDAO.update(specie, id);
    }

    public boolean delete(long id) {
        return specieDAO.delete(id);
    }
}
