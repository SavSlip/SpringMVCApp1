package ruspringcourse.config.DAO;

import org.springframework.stereotype.Component;
import ruspringcourse.config.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private int PEOPLE_COUNT;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"Tom"));
        people.add(new Person(++PEOPLE_COUNT,"Mary"));
        people.add(new Person(++PEOPLE_COUNT,"Jim"));
        people.add(new Person(++PEOPLE_COUNT,"Steve"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonByIndex(int index) {
        return people.stream().filter(person -> person.getId() == index).findAny().orElse(null);
    }

    public void save (Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person originalPerson = getPersonByIndex(id);
        originalPerson.setName(person.getName());
    }
}
