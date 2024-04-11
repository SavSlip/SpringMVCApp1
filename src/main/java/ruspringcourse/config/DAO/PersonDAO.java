package ruspringcourse.config.DAO;

import org.springframework.stereotype.Component;
import ruspringcourse.config.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
//    private int PEOPLE_COUNT = 0;

    {
        people = new ArrayList<>();
        people.add(new Person(1,"Tom"));
        people.add(new Person(2,"Mary"));
        people.add(new Person(3,"Jim"));
        people.add(new Person(4,"Steve"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonByIndex(int index) {
        return people.stream().filter(person -> person.getId() == index).findAny().orElse(null);
    }
}
