package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdata";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "eoot";


    public Connection getConnection() {

        Connection connection =null;

        try {

            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            connection.setAutoCommit(false);

            System.out.println("Connection done...");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
}
