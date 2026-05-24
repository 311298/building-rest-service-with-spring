package com.example.payroll;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeModelAssembler assembler;

    @GetMapping()
    public ResponseEntity<List<EntityModel<Employee>>> all() {
        return ResponseEntity.ok(
                service.all()
                        .stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Employee>> one(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(assembler.toModel(service.one(id)));
    }

    @PostMapping()
    public ResponseEntity<Employee> addNew(
            @RequestBody Employee employee
    ) {
        Employee newEmployee = service.addNew(employee);
        return ResponseEntity.created(URIHelper.getLocation("id", newEmployee.getId().toString()))
                .body(newEmployee);
    }

    @PutMapping()
    public ResponseEntity<Employee> update(
            @RequestBody Employee employee,
            @PathVariable Long id
    ) {
        Employee updateEmployee = service.update(employee, id);
        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
