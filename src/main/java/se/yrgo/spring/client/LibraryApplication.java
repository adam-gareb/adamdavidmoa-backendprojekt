package se.yrgo.spring.client;

import java.util.*;

import org.springframework.context.support.*;

import se.yrgo.spring.misc.*;
import se.yrgo.spring.services.author.*;
import se.yrgo.spring.services.book.*;
import se.yrgo.spring.services.loan.*;
import se.yrgo.spring.services.user.*;

public class LibraryApplication {

    public void runLibrary() {
        try (
                ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {
            AuthorService author = container.getBean(AuthorService.class);
            BookService book = container.getBean(BookService.class);
            UserService user = container.getBean(UserService.class);
            LoanService loan = container.getBean(LoanService.class);

            Set<String> ids = new HashSet<>();
            UniqueIdGenerator idGenerator = new UniqueIdGenerator();
        }

    }

    public void createMockData() {
        try (
                ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {
            AuthorService author = container.getBean(AuthorService.class);
            BookService book = container.getBean(BookService.class);
            UserService user = container.getBean(UserService.class);
            LoanService loan = container.getBean(LoanService.class);

            Set<String> ids = new HashSet<>();
            UniqueIdGenerator idGenerator = new UniqueIdGenerator();
        }
    }

}
