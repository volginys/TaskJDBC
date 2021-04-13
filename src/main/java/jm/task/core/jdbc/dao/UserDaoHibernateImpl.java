package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl extends HibernateUtil implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable(){

        openTransactionSession();

        String sql = "CREATE TABLE if not exists users " +
                "(id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "lastname VARCHAR(30)," +
                "age TINYINT);";

        Session session = getSession();
        session.createSQLQuery(sql).executeUpdate();

        closeTransactionSession();

    }

    @Override
    public void dropUsersTable(){

        openTransactionSession();

        String sql = "DROP TABLE IF EXISTS USERS";

        Session session = getSession();
        session.createSQLQuery(sql).executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age){

        openTransactionSession();

        Session session = getSession();
        session.save(new User(name, lastName, age));
        System.out.println("User с именем"+name+" добавлен в базу данных");

        closeTransactionSession();
    }

    @Override
    public void removeUserById(long id){

        openTransactionSession();

        String sql = "DELETE FROM USERS WHERE ID = :id";
        Session session = getSession();
        session.createSQLQuery(sql).setParameter("id", id).executeUpdate();

        closeTransactionSession();

    }

    @Override
    public List<User> getAllUsers(){

        openTransactionSession();

        String sql = "SELECT * FROM USERS";

        Session session = getSession();
        SQLQuery query = session.createSQLQuery(sql).addEntity(User.class);
        List<User> userList = query.list();

        closeTransactionSession();

        return userList;
    }

    @Override
    public void cleanUsersTable(){
        openTransactionSession();

        String sql = "TRUNCATE TABLE USERS;";

        Session session = getSession();
        session.createSQLQuery(sql).executeUpdate();

        closeTransactionSession();
    }
}
