package org.academiadecodigo.pettolove.services;

import org.academiadecodigo.pettolove.persistence.model.Animal;

import java.util.List;

public interface AnimalService {

    Animal get(Integer id);

    List<Animal> listAll();
}
