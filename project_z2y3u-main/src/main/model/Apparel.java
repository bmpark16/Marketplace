package model;

import org.json.JSONObject;
import persistence.Writable;

// individual clothing items (apparels)
public class Apparel implements Writable {

    private String model;
    private String colour;
    private String name;
    private int buyNowPrice;
    private int bidPrice;

    //EFFECTS : constructs a fashion item with model, colour, condition, its buy now price and the current bid.
    public Apparel(String name, String model, String colour, int buyNowPrice, int bidPrice) {
        this.name = name;
        this.model = model;
        this.colour = colour;
        this.buyNowPrice = buyNowPrice;
        this.bidPrice = bidPrice;
    }


    //EFFECTS : returns the brand of the fashion item
    public String getModel() {

        return model;
    }


    //EFFECTS : returns the name of the fashion item
    public String getName() {

        return name;
    }

    //EFFECTS : returns the colour of the fashion item
    public String getColour() {

        return colour;
    }

    //EFFECTS : returns the colour of the fashion item
    public int getBuyPrice() {

        return buyNowPrice;
    }

    //EFFECTS : returns the colour of the fashion item
    public int getBidPrice() {
        return bidPrice;
    }

    //MODIFIES : this
    //EFFECTS : sets the bid price to the given amount
    public void setBid(int bid) {
        this.bidPrice = bid;
    }


    // EFFECTS : turns the given apparel into a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("model", model);
        object.put("colour", colour);
        object.put("buyPrice", Integer.toString(buyNowPrice));
        object.put("bidPrice", Integer.toString(bidPrice));

        return object;
    }

    // EFFECTS : overrides the "toString" method.
    @Override
    public String toString() {
        String stringOne = " " + model + " | " + " " +  colour + " | " + " " + name + " | ";
        String stringTwo = " Buy Now Price: $" + buyNowPrice + " | ";
        String stringThree = " Current Bid: $" + bidPrice;
        return stringOne + stringTwo + stringThree;
    }

}
