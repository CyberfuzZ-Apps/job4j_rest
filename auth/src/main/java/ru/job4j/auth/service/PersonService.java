package ru.job4j.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.Optional;

/**
 * Класс PersonService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class PersonService {

    private final PersonRepository persons;

    public PersonService(PersonRepository persons) {
        this.persons = persons;
    }

    public Iterable<Person> findAll() {
        return persons.findAll();
    }

    public Optional<Person> findById(int id) {
        return persons.findById(id);
    }

    @Transactional
    public Person save(Person person) {
        return persons.save(person);
    }

    public void delete(Person person) {
        persons.delete(person);
    }
}
