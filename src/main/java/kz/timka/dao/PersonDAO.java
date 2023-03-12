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
                new Person(peopleCount++, "Alex"),
                new Person(peopleCount++, "Tom"),
                new Person(peopleCount++, "Ben"),
                new Person(peopleCount++, "Kate")
        ));
    }

    public void save(Person person) {
        person.setId(peopleCount++);
        people.add(person);
    }

    public List<Person> findAll() {
        return Collections.unmodifiableList(people);
    }

    public Person getById(Long id) {
        return people.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
    }



}
