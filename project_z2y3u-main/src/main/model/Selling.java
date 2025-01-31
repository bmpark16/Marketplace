package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Selling implements Writable {

    private ArrayList<Apparel> forSale;
    private Apparel jd;
    private Apparel hd;
    private Apparel cdg;
    private Apparel yzy;

    // EFFECTS : constructs the selling class, and sets the initial state of the market
    public Selling() {
        forSale = new ArrayList<Apparel>();
        jd = new Apparel("NIKE", "AJ1 RED TOES", "White", 80, 65);
        hd = new Apparel("STUSSY", "Logo Hoodie", "Black", 75, 20);
        cdg = new Apparel("CDG", "CDG HEART SHIRT","White", 65, 30);
        yzy = new Apparel("ADIDAS", "YEEZY 350 SESAME", "Black", 99, 85);
        forSale.add(jd);
        forSale.add(hd);
        forSale.add(cdg);
        forSale.add(yzy);
    }

    // EFFECTS : returns the forSale list
    public ArrayList<Apparel> getForSale() {
        return forSale;
    }


    // MODIFIES : this
    // EFFECTS : adds given apparel to forSale
    public void addToSell(Apparel a) {
        forSale.add(a);
        EventLog.getInstance().logEvent(new Event(a.getModel() + " has been added to Market"));
    }


    // MODIFIES : this
    // EFFECTS : clears the forSale list, making it an empty ArrayList<Apparel>
    public void clearSell() {
        this.forSale = new ArrayList<Apparel>();
    }


    // EFFECTS : constructs a JSONObject given Sell.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("sell", sellingToJson());

        return json;
    }


    // EFFECTS : turns the Apparels in the forSale list into JSONObjects, and in turn, turns forSale into JSONArrray
    public JSONArray sellingToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Apparel a : forSale) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}
