package org.academiadecodigo.pettolove.services;

import org.academiadecodigo.pettolove.persistence.dao.AnimalDao;
import org.academiadecodigo.pettolove.persistence.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalDao animalDao;

    @Autowired
    public void setAnimalDao(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @Override
    public Animal get(Integer id) {
        return animalDao.findById(id);
    }

    @Override
    public List<Animal> listAll() {
        return animalDao.findAll();
    }
}
