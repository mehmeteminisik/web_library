package com.weblibrary.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {
    private static SessionFactory factory;

    public static Configuration getInitializedConfiguration(){
        Configuration config=new Configuration();
        config.configure();
        return config;
    }

    public static Session getSession(){

        if (factory==null) {
            Configuration config= HibernateUtil.getInitializedConfiguration();
            factory = config.buildSessionFactory();
        }
        Session session=factory.getCurrentSession();
        return session;
    }

    public static void closeSession(){
        HibernateUtil.getSession().close();
    }

    public static void recreateDatabase(){
        Configuration config;
        config= HibernateUtil.getInitializedConfiguration();
        new SchemaExport(config).create(true, true);
    }
    public static Session beginTransaction(){
        Session session=getSession();
        session.beginTransaction();
        return session;
    }

    public static void commitTransaction(){
        HibernateUtil.getSession().getTransaction().commit();
    }

    public static void rollbackTransaction(){
        HibernateUtil.getSession().getTransaction().rollback();
    }
}
