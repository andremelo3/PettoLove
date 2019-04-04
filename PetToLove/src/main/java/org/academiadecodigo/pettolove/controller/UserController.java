package org.academiadecodigo.pettolove.controller;

import org.academiadecodigo.pettolove.command.UserDTO;
import org.academiadecodigo.pettolove.converters.UserDTOToUser;
import org.academiadecodigo.pettolove.converters.UserToUserDTO;
import org.academiadecodigo.pettolove.persistence.model.User;
import org.academiadecodigo.pettolove.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserToUserDTO userToUserDTO;
    private UserDTOToUser userDTOToUser;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserDTOToUser(UserDTOToUser userDTOToUser) {
        this.userDTOToUser = userDTOToUser;
    }

    @Autowired
    public void setUserToUserDTO(UserToUserDTO userToUserDTO) {
        this.userToUserDTO = userToUserDTO;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<UserDTO> showUser(@PathVariable Integer id) {

        User user = userService.get(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userToUserDTO.convert(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        if (bindingResult.hasErrors() || userDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userDTOToUser.convert(userDto);

        List<User> users = userService.list();

        for(User username:users) {

            if (user.getUsername().equals(username.getUsername())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        User savedUser = userService.save(user);

        // get help from the framework building the path for the newly created resource
        UriComponents uriComponents = uriComponentsBuilder.path("/user/" + savedUser.getId()).build();

        // set headers with the created path
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<UserDTO> editUser(@Valid @RequestBody UserDTO userDto, BindingResult bindingResult, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userDto.getId() != null && !userDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userDto.setId(id);

        userService.save(userDTOToUser.convert(userDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer id) {

            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public ResponseEntity<List<UserDTO>> listUsersRest() {

        System.out.println("List all users");

        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : userService.list()) {
            userDTOS.add(userToUserDTO.convert(user));
        }

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
}
