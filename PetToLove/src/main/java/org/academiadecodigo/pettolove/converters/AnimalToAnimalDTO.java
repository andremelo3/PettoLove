package org.academiadecodigo.pettolove.converters;

import org.academiadecodigo.pettolove.command.AnimalDTO;
import org.academiadecodigo.pettolove.persistence.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AnimalToAnimalDTO extends AbstractConverters<Animal, AnimalDTO> {

    private UserToUserDTO userToUserDTO;

    @Autowired
    public void setUserToUserDTO(UserToUserDTO userToUserDTO) {
        this.userToUserDTO = userToUserDTO;
    }

    @Override
    public AnimalDTO convert(Animal animal) {

        AnimalDTO animalDTO = new AnimalDTO();

        animalDTO.setId(animal.getId());
        animalDTO.setName(animal.getName());
        animalDTO.setUserDTO(userToUserDTO.convert(animal.getUser()));
        animalDTO.setBreed(animal.getBreed());
        animalDTO.setAnimalType(animal.getAnimalType());
        animalDTO.setGender(animal.getGender());
        animalDTO.setAge(animal.getAge());
        animalDTO.setDescription(animal.getDescription());
        animalDTO.setLikes(animal.getLikes());
        animalDTO.setImageLink(animal.getImageLink());

        return animalDTO;
    }
}
