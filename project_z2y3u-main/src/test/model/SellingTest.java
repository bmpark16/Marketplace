package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SellingTest {
    private Selling sell;
    private Apparel a;
    private Apparel i;

    @BeforeEach
    void setup() {
        sell = new Selling();
        a = new Apparel("a", "model", "colour", 25, 10);
        i = new Apparel("i", "model", "colour", 120,80);
    }

    @Test
    void testConstructor() {
        assertEquals(4, sell.getForSale().size());
        assertEquals("NIKE", sell.getForSale().get(0).getName());
        assertEquals("STUSSY", sell.getForSale().get(1).getName());
        assertEquals("CDG", sell.getForSale().get(2).getName());
        assertEquals("ADIDAS", sell.getForSale().get(3).getName());

    }

    @Test
    void testAddToSell() {
        sell.addToSell(a);
        assertEquals(5, sell.getForSale().size());
        assertEquals("a", sell.getForSale().get(4).getName());
        sell.addToSell(i);
        assertEquals(6, sell.getForSale().size());
        assertEquals("i", sell.getForSale().get(5).getName());

    }

    @Test
    void testClearSell() {
        sell.clearSell();
        assertTrue(sell.getForSale().isEmpty());
        sell.addToSell(a);
        assertEquals(1, sell.getForSale().size());
        assertEquals("a", sell.getForSale().get(0).getName());
    }

    @Test
    void testToJson() {
        JSONObject json = sell.toJson();
        assertEquals("sell", json.names().get(0));
    }
}
