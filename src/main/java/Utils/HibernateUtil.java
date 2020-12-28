package Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private SessionFactory factory;
    private static HibernateUtil hibernateUtil;

    private HibernateUtil(){ }

    public static HibernateUtil getInstance(){
        if(hibernateUtil == null)
            hibernateUtil = new HibernateUtil();

        return hibernateUtil;
    }

    public SessionFactory getFactory(){
        try {
            if (factory == null)
                factory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();

            else if (factory.isClosed())
                factory = new Configuration().configure().buildSessionFactory();

        }catch(Exception e){
            e.printStackTrace();
        }
        return factory;
    }

    public Session getCurrentSession() {
        Session session = null;
        try {
            session = getFactory().getCurrentSession();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
}

