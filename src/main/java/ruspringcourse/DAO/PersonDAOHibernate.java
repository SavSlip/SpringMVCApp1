package ruspringcourse.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ruspringcourse.model.Person;

@Component
public class PersonDAOHibernate implements PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Person> getAllPeople() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
        return people;
    }

    @Transactional
    public Person getPersonByIndex(int index) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Person)session.get(Person.class, index);
    }

    @Transactional
    public void save(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        Person originalPerson = (Person)session.get(Person.class, id);
        originalPerson.setName(person.getName());
        originalPerson.setAge(person.getAge());
        originalPerson.setEmail(person.getEmail());
    }

    @Transactional
    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person)session.get(Person.class, id);
        session.delete(person);
    }
}
