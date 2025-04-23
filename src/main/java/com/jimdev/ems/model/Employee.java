package com.jimdev.ems.model;

import jakarta.persistence.*;

import lombok.*;


@Entity

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(name="email",nullable = false,unique = true)
    private String email;


}
