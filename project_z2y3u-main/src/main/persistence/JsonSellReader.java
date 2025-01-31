package persistence;

import model.Apparel;
import model.Selling;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// JsonSellReader's implementation and the quotes are inspired by the JsonSerializationDemo provided from the course

public class JsonSellReader {

    // EFFECTS : constructs reader to read from source file
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads Sell from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Selling read(String source) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSell(jsonObject);
    }

    // EFFECTS: parses Sell from JSON object and returns it
    public Selling parseSell(JSONObject jsonObject) {
        Selling sell = new Selling();
        sell.clearSell();
        addApparels(sell, jsonObject);
        return sell;
    }

    // MODIFIES : Sell
    // EFFECTS : From a given JSONObject, turns the object in JSONArray back as Apparel and adds to Sell
    public void addApparels(Selling sell, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("sell");
        for (Object json : jsonArray) {
            JSONObject nextApparel = (JSONObject) json;
            addApparel(sell, nextApparel);
        }
    }

    // MODIFIES : Sell
    // EFFECTS : Constructs an Apparel from the given jsonObject and adds to given Sell.
    public void addApparel(Selling sell, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String model = jsonObject.getString("model");
        String colour = jsonObject.getString("colour");
        String buyPrice = jsonObject.getString("buyPrice");
        String bidPrice = jsonObject.getString("bidPrice");
        Apparel a = new Apparel(name, model, colour, Integer.valueOf(buyPrice), Integer.valueOf(bidPrice));
        sell.addToSell(a);
    }


}
