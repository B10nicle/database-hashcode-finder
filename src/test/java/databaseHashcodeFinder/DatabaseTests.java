package databaseHashcodeFinder;

import com.khilkoleg.databaseHashcodeFinder.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.util.Properties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import java.io.File;

/**
 * @author Oleg Khilko
 */

public class DatabaseTests extends Assertions {
    private static final String TIME_STAMP = new SimpleDateFormat("ddMMyy").format(Calendar.getInstance().getTime());
    private static final File FILE = Path.of("src/test/resources/databases/database_" + TIME_STAMP + ".json").toFile();
    private static final int amount = 10_000;
    private static Properties properties;
    private static Database testDatabase;
    private static Database database;

    @BeforeEach
    public void initializeDatabases() {
        testDatabase = new Database(amount);
        database = new Database(amount);
        properties = new Properties();
    }

    @Test
    public void createDatabaseTest() {
        System.out.println("Размер базы данных: " + database.getPhoneNumbers().size() + " телефонных номеров.");

        assertNotNull(database);
        assertEquals(testDatabase.getPhoneNumbers().size(), database.getPhoneNumbers().size());
    }

    @Test
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

}