package com.khilkoleg.databaseHashcodeFinder;

import java.text.SimpleDateFormat;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

/**
 * @author Oleg Khilko
 */

public class RandomPhoneNumbers {
    private static final String TIME_STAMP = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    private static final File FILE = Path.of("src/main/resources/random_numbers/random_numbers_" + TIME_STAMP + ".txt").toFile();
    private final List<String> randomPhoneNumbers;
    private final int amount;

    public RandomPhoneNumbers(int amount, Database database) {
        randomPhoneNumbers = getRandomHashCodesFromDatabase(amount, database);
        this.amount = amount;
    }

    public List<String> getRandomHashCodesFromDatabase(int amount, Database database) {
        var values = new ArrayList<>(database.getHashCodedPhoneNumbers().values());
        List<String> randomPhoneNumbers = new ArrayList<>(amount);
        values.stream()
                .limit(amount)
                .forEach(n -> randomPhoneNumbers.add(values.get(new Random().nextInt(values.size()))));

        return randomPhoneNumbers;
    }

    public void save(List<String> randomPhoneNumbers) {
        try (var bw = new BufferedWriter(new FileWriter(FILE))) {
            for (var line : randomPhoneNumbers)
                bw.write(line + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка из метода saveRandomHashCodesFromDatabase() " + e.getMessage());
        }
    }

    public List<String> getRandomPhoneNumbers() {
        return randomPhoneNumbers;
    }

    public int getAmount() {
        return amount;
    }
}