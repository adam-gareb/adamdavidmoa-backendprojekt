package se.yrgo.spring.misc;

import java.security.*;
import java.util.*;
import java.util.stream.*;

public class UniqueIdGenerator {
    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    private int idLength = 5; // Default to be overridden

    public void setIdLength(int idLength) {
        this.idLength = idLength;
    }

    public String generateUniqueId(Set<String> existingIds) {
        String newId;
        do {
            newId = generateRandomString(this.idLength);
        } while (existingIds.contains(newId));
        return newId;
    }

    private String generateRandomString(int length) {
        return random.ints(length, 0, ALPHANUMERIC_CHARACTERS.length())
                .mapToObj(ALPHANUMERIC_CHARACTERS::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
