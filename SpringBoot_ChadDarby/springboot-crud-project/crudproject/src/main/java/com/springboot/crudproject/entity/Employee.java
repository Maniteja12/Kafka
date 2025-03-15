package com.springboot.crudproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
