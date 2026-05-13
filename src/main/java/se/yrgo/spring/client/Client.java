package se.yrgo.spring.client;

//The main class that runs the whole program. We call on the method runLibrary() from LibraryApplication.
public class Client {
    public static void main(String[] args) {

        LibraryApplication app = new LibraryApplication();

        try {
            app.runLibrary();
        } catch (Exception ex) {
            System.out.println("Something went wrong with running the application: " + ex.getMessage());
        }
    }
}
