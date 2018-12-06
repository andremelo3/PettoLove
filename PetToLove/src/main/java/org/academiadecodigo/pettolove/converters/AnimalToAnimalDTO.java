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
        animalDTO.setUserDto(userToUserDTO.convert(animal.getUser()));
        animalDTO.setBreed(animal.getBreed());
        animalDTO.setAnimalType(animal.getAnimalType());
        animalDTO.setDescription(animal.getDescription());

        return animalDTO;
    }
}
