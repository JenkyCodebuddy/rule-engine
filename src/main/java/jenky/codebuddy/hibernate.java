package jenky.codebuddy;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernate {

    private SessionFactory sessionFactory;
    private Session session;

    private Session prepare(){
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch(Throwable e){
            System.out.println("Failed to create object: " + e);
            throw new ExceptionInInitializerError(e);
        }
        session = null;
        return session;
    }

    public void putStuffInDB(){

    }

    private void close(){

    }

}
