package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;


public class UserServiceImpl implements UserService {

    private final UserDaoHibernateImpl userDaoHibernateImpl = new UserDaoHibernateImpl();                            // в класса UserServiceImpl создаем общее для всех методов класса приватное поле - объект класса UserDaoHibernateImpl,


    public void createUsersTable() {
        userDaoHibernateImpl.createUsersTable();                                                                     //  и уже у него вызаваем его такой же метод. Принцип MVC. Service -> DAO -> DB. (у него он есть!)
    }


    public void dropUsersTable() {
        userDaoHibernateImpl.dropUsersTable();
    }


    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateImpl.saveUser(name, lastName, age);                                                          // передаем теже параметры, что приходят в метод UserServiceImpl.
    }


    public void removeUserById(long id) {
        userDaoHibernateImpl.removeUserById(id);                                                                     // передаем теже параметры, что приходят в метод UserServiceImpl.
    }


    public List<User> getAllUsers() {
        return userDaoHibernateImpl.getAllUsers();                                                                   // сдесь возвращаем то, что возвращает метод getAllUsers() класса userDaoJDBC ( т.е List<User> userList ).
    }


    public void cleanUsersTable() {
        userDaoHibernateImpl.cleanUsersTable();
    }
}
