package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void close() throws SQLException {
        connection.close();
    }

    public void createUsersTable() throws SQLException{

        PreparedStatement preparedStatement = null;

        String sql = "CREATE TABLE if not exists users " +
                "(id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "lastname VARCHAR(30)," +
                "age TINYINT);";

        try{

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException{

        PreparedStatement preparedStatement = null;

        String sql = "DROP TABLE if exists USERS;";

        try{

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES(? ,? ,?)";

        try{

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

            connection.commit();

            System.out.println("User с именем - "+name+" добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException{

        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM USERS WHERE ID=?";

        try{

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException{

        List<User> userList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";

        Statement statement = null;

        try{

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){

                User user = new User();

                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null){
                statement.close();
            }
        }
        return userList;
    }

    public void cleanUsersTable() throws SQLException{


        PreparedStatement preparedStatement = null;

        String sql = "TRUNCATE TABLE USERS;";

        try{

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();


            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }
}
