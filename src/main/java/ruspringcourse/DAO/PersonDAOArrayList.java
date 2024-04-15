package ruspringcourse.DAO;

import org.springframework.stereotype.Component;
import ruspringcourse.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAOArrayList implements PersonDAO {
    private final List<Person> people;
    private int PEOPLE_COUNT;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", 10, "tomm@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mary", 34, "marrry@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Jim", 17, "ssstttvvv@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Steve", 28, "coolsteve@gmail.com"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonByIndex(int index) {
        return people.stream().filter(person -> person.getId() == index).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person originalPerson = getPersonByIndex(id);
        originalPerson.setName(person.getName());
        originalPerson.setAge(person.getAge());
        originalPerson.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
