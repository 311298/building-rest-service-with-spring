package com.example.payroll;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String role;

    @Builder
    public Employee (
            String name,
            String role
    ) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Employee name : " + name +
                " role : " + role +
                " employee id : " + id;
    }
}
