package com.example.payroll;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repo;

    public List<Employee> all() {
        return repo.findAll();
    }

    public Employee one(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> {throw new EmployeeNotFoundException(id);});
    }

    public Employee addNew(Employee employee) {
        return repo.save(employee);
    }

    public Employee update(
            Employee newEmployee,
            Long id
    ) {
        Employee oldEmployee = one(id);
        oldEmployee.setName(newEmployee.getName());
        oldEmployee.setRole(newEmployee.getRole());
        return repo.save(oldEmployee);
    }

    public void delete(Long id) {
        if (one(id) != null) repo.deleteById(id);
        else throw new EmployeeNotFoundException(id);
    }


}
