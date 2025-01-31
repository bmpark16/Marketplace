package persistence;

import model.Account;
import model.Selling;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// JsonWriter's implementation and tests are inspired by the JsonSerializationDemo provided from the course

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String directory;

    // MODIFIES : this
    // EFFECTS : opens writer; throws FileNotFoundException if destination file cannot be opened.
    public void open(String destination) throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES : this
    // EFFECTS : writes the json representation of Account to file
    public void write(Account account) {
        JSONObject json = account.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES : this
    // EFFECTS : writes the json representation of Sell to file
    public void write(Selling sell) {
        JSONObject json1 = sell.toJson();
        saveToFile(json1.toString(TAB));
    }

    // MODIFIES : this
    // EFFECTS : closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES : this
    // EFFECTS writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }


}
