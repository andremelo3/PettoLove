package org.academiadecodigo.pettolove.persistence.dao;

import org.academiadecodigo.pettolove.persistence.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }
}
