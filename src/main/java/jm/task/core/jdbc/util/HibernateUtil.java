package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Session session;
    private Transaction transaction;


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        getSessionFactory().close();
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSession() {
        return getSessionFactory().openSession();
    }

    public void closeSession() {
        session.close();
    }

    public Session openTransactionSession() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public void closeTransactionSession() {
        transaction.commit();
        closeSession();
    }
}
