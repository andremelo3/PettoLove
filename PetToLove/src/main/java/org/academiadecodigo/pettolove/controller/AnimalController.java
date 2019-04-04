package org.academiadecodigo.pettolove.controller;

import org.academiadecodigo.pettolove.command.AnimalDTO;
import org.academiadecodigo.pettolove.converters.AnimalDTOToAnimal;
import org.academiadecodigo.pettolove.converters.AnimalToAnimalDTO;
import org.academiadecodigo.pettolove.persistence.model.Animal;
import org.academiadecodigo.pettolove.persistence.model.User;
import org.academiadecodigo.pettolove.services.AnimalService;
import org.academiadecodigo.pettolove.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/animal")
public class AnimalController {

    private AnimalService animalService;
    private UserService userService;
    private AnimalDTOToAnimal animalDTOToAnimal;
    private AnimalToAnimalDTO animalToAnimalDTO;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAnimalService(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Autowired
    public void setAnimalDTOToAnimal(AnimalDTOToAnimal animalDTOToAnimal) {
        this.animalDTOToAnimal = animalDTOToAnimal;
    }

    @Autowired
    public void setAnimalToAnimalDTO(AnimalToAnimalDTO animalToAnimalDTO) {
        this.animalToAnimalDTO = animalToAnimalDTO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{uid}/animal")
    public ResponseEntity<List<AnimalDTO>> listUserAnimals(@PathVariable Integer uid) {

        User user = userService.get(uid);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<AnimalDTO> animalDTOS = animalToAnimalDTO.convert(user.getAnimals());

        return new ResponseEntity<>(animalDTOS, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{uid}/animal/{aid}")
    public ResponseEntity<AnimalDTO> showUserAnimal(@PathVariable Integer uid, @PathVariable Integer aid) {

        Animal animal = animalService.get(aid);

        if (animal == null || animal.getUser() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!animal.getUser().getId().equals(uid)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(animalToAnimalDTO.convert(animal), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{uid}/animal")
    public ResponseEntity<?> addAnimal(@PathVariable Integer uid, @Valid @RequestBody AnimalDTO animalDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || animalDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Animal animal = userService.addAnimal(uid, animalDTOToAnimal.convert(animalDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/customer/" + uid + "/account/" + animal.getId()).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/{uid}/account/{aid}/remove")
    public ResponseEntity<?> removeAnimal(@PathVariable Integer uid, @PathVariable Integer aid) {

        userService.removeAnimal(uid, aid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
