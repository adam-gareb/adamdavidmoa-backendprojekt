package se.yrgo.spring.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.spring.domain.*;
import se.yrgo.spring.services.author.AuthorService;
import se.yrgo.spring.services.book.BookService;
import se.yrgo.spring.services.user.UserService;

public class Client {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {

            AuthorService author = container.getBean(AuthorService.class);
            BookService book = container.getBean(BookService.class);
            UserService user = container.getBean(UserService.class);

            Author author1 = author.addAuthor("GRE-3", "Bertil");
            // book.registerNewBook("123123333", "hej", author1);

            User user1 = user.addUser("1", "Bob", "Geldof", "bobi2@gmail.com", "SvenStig 2", "123", "Munkebäck");

            System.out.println("User: " + user1);
            System.out.println("Author: " + author1);

            // Book book1 = book.getBookByIsbn("123123333");

            // System.out.println(book1.toString());

        } catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }

    }

}
