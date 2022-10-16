package com.khilkoleg.databaseHashcodeFinder;

import com.khilkoleg.databaseHashcodeFinder.exceptions.*;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Oleg Khilko
 */

public class Settings {
    private Database database;

    public void mainMenu() {
        var input = new Scanner(System.in);
        var userChoice = printMainMenu();
        int amount;

        while (true) {
            switch (userChoice) {
                case "1" -> {
                    System.out.print("\nформат базы данных: ключ-значение\nвведите размер базы данных: ".toUpperCase());
                    amount = input.nextInt();
/*                    try {
                        database = new Database(amount);
                        var hashCodedPhoneNumbers = database.hash();
                        database.save(hashCodedPhoneNumbers);
                        System.out.println("\nгенерация базы данных успешно завершена".toUpperCase());
                        userChoice = printMainMenu();
                    } catch (IOException e) {
                        throw new MenuException("Ошибка при сохранении: " + e.getMessage());
                    }*/
                }
                case "2" -> {
/*                    try {
                        database.load();
                        System.out.print("\nгенерация случайных значений из базы данных\nвведите количество: ".toUpperCase());
                        amount = input.nextInt();
                    } catch (IOException e) {
                        throw new MenuException("Ошибка при загрузки: " + e.getMessage());
                    }
                    try {
                        database.saveRandomNumbers(database.createRandomNumbers(amount));
                        database.loadRandomNumbers();
                        System.out.println("\nгенерация случайных значений успешно завершена".toUpperCase());
                        userChoice = printMainMenu();
                    } catch (IOException e) {
                        throw new MenuException("Ошибка при загрузки и сохранении случайных чисел: " + e.getMessage());
                    }*/
                }
                case "3" -> {
/*                    try {
                        System.out.println("\nвыполняется поиск...\n".toUpperCase());
                        Finder.phoneNumbers(database.load(), database.loadRandomNumbers());
                        userChoice = printMainMenu();
                    } catch (IOException e) {
                        throw new MenuException("Ошибка при поиске: " + e.getMessage());
                    }*/
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
            throw new MenuException("В меню произошла ошибка: " + e.getMessage());
        }

        return choice;
    }
}