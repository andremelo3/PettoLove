package org.academiadecodigo.pettolove.converters;

import org.academiadecodigo.pettolove.command.AnimalDTO;
import org.academiadecodigo.pettolove.persistence.model.Animal;
import org.academiadecodigo.pettolove.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalDTOToAnimal extends AbstractConverters<AnimalDTO, Animal> {

    private AnimalService animalService;
    private UserDTOToUser userDTOToUser;

    @Autowired
    public void setAnimalService(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Autowired
    public void setUserDTOToUser(UserDTOToUser userDTOToUser) {
        this.userDTOToUser = userDTOToUser;
    }

    @Override
    public Animal convert(AnimalDTO animalDTO) {
        Animal animal = (animalDTO.getId() != null ? animalService.get(animalDTO.getId()) : new Animal());

        animal.setName(animalDTO.getName());
        animal.setUser(userDTOToUser.convert(animalDTO.getUserDTO()));
        animal.setBreed(animalDTO.getBreed());
        animal.setAnimalType(animalDTO.getAnimalType());
        animal.setGender(animalDTO.getGender());
        animal.setAge(animalDTO.getAge());
        animal.setDescription(animalDTO.getDescription());
        animal.setLikes(animalDTO.getLikes());
        animal.setImageLink(animalDTO.getImageLink());

        return animal;
    }
}
