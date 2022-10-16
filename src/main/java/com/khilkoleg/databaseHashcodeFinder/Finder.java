package com.khilkoleg.databaseHashcodeFinder;

import java.util.Map;

/**
 * @author Oleg Khilko
 */

public class Finder {

    public static void phoneNumbers(Map<String, String> hashMap, String[] randomNumbers) {

        var start = System.nanoTime();

        var sortedHashMap = hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .toList();

        for (String eachRandomNumber : randomNumbers) {
            var finalResult = sortedHashMap.stream()
                    .filter(n -> eachRandomNumber.equals(n.getValue()))
                    .findAny()
                    .orElse(null);
            System.out.println(finalResult);
        }

        long end = System.nanoTime();
        long executionTime = (end - start);

        double seconds = (double) executionTime / 1_000_000_000.0;
        System.out.println("\nвремя выполнения поиска: ".toUpperCase() + seconds + " сек");
    }

}