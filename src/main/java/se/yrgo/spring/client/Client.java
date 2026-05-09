package se.yrgo.spring.client;

import java.util.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.spring.domain.*;
import se.yrgo.spring.misc.*;
import se.yrgo.spring.services.author.AuthorService;
import se.yrgo.spring.services.book.BookService;
import se.yrgo.spring.services.user.UserService;

public class Client {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {

            AuthorService author = container.getBean(AuthorService.class);
            BookService book = container.getBean(BookService.class);
            UserService user = container.getBean(UserService.class);

            Set<String> ids = new HashSet<>();
            UniqueIdGenerator idGenerator = new UniqueIdGenerator();

            while (true) {
                Scanner input = new Scanner(System.in);
                System.out.printf("""
                        Välkommen till biblioteket(använd 1, 2, 3 etc.. för att göra val)
                        1. Logga in/Skapa användare
                        2. Redigera användare
                        3. Låna en bok
                        4. Visa dina lån
                        5. Admin meny
                        """);
                String choice = input.nextLine();
                String email;
                String password;
                String firstName;
                String lastName;
                String address;
                String zip;
                String city;
                switch (choice) {
                    case "1" -> {
                        System.out.print("Skriv in din mail: ");
                        email = input.nextLine();
                        System.out.print("Skriv in ditt förnamn: ");
                        firstName = input.nextLine();
                        System.out.print("Skriv in ditt efternamn: ");
                        lastName = input.nextLine();
                        System.out.print("Skriv in din adress: ");
                        address = input.nextLine();
                        System.out.print("Skriv in din zipkod: ");
                        zip = input.nextLine();
                        System.out.print("Skriv in din stad: ");
                        city = input.nextLine();
                        System.out.print("Skriv in ett lösenord: ");
                        password = input.nextLine();
                        user.addUser(idGenerator.generateUniqueId(ids), firstName, lastName, email, password, address, zip, city);
                        System.out.println("Added user: " + user.findUserByEmail(email));
                    }
                    case "2" -> {
                        System.out.println("Skriv in din mail:");
                        String emailChoice = input.nextLine();
                        User theUser = user.findUserByEmail(emailChoice);
                        System.out.printf("""
                                1. Ändra mail
                                2. Ändra lösenord
                                3. Ändra namn
                                4. Ändra postadress
                                """);
                        choice = input.nextLine();

                        String newMail;
                        String newPassword;
                        String newFirstName;
                        String newLastName;
                        String newAddress;
                        String newZip;
                        String newCity;
                        switch(choice){
                            case "1" -> {
                                System.out.println("Skriv din nya mail:");
                                newMail = input.nextLine();
                            }
                            case "2" -> {
                                System.out.println("Skriv in ditt nya lösenord:");
                                newPassword = input.nextLine();
                            }
                            case "3" -> {
                                System.out.println("Skriv ditt nya förnamn:");
                                newFirstName = input.nextLine();
                                System.out.println("Skriv ditt nya efternamn:");
                                newLastName = input.nextLine();
                            }
                            case "4" -> {
                                System.out.println("Skriv din nya adress:");
                                newAddress = input.nextLine();
                                System.out.println("Skriv ditt nya postnummer:");
                                newZip = input.nextLine();
                                System.out.println("Skriv din nya stad:");
                                newCity = input.nextLine();
                            }
                            default -> {
                                System.out.println("Något gick fel. Försök igen!");
                            }
                        }
                        theUser.updateUser(newFirstName, newLastName, newMail, newPassword, newAddress, newZip, newCity);
                    }
                    case "3" -> {

                    }
                    case "4" -> {

                    }
                    case "5" -> {

                    }
                    default -> {

                    }
                }
                break;
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }

    }

}
