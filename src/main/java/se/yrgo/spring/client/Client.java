package se.yrgo.spring.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.spring.services.author.AuthorService;
import se.yrgo.spring.services.book.BookService;
import se.yrgo.spring.services.user.UserService;

public class Client {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {

            AuthorService author = container.getBean(AuthorService.class);
            BookService book = container.getBean(BookService.class);
            UserService user = container.getBean(UserService.class);


        } catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }

    }

}
