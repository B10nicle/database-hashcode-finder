package databaseHashcodeFinder;

import com.khilkoleg.databaseHashcodeFinder.Database;
import com.khilkoleg.databaseHashcodeFinder.RandomPhoneNumbersGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * @author Oleg Khilko
 */

public class RandomPhoneNumbersGeneratorTest extends Assertions {
    private static final String TIME_STAMP = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    private static final File FILE = Path.of("src/main/resources/random_numbers_" + TIME_STAMP + ".json").toFile();
    private RandomPhoneNumbersGenerator generator;
    private List<String> randomPhoneNumbers;
    private static Properties properties;
    private Database database;

    @BeforeEach
    public void initializeDatabases() {
        generator = new RandomPhoneNumbersGenerator();
        database = new Database(10_000);
    }

    @Test
    public void createDatabaseTest() {
        randomPhoneNumbers = generator.createRandomPhoneNumbers(100, database.getHashCodedPhoneNumbers());
        System.out.println("Размер базы данных рандомных номеров: " + randomPhoneNumbers.size() + " телефонных номеров.");

        assertEquals(100, randomPhoneNumbers.size());

    }

   /* @Test
    public void createHashCodedPhoneNumbersTest() {
        System.out.println("Количество хэшированных телефонных номеров в базе данных: "
                + database.getHashCodedPhoneNumbers().size() + " телефонных номеров.");

        assertNotNull(database);
        assertEquals(testDatabase.getHashCodedPhoneNumbers().size(), database.getHashCodedPhoneNumbers().size());
    }

    @Test
    public void databaseSaveTest() throws IOException {
        properties.putAll(database.getHashCodedPhoneNumbers());

        properties.store(new FileOutputStream(FILE), null);

        assertTrue(Files.exists(FILE.toPath()));
    }

    @Test
    public void databaseLoadTest() throws IOException {
        var newDatabase = new Database(0);
        properties.load(new FileInputStream(FILE));
        properties.stringPropertyNames()
                .forEach(key -> newDatabase.getHashCodedPhoneNumbers()
                        .put(key, properties.get(key).toString()));

        assertNotNull(newDatabase);
        assertEquals(10_000, newDatabase.getHashCodedPhoneNumbers().size());
    }*/

}