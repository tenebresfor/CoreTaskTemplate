package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE users (id INT AUTO_INCREMENT, name VARCHAR(256) NOT NULL, lastName VARCHAR(256) NOT NULL, age TINYINT NOT NULL, PRIMARY KEY(id));").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE users;");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query q = session.createQuery("delete User where id = :id;").setParameter("id", id);
            q.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.clear();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            users = session.createQuery("From User").list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("DELETE FROM users;");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
    }
}
