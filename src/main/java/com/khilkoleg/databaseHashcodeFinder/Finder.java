package com.khilkoleg.databaseHashcodeFinder;

import java.util.Map;

/**
 * @author Oleg Khilko
 */

public class Finder {

    public static void findPhoneNumbers(Database database, RandomPhoneNumbersGenerator generator) {
        var start = System.nanoTime();

        var sortedHashMap = database.getHashCodedPhoneNumbers()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        for (var number : generator.getRandomPhoneNumbers()) {
            sortedHashMap.stream()
                    .filter(n -> number.equals(n.getValue()))
                    .findAny()
                    .ifPresent(System.out::println);
        }
        long end = System.nanoTime();
        double seconds = (end - start) / 1_000_000_000d;
        System.out.println("\nвремя выполнения поиска: ".toUpperCase() + seconds + " сек");
    }
}