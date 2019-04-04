package org.academiadecodigo.pettolove.converters;

import org.academiadecodigo.pettolove.command.UserDTO;
import org.academiadecodigo.pettolove.persistence.model.User;
import org.academiadecodigo.pettolove.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUser extends AbstractConverters<UserDTO, User> {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User convert(UserDTO userDTO) {

        User user = (userDTO.getId() != null ? userService.get(userDTO.getId()) : new User());

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());

        return user;
    }
}
