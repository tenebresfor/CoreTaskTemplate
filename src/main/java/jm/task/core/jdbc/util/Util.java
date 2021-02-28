package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
        private static SessionFactory sessionFactory;

        public static SessionFactory getSessionFactory() {
        try {
        // Настройки hibernate
        Configuration configuration = new Configuration()
                .setProperty( "hibernate.connection.driver_class",
                        "com.mysql.cj.jdbc.Driver" )
                .setProperty( "hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/DBTEST" )
                .setProperty( "hibernate.connection.username",
                        "root" )
                .setProperty( "hibernate.connection.password",
                       "password" )
                .setProperty( "hibernate.connection.pool_size", "1" )
                .setProperty( "hibernate.connection.autocommit", "false" )
                .setProperty( "hibernate.cache.provider_class",
                        "org.hibernate.cache.NoCacheProvider" )
                .setProperty( "hibernate.cache.use_second_level_cache",
                        "false" )
                .setProperty( "hibernate.cache.use_query_cache", "false" )
                .setProperty( "hibernate.dialect",
                        "org.hibernate.dialect.MySQLDialect" )
                .setProperty( "hibernate.show_sql","true" )
                .setProperty( "hibernate.current_session_context_class",
                        "thread" )
                .setProperty("useSSL", "false")
                .addPackage( "ru.mysql.db" )
                .addAnnotatedClass(User.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            System.out.println("Исключение!" + e);
        }
        return sessionFactory;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        final String DB_USER = "root";
        final String DB_PASSWORD = "password";
        return getConnection(DB_USER, DB_PASSWORD);
    }
    public static Connection getConnection(String dbUser, String dbPassword) throws SQLException, ClassNotFoundException  {
        final String DB_NAME = "DBTEST";
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Драйвер подключен");
        String connectionURL = "jdbc:mysql://localhost:3306/" + DB_NAME;
        Connection connection = DriverManager.getConnection(connectionURL, dbUser, dbPassword);
        System.out.println("Соединение установлено");
        return connection;
    }
}
