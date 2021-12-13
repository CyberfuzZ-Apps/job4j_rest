package ru.job4j.auth.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс Employee
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String taxNumber;
    private Timestamp dateOfHiring;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Person> persons = new ArrayList<>();

    public static Employee of(int id, String name, String surname,
                              String taxNumber,
                              List<Person> persons) {
        Employee employee = new Employee();
        employee.id = id;
        employee.name = name;
        employee.surname = surname;
        employee.taxNumber = taxNumber;
        employee.dateOfHiring = new Timestamp(System.currentTimeMillis());
        employee.persons = persons;
        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Timestamp getDateOfHiring() {
        return dateOfHiring;
    }

    public void setDateOfHiring(Timestamp dateOfHiring) {
        this.dateOfHiring = dateOfHiring;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", taxNumber='" + taxNumber + '\''
                + ", dateOfHiring=" + dateOfHiring
                + ", persons=" + persons
                + '}';
    }
}
