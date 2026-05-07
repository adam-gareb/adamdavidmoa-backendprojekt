package se.yrgo.spring.client;

import java.util.*;

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
            book.registerNewBook("123123333", "hej", author1);

            Book book1 = book.getBookByIsbn("123123333");

            Author author3 = author.findAuthorByName("Bertil");

            author3.addBookToAuthor(book1);

            Set<Book> books = author3.getAllBooks();

            System.out.println(book1.toString());


            Author newAuthor = author.addAuthor("BAA-9", "Johnson");
            Author newAuthor2 = author.addAuthor("BAA-8", "Rickardsson");

            book.registerNewBook("99999888", "The Book.", newAuthor);
            Book newBook = book.getBookByIsbn("99999888");

            newAuthor.addBookToAuthor(newBook);
            newAuthor.addBookToAuthor(book1);
            
            newBook.addAuthor(newAuthor2);

            books.add(newBook);

            books.forEach(System.out::println);

            System.out.println("Authors:");
            System.out.println(newAuthor.toString());

        } catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }

    }

}
