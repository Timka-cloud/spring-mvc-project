package kz.timka.dao;

import kz.timka.models.Person;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class PersonDAO {

    private static long peopleCount = 1;
    private List<Person> people;

    @PostConstruct
    public void init() {
        people = new ArrayList<>(Arrays.asList(
                new Person(peopleCount++, "Alex", "Benjamin", "alex@gmail.ru"),
                new Person(peopleCount++, "Tom", "Spencer", "tom@gmail.ru"),
                new Person(peopleCount++, "Ben", "Lincoln", "ben@gmail.ru"),
                new Person(peopleCount++, "Kate", "Datsu", "kate@gmail.ru")
        ));
    }

    public void save(Person person) {
        person.setId(peopleCount++);
        people.add(person);
    }

    public void update(Long id, Person person) {
        Person personToBeUpdated = getById(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setSurname(person.getSurname());
        personToBeUpdated.setEmail(person.getEmail());
    }

    public List<Person> findAll() {
        return Collections.unmodifiableList(people);
    }

    public Person getById(Long id) {
        return people.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
    }


    public void delete(Long id) {
        people.removeIf(p -> p.getId().equals(id));
    }
}
