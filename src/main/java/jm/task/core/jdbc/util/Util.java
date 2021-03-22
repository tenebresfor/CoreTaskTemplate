package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Util {
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        try {
        // Настройки hibernate
        Configuration configuration = new Configuration()
                .setProperty( "hibernate.connection.driver_class",
                        "com.mysql.cj.jdbc.Driver" )
                .setProperty( "hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/schema" )
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
}
