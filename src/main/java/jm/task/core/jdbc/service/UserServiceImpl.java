package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoHibernateImpl userService = new UserDaoHibernateImpl();

    public UserServiceImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() {
        userService.createUsersTable();
    }

    public void dropUsersTable() {
        userService.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userService.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userService.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void cleanUsersTable()  {
        userService.cleanUsersTable();
    }
}
