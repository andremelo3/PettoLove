package org.academiadecodigo.pettolove.services;

import org.academiadecodigo.pettolove.persistence.dao.AnimalDao;
import org.academiadecodigo.pettolove.persistence.dao.UserDao;
import org.academiadecodigo.pettolove.persistence.model.Animal;
import org.academiadecodigo.pettolove.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private AnimalDao animalDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setAnimalDao(AnimalDao animalDao) {
        this.animalDao = animalDao;
    }

    @Override
    public User get(Integer id) {
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        return userDao.saveOrUpdate(user);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        User user = userDao.findById(id);

        if (user == null) {
            throw new RuntimeException();
        }

        userDao.delete(id);
    }

    @Override
    public List<User> list() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Animal> listAnimals(Integer id) {
        User user = userDao.findById(id);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return new ArrayList<>(user.getAnimals());
    }

    @Transactional
    @Override
    public Animal addAnimal(Integer id, Animal animal) {
        User user = userDao.findById(id);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.addAnimal(animal);
        userDao.saveOrUpdate(user);

        return user.getAnimals().get(user.getAnimals().size() - 1);
    }

    @Transactional
    @Override
    public void removeAnimal(Integer id, Integer animalId) {
        User user = userDao.findById(id);
        Animal animal = animalDao.findById(id);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (animal == null) {
            throw new RuntimeException("Animal not found");
        }

        user.removeAnimal(animal);
        userDao.saveOrUpdate(user);
    }
}
