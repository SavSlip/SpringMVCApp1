package ruspringcourse.DAO;

import java.util.List;
import ruspringcourse.model.Person;

public interface PersonDAO {
    List<Person> getAllPeople();

    Person getPersonByIndex(int var1);

    void save(Person var1);

    void update(int var1, Person var2);

    void delete(int var1);
}
