package com.khilkoleg.databaseHashcodeFinder;

import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

/**
 * @author Oleg Khilko
 */

public class Database {
    private static final String TIME_STAMP = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    private static final File FILE = Path.of("src/main/resources/databases/database_" + TIME_STAMP + ".json").toFile();
    private Map<String, String> hashCodedPhoneNumbers;
    private static Properties properties;
    private static final int HEADER = 1;
    private List<String> phoneNumbers;

    public Database(int amount) {
        phoneNumbers = getPhoneNumbers(amount);
        hashCodedPhoneNumbers = getHashed();
        properties = new Properties();
    }

    private List<String> getPhoneNumbers(int amount) {
        var random = new Random();
        long minPhoneNumber = 7_900_000_00_00L;
        long maxPhoneNumber = 7_999_999_99_99L;
        phoneNumbers = new ArrayList<>(HEADER + amount);

        for (int i = 0; i < amount; i++) {
            phoneNumbers.add(String.valueOf((long) (minPhoneNumber + (random.nextDouble() * (maxPhoneNumber - minPhoneNumber)))));
            phoneNumbers.set(i, Long.toString(Long.parseLong(phoneNumbers.get(i))));
        }

        return phoneNumbers;
    }

    private Map<String, String> getHashed() {
        hashCodedPhoneNumbers = new HashMap<>(HEADER + phoneNumbers.size());

        for (var phoneNumber : phoneNumbers) {
            var hashPhoneNumbers = phoneNumber.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA256");
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Данный алгоритм не поддерживается: " + e.getMessage());
            }
            assert md != null;
            hashCodedPhoneNumbers.put(phoneNumber, String.format("%032x", new BigInteger(1, md.digest(hashPhoneNumbers))));
        }

        return hashCodedPhoneNumbers;
    }

    public void save() {
        properties.putAll(hashCodedPhoneNumbers);
        try {
            properties.store(new FileOutputStream(FILE), null);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка из метода save: " + e.getMessage());
        }
    }

    public Map<String, String> getHashCodedPhoneNumbers() {
        return hashCodedPhoneNumbers;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }
}