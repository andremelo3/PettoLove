package org.academiadecodigo.pettolove.converters;

import org.academiadecodigo.pettolove.command.UserDTO;
import org.academiadecodigo.pettolove.persistence.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTO extends AbstractConverters<User,UserDTO> {

    @Override
    public UserDTO convert(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
