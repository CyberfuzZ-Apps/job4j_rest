package ru.job4j.auth.service;

import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.repository.EmployeeRepository;

import java.util.Optional;

/**
 * Класс EmployeeService
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

}
