package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

public class Account implements Writable {
    private ArrayList<Apparel> wishlist;
    private ArrayList<Apparel> purchased;
    private ArrayList<Apparel> myListings;
    private ArrayList<Apparel> myBiddings;


    //EFFECTS : creates an account with an empty wishlist, empty purchased list and empty myListings
    public Account() {
        wishlist = new ArrayList<>();
        purchased = new ArrayList<>();
        myListings = new ArrayList<>();
        myBiddings = new ArrayList<>();
    }

    // EFFECTS : Returns the wishlist
    public ArrayList getWishlist() {

        return wishlist;
    }

    // EFFECTS : returns the list of purchased items
    public ArrayList getPurchased() {
        return purchased;
    }

    // EFFECTS : returns the items that the user has listed for sale
    public ArrayList getMyListings() {
        return myListings;
    }

    // EFFECTS : returns the list of items the user has placed a bid on
    public ArrayList getMyBiddings() {
        return myBiddings;
    }

    //MODIFIES : this
    //EFFECTS : adds given apparel to wishlist
    public void addToWishlist(Apparel i) {
        wishlist.add(i);
        EventLog.getInstance().logEvent(new Event(i.getModel() + " has been added to wishlist"));
    }

    //MODIFIES : this
    //EFFECTS : adds given apparel to purchased list
    public void addToPurchased(Apparel i) {
        EventLog.getInstance().logEvent(new Event(i.getModel() + " has been added to purchase history"));
        purchased.add(i);
    }

    //MODIFIES : this
    //EFFECTS : adds given apparel to myListings
    public void addToMyListings(Apparel i) {
        EventLog.getInstance().logEvent(new Event(i.getModel() + " has been added to My Listings"));
        myListings.add(i);
    }

    //MODIFIES : this
    //EFFECTS : adds given apparel to myBiddings
    public void addToMyBiddings(Apparel i) {
        EventLog.getInstance().logEvent(new Event(i.getModel() + " has been added to bidding history"));
        myBiddings.add(i);
    }


    // MODIFIES : this
    // EFFECTS : removes the given item off the "myListings" list.
    public void removeListing(Apparel i) {
        EventLog.getInstance().logEvent(new Event(i.getModel() + " has been removed from My Listing"));
        myListings.remove(i);
    }

    // MODIFIES : this
    // EFFECTS : removes the given item off my wishlist
    public void removeFromWishlist(Apparel i) {
        for (Apparel n : wishlist) {
            if (n == i) {
                wishlist.remove(i);
                EventLog.getInstance().logEvent(new Event(i.getModel() + " has been removed from wishlist"));
                return;
            }
        }
    }

    // MODIFIES : this
    // EFFECTS : removes the given item off my purchased history
    public void removeFromPurchased(Apparel i) {
        for (Apparel n : purchased) {
            if (n == i) {
                String r = "purchased history";
                purchased.remove(i);
                EventLog.getInstance().logEvent(new Event(i.getModel() + " has been removed from " + r));
                return;
            }
        }
    }

    // MODIFIES : this
    // EFFECTS : removes the given item off my purchased history
    public void removeFromBiddings(Apparel i) {
        for (Apparel n : myBiddings) {
            if (n == i) {
                String r = "bidding history";
                myBiddings.remove(i);
                EventLog.getInstance().logEvent(new Event(i.getModel() + " has been removed from " + r));
                return;
            }
        }
    }

    // EFFECTS : Turns the Account class into a single JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("wishlist", wishlistToJson());
        json.put("purchased", purchasedToJson());
        json.put("myListing", listingsToJson());
        json.put("myBiddings", biddingsToJson());


        return json;
    }

    // EFFECTS : Converts the account's wishlist into a JSONArray
    public JSONArray wishlistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Apparel a : wishlist) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    // EFFECTS : Converts the account's purchased list into a JSONArray
    public JSONArray purchasedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Apparel a : purchased) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    // EFFECTS : Converts the account's myListings into a JSONArray
    public JSONArray listingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Apparel a : myListings) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    // EFFECTS : Converts the account's myBiddings into a JSONArray
    public JSONArray biddingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Apparel a : myBiddings) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }


}
