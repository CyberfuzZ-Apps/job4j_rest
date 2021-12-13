package ru.job4j.auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.auth.domain.Employee;

/**
 * Класс EmployeeRepository
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("select distinct e from Employee e join fetch e.persons")
    Iterable<Employee> findAllEmployees();
}
