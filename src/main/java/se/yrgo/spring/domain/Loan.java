package se.yrgo.spring.domain;

import jakarta.persistence.*;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
}
