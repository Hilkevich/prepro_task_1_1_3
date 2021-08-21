package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        /*
         Создание таблицы User(ов)
         Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
         Получение всех User из базы и вывод в консоль (должен быть переопределен toString в классе User)
         Очистка таблицы User(ов)
         Удаление таблицы
         */


        Util.myDbConnection();                                                            // подключение к BD. (вызываем конекшн из Util, создав его там).
        UserServiceImpl userServiceImpl = new UserServiceImpl();                          // создание объекта класса сервиса (UserServiceImpl), через который будут вызываться методы сервиса.
       // userServiceImpl.dropUsersTable();
        userServiceImpl.createUsersTable();                                               // создание таблицы User(ов). т.е у объекта SERVICE вызываем метод, в котором вызывается такой же метод DAO, который уже с реализацией!

        userServiceImpl.saveUser("Jack","Black", (byte) 30);                // добавление 4-х User(ов).
        userServiceImpl.saveUser("Joe","White", (byte) 35);
        userServiceImpl.saveUser("Bob","Ferry", (byte) 40);
        userServiceImpl.saveUser("Natan","Jones", (byte) 45);

        userServiceImpl.getAllUsers();                                                     // получение всех User(ов) из базы и вывод в консоль (должен быть переопределен toString в классе User)
       // userServiceImpl.removeUserById(3);                                               // удаление User(а) из таблицы по id.
       // userServiceImpl.getAllUsers();
        userServiceImpl.cleanUsersTable();                                                 // очистка таблицы User(ов).
        userServiceImpl.dropUsersTable();                                                  // удаление таблицы.
    }
}
