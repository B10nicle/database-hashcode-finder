package com.khilkoleg.databaseHashcodeFinder;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Oleg Khilko
 */

public class Settings {
    private Database database;
    private RandomPhoneNumbersGenerator generator;

    public void mainMenu() {
        var input = new Scanner(System.in);
        var userChoice = printMainMenu();
        int amount;

        while (true) {
            switch (userChoice) {
                case "1" -> {
                    System.out.print("\nформат базы данных: ключ-значение\nвведите размер базы данных: ".toUpperCase());
                    amount = input.nextInt();
                    database = new Database(amount);
                    database.save();
                    System.out.println("\nгенерация базы данных успешно завершена".toUpperCase());
                    userChoice = printMainMenu();
                }
                case "2" -> {
                    System.out.print("\nгенерация случайных значений из базы данных\nвведите количество: ".toUpperCase());
                    amount = input.nextInt();
                    generator = new RandomPhoneNumbersGenerator(amount, database);
                    generator.saveRandomHashCodesFromDatabase(generator.getRandomPhoneNumbers());
                    System.out.println("\nгенерация случайных значений успешно завершена".toUpperCase());
                    userChoice = printMainMenu();
                }
                case "3" -> {
                    System.out.println("\nвыполняется поиск...\n".toUpperCase());
                    Finder.findPhoneNumbers(database, generator);
                    userChoice = printMainMenu();
                }
                case "4" -> System.exit(0);

                default -> {
                    System.out.println("\nДанная команда не поддерживается.");
                    userChoice = printMainMenu();
                }
            }
        }
    }

    private static String printMainMenu() {
        var br = new BufferedReader(new InputStreamReader(System.in));
        String choice;
        try {
            System.out.println(("""
                                    
                    1 - генерация базы данных
                    2 - генерация случайных значений
                    3 - поиск по базе данных
                    4 - выход
                    """).toUpperCase());

            choice = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException("В меню произошла ошибка: " + e.getMessage());
        }

        return choice;
    }
}