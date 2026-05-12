package se.yrgo.spring.client;

import java.util.*;

import org.springframework.context.support.*;

import se.yrgo.spring.dataaccess.BookNotFoundException;
import se.yrgo.spring.dataaccess.UserNotFoundException;
import se.yrgo.spring.domain.Author;
import se.yrgo.spring.domain.Book;
import se.yrgo.spring.domain.Loan;
import se.yrgo.spring.domain.User;
import se.yrgo.spring.misc.*;
import se.yrgo.spring.services.author.*;
import se.yrgo.spring.services.book.*;
import se.yrgo.spring.services.loan.*;
import se.yrgo.spring.services.user.*;

public class LibraryApplication {

    public void runLibrary() {
        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {

            AuthorService author = container.getBean(AuthorService.class);
            BookService book = container.getBean(BookService.class);
            UserService user = container.getBean(UserService.class);
            LoanService loan = container.getBean(LoanService.class);

            Set<String> ids = new HashSet<>();
            UniqueIdGenerator idGenerator = new UniqueIdGenerator();

            while (true) {
                cleanScreen();
                Scanner input = new Scanner(System.in);
                System.out.printf("""
                        Välkommen till biblioteket
                        1. Logga in/Skapa användare
                        2. Redigera användare
                        3. Låna en bok
                        4. Visa dina lån
                        5. Admin meny
                        0. Avsluta
                        : """);
                String choice = input.nextLine();
                switch (choice) {
                    case "1" -> {
                        cleanScreen();
                        signUp(user, ids, idGenerator, input);
                    }
                    case "2" -> {
                        cleanScreen();
                        editUser(user, input, choice);
                    }
                    case "3" -> {
                        cleanScreen();
                        createLoan(book, user, loan, ids, idGenerator, input);
                    }
                    case "4" -> {
                        cleanScreen();
                        showLoans(user, input);
                    }
                    case "5" -> {
                        cleanScreen();
                        adminMenu(author, book, user, loan, ids, idGenerator, input);
                    }
                    case "0" -> {
                        return;
                    }
                    default -> {
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }

    }

    public void createMockData() {
        try (ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml")) {
            AuthorService author = container.getBean(AuthorService.class);
            BookService book = container.getBean(BookService.class);
            UserService user = container.getBean(UserService.class);
            LoanService loan = container.getBean(LoanService.class);

            Set<String> ids = new HashSet<>();
            UniqueIdGenerator idGenerator = new UniqueIdGenerator();
        }
    }

    private static void enterMethod(Scanner input, String spacer) {
        System.out.println(spacer.repeat(10));
        cursiveText("klicka ENTER för att fortsätta");
        input.nextLine();
    }

    private static void cursiveText(String x) {
        System.out.println("\u001B[3m" + x + "\u001B[0m");
    }

    private static void cleanScreen() {
        System.out.println("\033[H\033[2J");
    }

    private static void spacer(String x) {
        System.out.println(x.repeat(10));
    }
    
    private static void signUp(UserService user, Set<String> ids, UniqueIdGenerator idGenerator, Scanner input)
            throws UserNotFoundException {
        String email;
        String password;
        String firstName;
        String lastName;
        String address;
        String zip;
        String city;
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
        cleanScreen();
        user.addUser(idGenerator.generateUniqueId(ids), firstName, lastName, email, password, address,
                zip, city);
        System.out
                .println("Lade till användare: " + user.findUserByEmail(email));
        enterMethod(input, "");
    }

    // Adam
    // Created the method editUser, where you can change mail, password, name,
    // address, zip,
    // and city for a specific user
    private static void editUser(UserService user, Scanner input, String choice) throws UserNotFoundException {
        System.out.printf("Skriv in din mail: ");
        String emailChoice = input.nextLine();
        User theUser = user.findUserByEmail(emailChoice);

        while (!choice.equals("0")) {
            cleanScreen();
            System.out.printf("""
                    Information:
                        Namn: %s %s
                        E-mail: %s
                        Adress: %s %s %s%n
                    """, theUser.getFirstName(), theUser.getLastName(), theUser.getEmail(), theUser.getAdress(),
                    theUser.getZip(), theUser.getCity());
            System.out.printf("""
                    1. Ändra mail
                    2. Ändra lösenord
                    3. Ändra namn
                    4. Ändra postadress
                    0. Tillbaka
                    : """);
            choice = input.nextLine();

            switch (choice) {
                case "1" -> {
                    cleanScreen();
                    System.out.print("Skriv din nya mail: ");
                    String newMail = input.nextLine();
                    cleanScreen();
                    theUser.setEmail(newMail);
                    System.out.printf("Din nya mail: %s%n", theUser.getEmail());
                    enterMethod(input, "");
                }
                case "2" -> {
                    cleanScreen();
                    System.out.print("Skriv in ditt nya lösenord: ");
                    String newPassword = input.nextLine();
                    cleanScreen();
                    theUser.setPassword(newPassword);
                    System.out.println("Ditt lösenord har ändrats");
                    enterMethod(input, "");
                }
                case "3" -> {
                    cleanScreen();
                    System.out.print("Skriv ditt nya förnamn: ");
                    String newFirstName = input.nextLine();
                    System.out.print("Skriv ditt nya efternamn: ");
                    String newLastName = input.nextLine();
                    cleanScreen();
                    theUser.setFirstName(newFirstName);
                    theUser.setLastName(newLastName);
                    System.out.printf("Ditt nya namn: %s %s%n", theUser.getFirstName(), theUser.getLastName());
                    enterMethod(input, "");

                }
                case "4" -> {
                    cleanScreen();
                    System.out.print("Skriv din nya adress: ");
                    String newAddress = input.nextLine();
                    System.out.print("Skriv ditt nya postnummer: ");
                    String newZip = input.nextLine();
                    System.out.print("Skriv din nya stad: ");
                    String newCity = input.nextLine();
                    cleanScreen();
                    theUser.setAdress(newAddress);
                    theUser.setZip(newZip);
                    theUser.setCity(newCity);
                    System.out.printf("Din nya adress: %s %s %s%n", theUser.getAdress(), theUser.getZip(),
                            theUser.getCity());
                    enterMethod(input, "");
                }
                default ->
                    System.out.println("Något gick fel. Försök igen!");
            }
            user.updateUser(theUser.getUserId(),
                    theUser.getFirstName(),
                    theUser.getLastName(),
                    theUser.getEmail(),
                    theUser.getPassword(),
                    theUser.getAdress(),
                    theUser.getZip(),
                    theUser.getCity());

            User testUser = user.findUserById(theUser.getUserId());
            System.out.println(testUser.toString());
        }
    }

    // Adam
    // Created method createLoan, to be able to create a loan and put books into
    // loan
    private static void createLoan(BookService book, UserService user, LoanService loan, Set<String> ids,
            UniqueIdGenerator idGenerator, Scanner input) throws BookNotFoundException {
        String choice;
        System.out.print("Skriv in din mail: ");
        User theUser = null;

        while (theUser == null) {
            try {
                theUser = user.findUserByEmail(input.nextLine());
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.print("Försök igen: ");
            }
        }

        // Någon hantering av när man skrivit fel mail, eller mail som inte är
        // registrerad med en användare?

        Set<Book> booksToLoan = new HashSet<>();

        System.out.println("Hej " + theUser.getFirstName() + " " + theUser.getLastName() + "!");
        while (true) {
            System.out.println("Skriv ISBN på bok som du vill låna (skriv '0' när du är klar):");
            System.out
                    .println("(om du ångrar lån av en bok, skriv ISBN på den boken du ångrade dig på)");
            for (Book aBook : book.getEntireCatalogue()) {
                if (aBook.isAvailable()) {
                    System.out.println("-------------");
                    System.out.println(aBook.toString());
                }
            }
            choice = input.nextLine();

            if (choice.equals("0"))
                break;

            if (booksToLoan.contains(book.getBookByIsbn(choice))) {
                booksToLoan.remove(book.getBookByIsbn(choice));
                System.out
                        .println("Tog bort " + book.getBookByIsbn(choice).getTitle() + " från lånet\n");
            } else {
                booksToLoan.add(book.getBookByIsbn(choice));
                System.out.println(book.getBookByIsbn(choice).getTitle() + " lades till i lånet!\n");
            }
        }

        if (!booksToLoan.isEmpty()) {
            Date startDate = new Date();
            Date dueDate = new Date(startDate.getTime() + 14L * 24 * 60 * 60 * 1000);

            loan.addLoan(idGenerator.generateUniqueId(ids), booksToLoan, startDate, dueDate, theUser);

            for (Book unavailableBook : booksToLoan) {
                unavailableBook.setAvailable(false);
                book.updateBook(unavailableBook);
            }

            System.out.println("Lån har skapats!");
            System.out.println("Följande böcker har blivit utlånade:");
            booksToLoan.forEach(System.out::println);
            enterMethod(input, "");
        }
    }

    private static void showLoans(UserService user, Scanner input) throws UserNotFoundException {
        System.out.print("Skriv in din mail: ");
        User theUser = null;

        while (theUser == null) {
            try {
                theUser = user.findUserByEmail(input.nextLine());
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.print("Försök igen: ");
            }
        }

        List<Loan> loans = theUser.getLoans();
        loans.forEach(System.out::println);
        System.out.print("Skriv 0 för att avsluta: ");
        input.nextLine();
    }

    private static void adminMenu(AuthorService author, BookService book, UserService user, LoanService loan,
            Set<String> ids, UniqueIdGenerator idGenerator, Scanner input) throws BookNotFoundException {
        boolean loggedIn = false;
        while (true) {
            String choice = "";
            System.out.print("Skriv in ditt användarnamn: \n (0 för att gå tillbaka)\n:");
            String userName = input.nextLine();
            if (userName.equals("0")) {
                break;
            }
            cleanScreen();
            System.out.print("Skriv in ditt lösenord: \n (0 för att gå tillbaka)\n:");
            String passKey = input.nextLine();
            if (passKey.equals("0")) {
                break;
            }
            if (userName.equals("admin") && passKey.equals("sa")) {
                loggedIn = true;
                cleanScreen();
                System.out.println("ADMIN");
                while (loggedIn) {
                    cleanScreen();
                    System.out.printf("""
                            1. Hantera användare
                            2. Hantera böcker
                            3. Hantera lån
                            4. Hantera författare
                            0. Logga ut
                            """);

                    choice = input.nextLine();
                    switch (choice) {
                        case "1" -> {
                            cleanScreen();
                            manageUsers(user, input);
                        }
                        case "2" -> {
                            cleanScreen();
                            manageBooks(author, book, ids, idGenerator, input);
                        }
                        case "3" -> {
                            cleanScreen();
                            manageLoans(loan, input);
                        }
                        case "4" -> {
                            cleanScreen();
                            manageAuthors(author, ids, idGenerator, input);
                        }
                        case "0" -> {
                            loggedIn = false;
                        }
                        default -> {
                            System.out.println("Något gick fel!");
                        }
                    }
                }
            }

            break;
        }
    }

    // Adam
    // Created method manageAuthors to be able to show authors, add authors, and
    // remove authors
    private static void manageAuthors(AuthorService author, Set<String> ids, UniqueIdGenerator idGenerator,
            Scanner input) {
        String choice;
        boolean authorMenu = true;
        System.out.println("Författare");
        while (authorMenu) {
            List<Author> authors = author.getAllAuthors();
            if (authors.isEmpty()) {
                System.out.println("Det finns inga författare för tillfället.");
            } else {
                authors.forEach(System.out::println);
                spacer("-");
            }

            System.out.printf("""
                    1. Lägg till författare
                    2. Ta bort författare
                    0. Tillbaka
                    """);
            choice = input.nextLine();
            switch (choice) {
                case "2" -> {
                    System.out.println("Lägg till författare.");
                    System.out.println("Namn: ");
                    String authorName = input.nextLine();

                    author.addAuthor(idGenerator.generateUniqueId(ids), authorName);
                }
                case "3" -> {
                    System.out.println("Skriv namn på författare du vill ta bort:");
                    String authorName = input.nextLine();

                    Author theAuthor = author.findAuthorByName(authorName);

                    author.deleteAuthor(theAuthor);

                    System.out.println("Tog bort " + authorName);
                }
                case "0" -> {
                    authorMenu = false;
                }
                default -> {
                    System.out.println("Någonting gick fel...");
                }

            }
        }
    }

    // Adam
    // Contributed to creating method manageLoans
    private static void manageLoans(LoanService loan, Scanner input) {
        String choice;
        boolean loanMenu = true;
        System.out.println("Lån");
        while (loanMenu) {
            List<Loan> loans = loan.getAllLoans();
            if (loans.isEmpty()){
                System.out.println("Det finns inga lån för tillfället.");
            }else{
                loans.forEach(System.out::println);
                spacer("-");
            }
            System.out.printf("""
                    1. Ta bort lån
                    2. Uppdatera lån
                    0. Tillbaka
                    """);
            choice = input.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println("Ange lånets ID för att radera lån:");
                    String loanId = input.nextLine();

                    loan.removeLoan(loanId);

                    System.out.println("Lån borttaget.\n");
                }
                case "2" -> {
                    System.out.println("Ange lånets ID för att uppdatera lån:");
                    String loanId = input.nextLine();

                    Loan toUpdate = loan.findLoanById(loanId);

                    loan.updateLoan(toUpdate.getLoanId());
                }
                case "0" -> {
                    loanMenu = false;
                }

                default -> {
                    System.out.println("Någonting gick fel...");
                }
            }
        }
    }

    private static void manageBooks(AuthorService author, BookService book, Set<String> ids,
            UniqueIdGenerator idGenerator, Scanner input) throws BookNotFoundException {
        String choice;
        boolean bookMenu = true;
        System.out.println("Böcker");
        while (bookMenu) {
            System.out.printf("""
                    1. Visa alla böcker
                    2. Lägg till bok
                    3. Ta bort bok
                    0. Tillbaka
                    """);
            choice = input.nextLine();
            switch (choice) {
                case "1" -> {
                    cleanScreen();
                    List<Book> books = book.getEntireCatalogue();
                    books.forEach(System.out::println);
                }
                case "2" -> {
                    cleanScreen();

                    System.out.print("ISBN: ");
                    String isbn = input.nextLine();

                    System.out.print("Titel: ");
                    String title = input.nextLine();

                    System.out.print("Författare: ");
                    String authorName = input.nextLine();

                    Author author1;
                    try {
                        author1 = author.findAuthorByName(authorName);
                    } catch (Exception ex) {
                        author.addAuthor(idGenerator.generateUniqueId(ids), authorName);
                        author1 = author.findAuthorByName(authorName);
                    }

                    Set<Author> authors = new HashSet<>();
                    authors.add(author1);

                    List<Book> books = book.getEntireCatalogue();
                    boolean bookExists = books.stream()
                            .anyMatch(b -> b.getIsbn().equals(isbn));

                    if (bookExists) {
                        System.out.println("Denna bok finns redan i systemet, ISBN: " + isbn);
                    } else {
                        book.registerNewBook(isbn, title, authors);
                        System.out.println("Bok registrerad. \n ISBN: " + isbn + "\nTitel: " + title + "\nFöfattare: "
                                + authorName);
                    }

                }
                case "3" -> {
                    cleanScreen();
                    List<Book> books = book.getEntireCatalogue();
                    books.forEach(System.out::println);
                    spacer("-");
                    System.out.println("Ange bokens ISBN för att radera: ");
                    cursiveText("0 för att avbryta");
                    String isbn = input.nextLine();
                    if (isbn.equals("0")) {
                        break;
                    }
                    Book deletedBook = book.getBookByIsbn(isbn);
                    book.deleteFromStock(isbn);
                    System.out.printf("Tog bort boken: %s %s", deletedBook.getTitle(), deletedBook.getIsbn());
                    cleanScreen();
                }
                case "0" -> {
                    bookMenu = false;
                }

                default -> {
                    System.out.println("Något gick fel. Försök igen!");
                }

            }
        }
    }

    private static void manageUsers(UserService user, Scanner input) {
        String choice;
        boolean userMenu = true;
        System.out.println("Användare:\n");
        while (userMenu) {
            List<User> users = user.getAllUsers();
            users.forEach(System.out::println);
            spacer("-");
            System.out.printf("""
                    1. Uppdatera användare
                    2. Ta bort användare
                    0. Tillbaka
                    """);
            choice = input.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println("Skriv in användarens ID för uppdatering: ");
                    cursiveText("0 för att avbryta");
                    String id = input.nextLine();
                    if (id.equals("0")) {
                        cleanScreen();
                        break;
                    }
                    User theUser = user.findUserById(id);

                    while (!choice.equals("0")) {
                        cleanScreen();
                        System.out.printf("""
                                Information:
                                    Namn: %s %s
                                    E-mail: %s
                                    Adress: %s %s %s%n
                                """, theUser.getFirstName(), theUser.getLastName(), theUser.getEmail(),
                                theUser.getAdress(),
                                theUser.getZip(), theUser.getCity());
                        System.out.printf("""
                                1. Ändra mail
                                2. Ändra lösenord
                                3. Ändra namn
                                4. Ändra postadress
                                0. Tillbaka
                                """);
                        choice = input.nextLine();

                        switch (choice) {
                            case "1" -> {
                                cleanScreen();
                                System.out.println("Skriv ny mail:");
                                String newMail = input.nextLine();
                                theUser.setEmail(newMail);
                            }
                            case "2" -> {
                                cleanScreen();
                                System.out.println("Skriv in nytt lösenord:");
                                String newPassword = input.nextLine();
                                theUser.setPassword(newPassword);
                            }
                            case "3" -> {
                                cleanScreen();
                                System.out.println("Skriv nytt förnamn:");
                                String newFirstName = input.nextLine();
                                System.out.println("Skriv nytt efternamn:");
                                String newLastName = input.nextLine();
                                theUser.setFirstName(newFirstName);
                                theUser.setLastName(newLastName);
                            }
                            case "4" -> {
                                cleanScreen();
                                System.out.println("Skriv ny adress:");
                                String newAddress = input.nextLine();
                                System.out.println("Skriv nytt postnummer:");
                                String newZip = input.nextLine();
                                System.out.println("Skriv ny stad:");
                                String newCity = input.nextLine();
                                theUser.setAdress(newAddress);
                                theUser.setZip(newZip);
                                theUser.setCity(newCity);
                            }
                            case "0" ->
                                System.out.println("");

                            default ->
                                System.out.println("Något gick fel. Försök igen!");
                        }
                    }

                    user.updateUser(theUser.getUserId(),
                            theUser.getFirstName(),
                            theUser.getLastName(),
                            theUser.getEmail(),
                            theUser.getPassword(),
                            theUser.getAdress(),
                            theUser.getZip(),
                            theUser.getCity());

                    User testUser = user.findUserById(theUser.getUserId());
                    System.out.println(testUser.toString());
                }
                case "2" -> {
                    System.out.println("Ange användarens ID för att radera: ");
                    cursiveText("0 för att avbryta");
                    String id = input.nextLine();
                    if (id.equals("0")) {
                        cleanScreen();
                        break;
                    }
                    user.deleteUser(id);
                    System.out.println("Användare borttagen.");
                }
                case "0" -> {
                    userMenu = false;
                }
                default -> {
                    System.out.println("Något gick fel. Försök igen!");
                }
            }
        }
    }
}