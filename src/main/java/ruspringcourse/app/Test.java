package ruspringcourse.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ruspringcourse.model.Person;

public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        Configuration configuration = (new Configuration()).addAnnotatedClass(Person.class);
        configuration.setProperty("hibernate.properties", "/main/resources/hibernate.properties");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Person person = new Person("Test333", 22, "www.mail.ru");
            session.save(person);
            session.getTransaction().commit();
        } catch (Throwable var6) {
            if (sessionFactory != null) {
                try {
                    sessionFactory.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }
            }

            throw var6;
        }

        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }
}

