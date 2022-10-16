package com.khilkoleg.databaseHashcodeFinder;

import java.text.SimpleDateFormat;
import java.nio.file.Path;
import java.util.*;
import java.io.*;

/**
 * @author Oleg Khilko
 */

public class RandomPhoneNumbersGenerator {
    private static final String TIME_STAMP = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    private static final File FILE = Path.of("src/main/resources/random_numbers_" + TIME_STAMP + ".json").toFile();
    private List<String> randomPhoneNumbers;
    private static Properties properties;

    public RandomPhoneNumbersGenerator() {
        properties = new Properties();
    }

    public List<String> createRandomPhoneNumbers(int amount, Map<String, String> hashCodedPhoneNumbers) {
        var values = new ArrayList<>(hashCodedPhoneNumbers.values());
        List<String> randomPhoneNumbers = new ArrayList<>(amount);
        values.stream()
                .limit(amount)
                .forEach(n -> randomPhoneNumbers.add(values.get(new Random().nextInt(values.size()))));

        return randomPhoneNumbers;
    }

    public void saveRandomPhoneNumbers(List<String> randomPhoneNumbers) {
        try (var bw = new BufferedWriter(new FileWriter(FILE))) {
            for (var line : randomPhoneNumbers)
                bw.write(line + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка из метода saveRandomPhoneNumbers: " + e.getMessage());
        }
    }

    private void loadRandomPhoneNumbers() throws IOException {
        properties.load(new FileInputStream(FILE));
        randomPhoneNumbers.forEach(n -> properties.stringPropertyNames());
    }

    public List<String> getRandomPhoneNumbers() {
        return randomPhoneNumbers;
    }
}