package persistence;

import model.Account;
import model.Selling;
import model.Apparel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    private Apparel a;
    private Apparel i;
    private Apparel k;
    private Apparel j;

    @BeforeEach
    void setup() {
        a = new Apparel("a", "model", "colour", 25, 10);
        i = new Apparel("i", "model", "colour", 120,80);
        k = new Apparel("k", "model", "colour", 25, 10);
        j = new Apparel("j", "model", "colour", 25, 10);
    }


    @Test
    void testWriterInvalidFile() {
        try {
            Account acc = new Account();
            JsonWriter writer = new JsonWriter();
            writer.open("./data/my\0illegal:fileName.json");
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccount() {
        try {
            JsonWriter writer = new JsonWriter();
            Account acc = new Account();
            writer.open("./data/EmptyAccount.json");
            writer.write(acc);
            writer.close();

            JsonAccountReader reader = new JsonAccountReader();
            acc = reader.read("./data/EmptyAccount.json");
            assertTrue(acc.getPurchased().isEmpty());
            assertTrue(acc.getMyBiddings().isEmpty());
            assertTrue(acc.getWishlist().isEmpty());
            assertTrue(acc.getMyListings().isEmpty());
        } catch (IOException e) {
            fail("unexpected exception thrown");
        }
    }

    @Test
    void testWriterInitialSelling() {
        try {
            JsonWriter writer = new JsonWriter();
            Selling sell = new Selling();
            writer.open("./data/InitialSell.json");
            writer.write(sell);
            writer.close();

            JsonSellReader reader = new JsonSellReader();
            sell = reader.read("./data/InitialSell.json");
            assertEquals(4, sell.getForSale().size());
            assertEquals("NIKE", sell.getForSale().get(0).getName());
            assertEquals("STUSSY", sell.getForSale().get(1).getName());
            assertEquals("CDG", sell.getForSale().get(2).getName());
            assertEquals("ADIDAS", sell.getForSale().get(3).getName());
        } catch (IOException e) {
            fail("unexpected exception thrown");
        }
    }

    @Test
    void testWriterGeneralAccount() {
        try{
            JsonWriter writer = new JsonWriter();
            Account account = new Account();
            account.addToWishlist(a);
            account.addToMyBiddings(i);
            account.addToMyListings(k);
            account.addToPurchased(j);
            writer.open("./data/GeneralAccount.json");
            writer.write(account);
            writer.close();

            JsonAccountReader reader = new JsonAccountReader();
            account = reader.read("./data/GeneralAccount.json");
            assertEquals(1, account.getPurchased().size());
            assertEquals(1, account.getWishlist().size());
            assertEquals(1, account.getMyListings().size());
            assertEquals(1, account.getMyBiddings().size());
        } catch (IOException e) {
            fail("unexpected exception thrown");
        }
    }

    @Test
    void testWriterEmptySelling() {
        try {
            JsonWriter writer = new JsonWriter();
            Selling sell = new Selling();
            sell.clearSell();
            writer.open("./data/EmptySell.json");
            writer.write(sell);
            writer.close();

            JsonSellReader reader = new JsonSellReader();
            sell = reader.read("./data/EmptySell.json");
            assertTrue(sell.getForSale().isEmpty());
        } catch (IOException e) {
            fail("unexpected exception thrown");
        }
    }
}


