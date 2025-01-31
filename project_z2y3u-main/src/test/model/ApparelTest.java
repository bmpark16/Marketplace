package model;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApparelTest {

    private Apparel jordan;
    private Apparel hoodie;
    private Apparel cdg;
    private Account myAccount;

    @BeforeEach
    void setup() {
        jordan = new Apparel("Nike", "Air Jordan 1", "White",450,380);
        hoodie = new Apparel("Nike", "Big Swoosh Hoodie", "White",75, 40);
        cdg = new Apparel("CDG", "Heart Shirt", "White",55, 25);
        myAccount = new Account();
    }

    @Test
    public void testConstructor() {
        Apparel jordan = new Apparel("Nike", "Swoosh Hoodie", "White",45, 38);
        assertEquals("Nike", jordan.getName());
        assertEquals("White", jordan.getColour());
        assertEquals("Swoosh Hoodie", jordan.getModel());
        assertEquals(45, jordan.getBuyPrice());
        assertEquals(38, jordan.getBidPrice());
    }

    @Test
    public void testSetBid(){
        jordan.setBid(40);
        assertEquals(40, jordan.getBidPrice());
    }

    @Test
    void testToJson() {
        JSONObject json = jordan.toJson();
        assertEquals("Nike", json.get("name"));
        assertEquals("Air Jordan 1", json.get("model"));
        assertEquals("White", json.get("colour"));
        assertEquals("450", json.get("buyPrice"));
        assertEquals("380", json.get("bidPrice"));
    }

    @Test
    void testToString() {
        String stringOne = " " + "Air Jordan 1" + " | " + " " +  "White" + " | " + " " + "Nike" + " | ";
        String stringTwo = " Buy Now Price: " + "$450" + " | ";
        String stringThree = " Current Bid: " + "$380";
        assertEquals(stringOne + stringTwo + stringThree, jordan.toString());
    }

}
