package com.khilkoleg.databaseHashcodeFinder;

import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.math.BigInteger;
import java.util.*;
import java.io.*;

/**
 * @author Oleg Khilko
 */

public class Database {

    String timeStamp = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    Map<String, String> hashMap = new HashMap<>();

    public String[] create(int amount) {
        Random random = new Random();
        long[] phoneNumbers = new long[amount];
        String[] strPhoneNumbers = new String[amount];
        long minPhoneNumber = 7_900_000_00_00L;
        long maxPhoneNumber = 7_999_999_99_99L;
        for (int i = 0; i < amount; i++) {
            phoneNumbers[i] = minPhoneNumber + ((long) (random.nextDouble() * (maxPhoneNumber - minPhoneNumber)));
            String strPhoneNumber = Long.toString(phoneNumbers[i]);
            strPhoneNumbers[i] = strPhoneNumber;
        }
        return strPhoneNumbers;
    }

    public Map<String, String> hash(String[] strPhoneNumbers) {
        Map<String, String> hashMap = new HashMap<>(strPhoneNumbers.length);
        byte[] hashOfPhoneNumbers;
        for (String strPhoneNumber : strPhoneNumbers) {
            hashOfPhoneNumbers = strPhoneNumber.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            hashMap.put(strPhoneNumber, String.format("%032x", new BigInteger(1, md.digest(hashOfPhoneNumbers))));
        }
        return hashMap;
    }

    public void save(Map<String, String> hashMap) throws IOException {

        var properties = new Properties();
        properties.putAll(hashMap);
        properties.store(new FileOutputStream("src/main/resources/database_" + timeStamp + ".json"), null);

    }

    public Map<String, String> load() throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/database_" + timeStamp + ".json"));
        for (String key : properties.stringPropertyNames()) {
            hashMap.put(key, properties.get(key).toString());
        }

        return hashMap;

    }

    public String[] createRandomNumbers(int amount) {
        ArrayList<String> values = new ArrayList<>(hashMap.values());
        String[] result = new String[amount];
        for (int i = 0; i < amount; i++) {
            String randomNumber = values.get(new Random().nextInt(values.size()));
            result[i] = randomNumber;
        }
        return result;
    }

    public void saveRandomNumbers(String[] result) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/olegkhilko/Desktop/Java/Projects/Database/random_numbers_" + timeStamp + ".json"));
        for (String s : result) {
            writer.write(s + "\n");
        }
        writer.close();
    }

    public String[] loadRandomNumbers() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("/Users/olegkhilko/Desktop/Java/Projects/Database/random_numbers_" + timeStamp + ".json"));
        return properties.stringPropertyNames().toArray(new String[0]);
    }
}