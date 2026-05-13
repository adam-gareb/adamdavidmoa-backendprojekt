package se.yrgo.spring.servicesTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.transaction.annotation.*;

import se.yrgo.spring.dataaccess.*;
import se.yrgo.spring.domain.*;
import se.yrgo.spring.services.user.*;

//This class creates tests for the UserServiceImpl.
@ExtendWith(SpringExtension.class)
@ContextConfiguration({ "/other-tiers.xml", "/datasource-test.xml" })
@Transactional
public class UserServiceTest {

    @Autowired
    UserService user;

    @Test
    public void testFindUserByIdTest() {
        try {
            User testUser = user.addUser("GrX56",
                    "Apa",
                    "Mannen",
                    "gurka@gurka.se",
                    "bassword",
                    "Vägen", "45556", "Staden");
            User foundUser = user.findUserById("GrX56");
            assertEquals(testUser, foundUser, "The user returned is the wrong user!");
        } catch (UserNotFoundException ex) {
            fail("No User was found when there should be a User found...");
        }
    }

    @Test
    public void testFindUserByEmail() {
        try {
            User testUser = user.addUser("GrX56",
                    "Bapa",
                    "Mannen",
                    "burkan@burka.se",
                    "bassord",
                    "Vägen", "45556", "Staden");
            User foundUser = user.findUserByEmail("burkan@burka.se");
            assertEquals(testUser, foundUser, "The user returned is the wrong user!");
        } catch (UserNotFoundException ex) {
            fail("No User was found when there should be a User found...");
        }
    }

    @Test
    public void testFindUsersByLastName() {
        user.addUser("tTyTY",
                "Doom", "Guy", "slayer@e.se",
                "wordPass",
                "Hell", "33333", "Citadel");

        user.addUser("tTy56",
                "Fiery", "Guy", "fire@kitchen.se",
                "wordPass",
                "Los Angeles", "44455", "USA");

        assertEquals(2, user.findUsersByLastName("Guy").size(),
                "There should be two users with the last name Guy");
    }

    @Test
    public void testAddingUsers() {
        user.addUser("UfX2f",
                "David",
                "Sson",
                "david@test.com",
                "test",
                "Gatan", "333 44", "cicy");

        user.addUser("FufuX",
                "Erik",
                "Sson",
                "erik@test.com",
                "test",
                "Gatan", "333 44", "cicy");

        int usersInDb = user.getAllUsers().size();
        assertEquals(2, usersInDb, "There should be two users in the database!");
    }

    @Test
    public void testFindingNonExistentUser() {
        assertThrows(UserNotFoundException.class, () -> {
            user.findUserByEmail("appapapapapapapapa@apa.se");
        });
    }

    @Test
    public void testUpdatingUser() {
        try {
            User testUser = user.addUser("4xgH6",
                    "Braken", "Smaken",
                    "korven@gkrvo.se",
                    "123123",
                    "123 apaväg",
                    "132 33",
                    "GOOOOO");

            user.updateUser(testUser.getUserId(),
                    testUser.getFirstName(),
                    testUser.getLastName(),
                    "gröken@mail.se",
                    testUser.getPassword(),
                    testUser.getAddress(), testUser.getZip(), testUser.getCity());

            User resultUser = user.findUserById("4xgH6");
            assertEquals("gröken@mail.se", resultUser.getEmail(),
                    "The email should be updated");
        } catch (UserNotFoundException e) {
            fail("No User was found when there should be a User found...");
        }
    }

    @Test
    public void testDeletingUser() {
        user.addUser("123gH",
                "Flesken", "Busken", "busk@musk.se",
                "korv", "Apan", "33333", "Stan");

        user.deleteUser("123gH");

        assertThrows(UserNotFoundException.class, () -> {
            user.findUserById("123gH");
        });
    }
}
