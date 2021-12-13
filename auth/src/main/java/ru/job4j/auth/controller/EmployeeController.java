package ru.job4j.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.service.EmployeeService;

import java.util.Optional;

/**
 * Класс EmployeeController
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    private final RestTemplate rest;
    private final EmployeeService service;

    public EmployeeController(RestTemplate rest, EmployeeService service) {
        this.rest = rest;
        this.service = service;
    }

    @GetMapping("/")
    public Iterable<Employee> findAll() {
        return service.findAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable int id) {
        Optional<Employee> optionalEmployee = this.service.findById(id);
        return optionalEmployee.orElse(new Employee());
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person rsl = rest.postForObject(API, person, Person.class);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
