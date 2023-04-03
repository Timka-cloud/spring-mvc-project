package kz.timka.dao;

import kz.timka.models.Person;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class PersonDAO {

    private static long peopleCount = 1;
    private static final String URL = "jdbc:postgresql://localhost:5433/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO Person VALUES (" + 2 + ", '" + person.getName() + "', "
                    + person.getAge() + ", '" + person.getEmail() + "', '" + person.getSurname() + "')";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Long id, Person person) {
//        Person personToBeUpdated = getById(id);
//        personToBeUpdated.setName(person.getName());
//        personToBeUpdated.setSurname(person.getSurname());
//        personToBeUpdated.setAge(person.getAge());
//        personToBeUpdated.setEmail(person.getEmail());
    }

    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Person");
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person getById(Long id) {

        Person person = new Person();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from person where id =" + id;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
       // return people.stream().filter(p -> p.getId().equals(id)).findAny().orElse(null);
        return person;
    }


    public void delete(Long id) {
     //   people.removeIf(p -> p.getId().equals(id));
    }
}
