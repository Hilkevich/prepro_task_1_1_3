package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "190685";

    // private static final Connection CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);


    public UserDaoJDBCImpl() {                                      // конструктор по умолчанию.
    }


    public void createUsersTable() {                                //  тут создание таблицы jdbc
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS `mydb`.`users` (\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`id`));";

            statement.executeUpdate(sql);

            System.out.println("Table \"users\" create successfully!");

        } catch (SQLException sqlEx) {
            System.out.println("Table \"users\" not create!");
            sqlEx.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "DROP TABLE IF EXISTS users";                                            //если "DROP TABLE users" (без IF EXISTS) вызвать 2-ой раз,то ошибка: не знаю такую таблицу!
            statement.executeUpdate(sql);

            System.out.println("Table \"users\" drop successfully!");

        } catch (SQLException sqlEx) {
            System.out.println("Table \"users\" not drop!");
            sqlEx.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql = "INSERT INTO users (name, lastName, age) Values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User под id " + id + " удален из базы данных");

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }


    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();                                   // создаем лист юзеров, чтоб собрать их туда и вернуть.

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {

                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getByte(4));

                userList.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return userList;
    }


    public void cleanUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM users";
            statement.executeUpdate(sql);

            System.out.println("Table \"users\" clean successfully!");

        } catch (SQLException sqlEx) {
            System.out.println("Table \"users\" not clean!");
            sqlEx.printStackTrace();
        }
    }
}

