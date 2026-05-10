package se.yrgo.spring.domain;

import java.util.*;

import jakarta.persistence.*;

// David
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String adress;
    private String zip;
    private String city;
    @OneToMany(mappedBy = "user")
    private List<Loan> loans;

    public User() {
    }

    public User(String userId,
            String firstName,
            String lastName,
            String email,
            String password,
            String adress,
            String zip,
            String city) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.zip = zip;
        this.city = city;
        this.loans = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateUser(
        String firstName,
        String lastName,
        String email,
        String password,
        String adress,
        String zip,
        String city){
            setFirstName(firstName);
            setLastName(lastName);
            setEmail(email);
            setPassword(password);
            setAdress(adress);
            setZip(zip);
            setCity(city);
        }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", adress=" + adress
                + ", zip=" + zip + ", city=" + city + "]";
    }

}
