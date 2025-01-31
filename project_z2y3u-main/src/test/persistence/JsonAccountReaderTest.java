package persistence;

import model.Account;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.fail;


public class JsonAccountReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonAccountReader reader = new JsonAccountReader();
        try {
            Account acc = reader.read("./data/noSuchFile.json");
            fail("Exception should be thrown");
        } catch (IOException e) {
            //expected to catch exception
        }
    }


    @Test
    void testReaderEmptySell() {
        JsonAccountReader reader = new JsonAccountReader();

        try {
            Account acc = reader.read("./data/EmptyAccount.json");
            assertTrue(acc.getPurchased().isEmpty());
            assertTrue(acc.getWishlist().isEmpty());
            assertTrue(acc.getMyListings().isEmpty());
            assertTrue(acc.getMyBiddings().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccount() {
        JsonAccountReader reader = new JsonAccountReader();

        try {
            Account account = new Account();
            account = reader.read("./data/GeneralAccount.json");
            assertEquals(1, account.getWishlist().size());
            assertEquals(1, account.getMyListings().size());
            assertEquals(1, account.getMyBiddings().size());
            assertEquals(1, account.getPurchased().size());
        } catch (IOException e) {
            fail("couldn't read from file");
        }
    }
}
