package persistence;

import model.Apparel;
import model.Account;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// JSONAccountReader's implementation and the quotes are inspired by the JsonSerializationDemo provided from the course

public class JsonAccountReader {

    // EFFECTS : constructs reader to read from source file
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads Account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read(String source) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // EFFECTS: parses Account from JSON object and returns it
    public Account parseAccount(JSONObject jsonObject) {
        Account account = new Account();
        addLists(account, jsonObject);
        return account;
    }

    // MODIFIES : Account
    // EFFECTS : takes in all the lists (wishlist, purchased, myListings, myBiddings) from the given JSONObject
    //           and adds the lists into given Account.
    public void addLists(Account account, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("wishlist");
        for (Object json : jsonArray) {
            JSONObject nextApparel = (JSONObject) json;
            addApparelWishlist(account, nextApparel);
        }

        JSONArray jsonPurchased = jsonObject.getJSONArray("purchased");
        for (Object json : jsonPurchased) {
            JSONObject nextApparel = (JSONObject) json;
            addApparelPurchased(account, nextApparel);
        }

        JSONArray jsonListings = jsonObject.getJSONArray("myListing");
        for (Object json : jsonListings) {
            JSONObject nextApparel = (JSONObject) json;
            addApparelListing(account, nextApparel);
        }

        JSONArray jsonBid = jsonObject.getJSONArray("myBiddings");
        for (Object json : jsonBid) {
            JSONObject nextApparel = (JSONObject) json;
            addApparelBid(account, nextApparel);
        }
    }

    // MODIFIES: account
    // EFFECTS: creates Apparel from the given JSONObject, and adds to the Account's wishlist
    protected void addApparelWishlist(Account account, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String model = jsonObject.getString("model");
        String colour = jsonObject.getString("colour");
        String buyPrice = jsonObject.getString("buyPrice");
        String bidPrice = jsonObject.getString("bidPrice");
        Apparel apparel = new Apparel(name, model, colour, Integer.valueOf(buyPrice), Integer.valueOf(bidPrice));
        account.addToWishlist(apparel);
    }

    // MODIFIES: Account
    // EFFECTS: creates Apparel from the given JSONObject, and adds to the Account's Purchased list
    protected void addApparelPurchased(Account account, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String model = jsonObject.getString("model");
        String colour = jsonObject.getString("colour");
        String buyPrice = jsonObject.getString("buyPrice");
        String bidPrice = jsonObject.getString("bidPrice");
        Apparel apparel = new Apparel(name, model, colour, Integer.valueOf(buyPrice), Integer.valueOf(bidPrice));
        account.addToPurchased(apparel);
    }

    // MODIFIES: Account
    // EFFECTS: creates Apparel from the given JSONObject, and adds to the Account's myListings
    protected void addApparelListing(Account account, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String model = jsonObject.getString("model");
        String colour = jsonObject.getString("colour");
        String buyPrice = jsonObject.getString("buyPrice");
        String bidPrice = jsonObject.getString("bidPrice");
        Apparel apparel = new Apparel(name, model, colour, Integer.valueOf(buyPrice), Integer.valueOf(bidPrice));
        account.addToMyListings(apparel);
    }

    // MODIFIES: Account
    // EFFECTS: creates Apparel from the given JSONObject, and adds to the Account's myBiddings
    protected void addApparelBid(Account account, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String model = jsonObject.getString("model");
        String colour = jsonObject.getString("colour");
        String buyPrice = jsonObject.getString("buyPrice");
        String bidPrice = jsonObject.getString("bidPrice");
        Apparel apparel = new Apparel(name, model, colour, Integer.valueOf(buyPrice), Integer.valueOf(bidPrice));
        account.addToMyBiddings(apparel);
    }




}
