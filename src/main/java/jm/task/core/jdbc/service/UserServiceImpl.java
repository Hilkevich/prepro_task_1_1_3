package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;


public class UserServiceImpl implements UserService {

    private final UserDaoJDBCImpl userDaoJDBCImpl = new UserDaoJDBCImpl();                            // в класса UserServiceImpl создаем общее для всех методов класса приватное поле - объект класса UserDaoJDBCImpl,


    public void createUsersTable() {
        userDaoJDBCImpl.createUsersTable();                                                     //  и уже у него вызаваем его такой же метод. Принцип MVC. Service -> DAO -> DB. (у него он есть!)
    }


    public void dropUsersTable() {
        userDaoJDBCImpl.dropUsersTable();
    }


    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBCImpl.saveUser(name, lastName, age);                                          // передаем теже параметры, что приходят в метод UserServiceImpl.
    }


    public void removeUserById(long id) {
        userDaoJDBCImpl.removeUserById(id);                                                      // передаем теже параметры, что приходят в метод UserServiceImpl.
    }


    public List<User> getAllUsers() {
        return userDaoJDBCImpl.getAllUsers();                                                    // сдесь возвращаем то, что возвращает метод getAllUsers() класса userDaoJDBC ( т.е List<User> userList ).
    }


    public void cleanUsersTable() {
        userDaoJDBCImpl.cleanUsersTable();
    }
}
