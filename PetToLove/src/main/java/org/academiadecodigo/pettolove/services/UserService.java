package org.academiadecodigo.pettolove.services;

import org.academiadecodigo.pettolove.persistence.model.Animal;
import org.academiadecodigo.pettolove.persistence.model.User;

import java.util.List;

public interface UserService {

    User get(Integer id);

    User save(User user);

    void delete(Integer id);

    List<User> list();

    List<Animal> listAnimals(Integer id);

    Animal addAnimal(Integer id, Animal animal);

    void removeAnimal(Integer id, Integer animalId);
}
