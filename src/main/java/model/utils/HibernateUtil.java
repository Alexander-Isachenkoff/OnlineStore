package model.utils;

import model.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static SessionFactory sessionFactory;
    
    private HibernateUtil() {}
    
    public static SessionFactory getSessionFactory() {
        sessionLazyInit();
        return sessionFactory;
    }
    
    private static void sessionLazyInit() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Category.class);
            configuration.addAnnotatedClass(Manufacturer.class);
            configuration.addAnnotatedClass(Provider.class);
            configuration.addAnnotatedClass(Product.class);
            configuration.addAnnotatedClass(Supply.class);
            configuration.addAnnotatedClass(ProductSupply.class);
            configuration.addAnnotatedClass(ProductOnStorage.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
    }
}
