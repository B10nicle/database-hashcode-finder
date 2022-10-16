package com.khilkoleg.databaseHashcodeFinder;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Oleg Khilko
 */

public class Menu {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        var database = new Database();
        int userChoice;
        userChoice = menu();
        int amount;
        if (userChoice == 1) {
            System.out.print("\nформат базы данных: ключ-значение\nвведите размер базы данных: ".toUpperCase());
            amount = input.nextInt();
            database.save(
                    database.hash(
                            database.create(amount)
                    )
            );
            System.out.println("\nгенерация базы данных успешно завершена".toUpperCase());
            userChoice = menu();
        }
        if (userChoice == 2) {
            database.load();
            System.out.print("\nгенерация случайных значений из базы данных\nвведите количество: ".toUpperCase());
            amount = input.nextInt();
            database.saveRandomNumbers(database.createRandomNumbers(amount));
            database.loadRandomNumbers();
            System.out.println("\nгенерация случайных значений успешна завершена".toUpperCase());
            userChoice = menu();
        }
        if (userChoice == 3) {
            System.out.println("\nвыполняется поиск...\n".toUpperCase());
            Find.phoneNumbers(database.load(), database.loadRandomNumbers());
        }
    }

    public static int menu() {
        byte selection;
        Scanner input = new Scanner(System.in);
        System.out.println(("""

                1 - генерация базы данных
                2 - генерация случайных значений
                3 - поиск по базе данных
                4 - выход""").toUpperCase());

        selection = input.nextByte();
        return selection;
    }
}