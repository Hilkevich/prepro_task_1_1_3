package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {                                                               // sql

        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS `mydb`.`User` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` TINYINT UNSIGNED NOT NULL,\n" +
                "  PRIMARY KEY (`id`));";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        System.out.println("Table \"User\" create successfully!");
        transaction.commit();
        session.close();

    }


    @Override
    public void dropUsersTable() {                                                                 //sql

        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS User";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        System.out.println("Table \"User\" drop successfully!");
        transaction.commit();
        session.close();

    }


    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(name, lastName, age);
        session.saveOrUpdate(user);

        System.out.println("User с именем " + name + " добавлен в базу данных");
        transaction.commit();
        session.close();

    }


    @Override
    public void removeUserById(long id) {

        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.load(User.class, id);
        session.delete(user);

        System.out.println("User под id " + id + " удален из базы данных");
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {

        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        List userList = session.createQuery("from User").list();

        System.out.println(userList);
        transaction.commit();
        session.close();

        return userList;

    }


    @Override
    public void cleanUsersTable() {

        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        String hql= "DELETE FROM User";
        Query query = session.createQuery(hql);
        query.executeUpdate();


        System.out.println("Table \"User\" clean successfully!");
        transaction.commit();
        session.close();

    }
}
