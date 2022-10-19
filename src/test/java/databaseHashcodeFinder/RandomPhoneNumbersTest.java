package databaseHashcodeFinder;

import com.khilkoleg.databaseHashcodeFinder.RandomPhoneNumbers;
import com.khilkoleg.databaseHashcodeFinder.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.List;
import java.io.*;

/**
 * @author Oleg Khilko
 */

public class RandomPhoneNumbersTest extends Assertions {
    private static final String TIME_STAMP = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    private static final File FILE = Path.of("src/test/resources/random_numbers/random_numbers_" + TIME_STAMP + ".txt").toFile();
    private List<String> randomPhoneNumbers;
    private RandomPhoneNumbers generator;

    @BeforeEach
    public void initializeDatabases() {
        var database = new Database(10_000);
        generator = new RandomPhoneNumbers(100, database);
        randomPhoneNumbers = generator.getRandomPhoneNumbers();
    }

    @Test
    public void getRandomHashCodesFromDatabaseTest() {
        System.out.println("Размер базы данных рандомных номеров: " + randomPhoneNumbers.size() + " телефонных номеров.");

        assertEquals(generator.getAmount(), randomPhoneNumbers.size());
    }

    @Test
    public void saveRandomHashCodesFromDatabaseTest() throws IOException {
        var writer = new FileWriter(FILE);
        for (var line : randomPhoneNumbers)
            writer.write(line + System.lineSeparator());
        writer.close();

        assertTrue(Files.exists(FILE.toPath()));
    }

}