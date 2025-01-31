package model;

import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

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
    void testConstructor() {
        assertTrue(myAccount.getWishlist().isEmpty());
        assertTrue(myAccount.getPurchased().isEmpty());
        assertTrue(myAccount.getMyListings().isEmpty());
        assertTrue(myAccount.getMyBiddings().isEmpty());
    }

    @Test
    void testAddToWishlist() {
        myAccount.addToWishlist(jordan);
        assertEquals(jordan, myAccount.getWishlist().get(0));
    }

    @Test
    void testAddToPurchased() {
        myAccount.addToPurchased(cdg);
        assertEquals(cdg,myAccount.getPurchased().get(0));
    }

    @Test
    void testAddToMyListings() {
        myAccount.addToMyListings(hoodie);
        assertEquals(hoodie,myAccount.getMyListings().get(0));
    }

    @Test
    void testAddToMyBiddings() {
        myAccount.addToMyBiddings(cdg);
        assertEquals(cdg, myAccount.getMyBiddings().get(0));
    }

    @Test
    void testRemoveListing() {
        myAccount.addToMyListings(cdg);
        myAccount.addToMyListings(jordan);
        myAccount.removeListing(jordan);
        assertEquals(1, myAccount.getMyListings().size());
        assertFalse(myAccount.getMyListings().contains(jordan));
        myAccount.removeListing(hoodie);
        assertEquals(1, myAccount.getMyListings().size());
    }

    @Test
    void testRemoveBiddings() {
        myAccount.addToMyBiddings(cdg);
        myAccount.addToMyBiddings(jordan);
        myAccount.removeFromBiddings(jordan);
        assertEquals(1, myAccount.getMyBiddings().size());
        assertFalse(myAccount.getMyBiddings().contains(jordan));
        myAccount.removeFromBiddings(hoodie);
        assertEquals(1, myAccount.getMyBiddings().size());
    }

    @Test
    void testRemovePurchased() {
        myAccount.addToPurchased(cdg);
        myAccount.addToPurchased(jordan);
        myAccount.removeFromPurchased(jordan);
        assertEquals(1, myAccount.getPurchased().size());
        assertFalse(myAccount.getPurchased().contains(jordan));
        myAccount.removeFromPurchased(hoodie);
        assertEquals(1, myAccount.getPurchased().size());
    }

    @Test
    void testRemoveFromWishlist() {
        myAccount.addToWishlist(cdg);
        myAccount.addToWishlist(jordan);
        myAccount.removeFromWishlist(jordan);
        assertEquals(1, myAccount.getWishlist().size());
        myAccount.removeFromWishlist(cdg);
        assertTrue(myAccount.getWishlist().isEmpty());
        myAccount.addToWishlist(cdg);
        myAccount.addToWishlist(jordan);
        myAccount.removeFromWishlist(hoodie);
        assertEquals(2,myAccount.getWishlist().size());
    }

    @Test
    void testToJson() {
        myAccount.addToPurchased(jordan);
        JSONObject json = myAccount.toJson();
        assertEquals("purchased", json.names().get(0));
        assertEquals("myBiddings", json.names().get(1));
        assertEquals("wishlist", json.names().get(2));
        assertEquals("myListing", json.names().get(3));
    }

    @Test
    void testWishlistToJson() {
        myAccount.addToWishlist(jordan);
        JSONArray array = myAccount.wishlistToJson();
        assertTrue(array.getJSONObject(0).has("buyPrice"));
        assertTrue(array.getJSONObject(0).has("name"));
        assertTrue(array.getJSONObject(0).has("model"));
        assertTrue(array.getJSONObject(0).has("bidPrice"));
        assertTrue(array.getJSONObject(0).has("colour"));
    }

    @Test
    void testBiddingsToJson() {
        myAccount.addToMyBiddings(jordan);
        JSONArray array = myAccount.biddingsToJson();
        assertTrue(array.getJSONObject(0).has("buyPrice"));
        assertTrue(array.getJSONObject(0).has("name"));
        assertTrue(array.getJSONObject(0).has("model"));
        assertTrue(array.getJSONObject(0).has("bidPrice"));
        assertTrue(array.getJSONObject(0).has("colour"));
    }


    @Test
    void testPurchasedToJson() {
        myAccount.addToPurchased(jordan);
        JSONArray array = myAccount.purchasedToJson();
        assertTrue(array.getJSONObject(0).has("buyPrice"));
        assertTrue(array.getJSONObject(0).has("name"));
        assertTrue(array.getJSONObject(0).has("model"));
        assertTrue(array.getJSONObject(0).has("bidPrice"));
        assertTrue(array.getJSONObject(0).has("colour"));
    }

    @Test
    void testListingsToJson() {
        myAccount.addToMyListings(jordan);
        JSONArray array = myAccount.listingsToJson();
        assertTrue(array.getJSONObject(0).has("buyPrice"));
        assertTrue(array.getJSONObject(0).has("name"));
        assertTrue(array.getJSONObject(0).has("model"));
        assertTrue(array.getJSONObject(0).has("bidPrice"));
        assertTrue(array.getJSONObject(0).has("colour"));
    }




}
