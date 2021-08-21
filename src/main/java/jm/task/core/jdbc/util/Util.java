package jm.task.core.jdbc.util;

import jdk.jfr.StackTrace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Util {
    // реализуйте настройку соеденения с БД

    public static void myDbConnection() throws ClassNotFoundException {


        String url = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "190685";
        Class.forName("com.mysql.cj.jdbc.Driver"); // ..cj.jdbc - ok! актуальный драйвер.Скачал connector j(8.0.26), установил в папку с MySQLServer'ом и Workbench'ем, проставил разрешения на всё, в POM.xml заменил 5.1.38 на 8.0.26!

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection to DB successfully!");


        } catch (Exception e) {
            System.out.println("Connection to DB failed");
            e.printStackTrace();
        }
    }
}
