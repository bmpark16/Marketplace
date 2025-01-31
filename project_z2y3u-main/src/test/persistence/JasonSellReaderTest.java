package persistence;

import model.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import model.Selling;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.fail;

public class JasonSellReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonSellReader reader = new JsonSellReader();
        try {
            Selling sell = reader.read("./data/noSuchFile.json");
            fail("Exception should be thrown");
        } catch (IOException e) {
            //expected to catch exception
        }
    }


    @Test
    void testReaderEmptySell() {
        JsonSellReader reader = new JsonSellReader();

        try {
            Selling sell = reader.read("./data/EmptySell.json");
            assertTrue(sell.getForSale().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderInitialSell() {
        JsonSellReader reader = new JsonSellReader();

        try {
            Selling sell = new Selling();
            sell = reader.read("./data/InitialSell.json");
            assertEquals(4, sell.getForSale().size());
            assertEquals("NIKE", sell.getForSale().get(0).getName());
            assertEquals("STUSSY", sell.getForSale().get(1).getName());
            assertEquals("CDG", sell.getForSale().get(2).getName());
            assertEquals("ADIDAS", sell.getForSale().get(3).getName());
        } catch (IOException e) {
            fail("couldn't read from file");
        }
    }
}
