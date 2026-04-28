package se.yrgo.spring.domain;

import java.lang.annotation.Inherited;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
}
