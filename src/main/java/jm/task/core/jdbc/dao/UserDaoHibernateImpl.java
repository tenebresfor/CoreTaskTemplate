package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String TABLE_NAME = "users";

    @Override
    public void createUsersTable() {
        final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (id INT AUTO_INCREMENT, name VARCHAR(256) NOT NULL, lastName VARCHAR(256) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY(id))";
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(CREATE_TABLE).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }

    }

    @Override
    public void dropUsersTable() {
        final String DROP_TABLE = "DROP TABLE users";
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(DROP_TABLE);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
    }

    @Override
    public void removeUserById(long id) {
        final String DELETE_USER = "delete User where id = :id";
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query q = session.createQuery(DELETE_USER).setParameter("id", id);
            q.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        final String FROM = "From User";
        List<User> users = new ArrayList<>();
        users.clear();
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            users = session.createQuery(FROM).list();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        final String DELETE_FROM = "DELETE FROM users";
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createSQLQuery(DELETE_FROM);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.getMessage();
        }
    }
}
