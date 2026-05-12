package se.yrgo.spring.client;

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
