package ui;


import java.util.Scanner;
import java.util.ArrayList;

import model.Account;
import model.Selling;
import model.Apparel;
import persistence.JsonSellReader;
import persistence.JsonWriter;
import persistence.JsonAccountReader;


import java.io.FileNotFoundException;
import java.io.IOException;


// Fashion Store Application
public class FashionStore {
    private static final String FASHION_STORE = "./data/fashionstore.json";
    private static final String SELLING = "./data/selling.json";
    private Account newAccount;
    private Scanner input;
    private Selling selling;
    private JsonWriter jsonWriter;
    private JsonAccountReader jsonAccountReader;
    private JsonSellReader jsonSellReader;


    // EFFECTS : creates and runs a new instance of the fashion store
    public FashionStore() throws FileNotFoundException {
        jsonWriter = new JsonWriter();
        jsonAccountReader = new JsonAccountReader();
        jsonSellReader = new JsonSellReader();
        runStore();
    }

    // MODIFIES : this
    // EFFECTS : takes care of the initial user input
    private void runStore() {
        boolean quit = false;
        input = new Scanner(System.in);
        String command = null;
        setup();

        while (!quit) {
            mainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                quit = true;
            } else {
                processInput(command);
            }
        }
        System.out.println("Thanks for visiting! Come back soon!");
    }

    // MODIFIES : this
    // EFFECTS : sets up the app, creates a new account and creates listings with apparels for sale
    public void setup() {
        newAccount = new Account();
        selling = new Selling();
    }

    // EFFECTS : displays the screen of the main menu
    public void mainMenu() {
        System.out.println("\nWelcome! What do you wish to do?");
        System.out.println("\tb ==> BUY");
        System.out.println("\tm ==> MY ACCOUNT");
        System.out.println("\tl ==> LIST ITEM");
        System.out.println("\tsave ==> SAVE ACCOUNT INFO");
        System.out.println("\tload ==> LOAD SAVED ACCOUNT");
        System.out.println("\tq ==> QUIT");
    }

    // EFFECTS : processes the input in the main menu
    public void processInput(String c) {
        c.toLowerCase();
        if (c.equals("b")) {
            showSale();
        } else if (c.equals("m")) {
            accountMenu();
        } else if (c.equals("l")) {
            sellMenu();
        } else if (c.equals("save")) {
            saveApp();
        } else if (c.equals("load")) {
            load();
        } else {
            System.out.println("INVALID INPUT. TRY AGAIN");
        }
    }

    // EFFECTS : show the sales tab, display the list of names of items up for sale
    //           also processes the user's console input at this stage of the app.
    public void showSale() {
        System.out.println("SHOWING APPAREL FOR SALE ");
        System.out.println(displayNames(selling.getForSale()));
        System.out.println("\t s ==> SELECT ITEM TO VIEW");
        System.out.println("\t m ==> BACK TO MAIN MENU");
        String command = input.next();
        if (command.equals("m")) {
            mainMenu();
        } else if (command.equals("s")) {
            System.out.println("ENTER ITEM NAME");
            command = input.next();
            showItem(command, selling.getForSale());
        } else {
            System.out.println("INVALID INPUT. TRY AGAIN");
            showSale();
        }
    }

    // EFFECTS : saves the current account information to file
    private void saveAccount() {
        try {
            jsonWriter.open(FASHION_STORE);
            jsonWriter.write(newAccount);
            jsonWriter.close();
            System.out.println("Saved Account to" + FASHION_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FASHION_STORE);
        }
    }

    // EFFECTS : saves the current market information to file
    private void saveSelling() {
        try {
            jsonWriter.open(SELLING);
            jsonWriter.write(selling);
            jsonWriter.close();
            System.out.println("Saved Account to" + SELLING);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FASHION_STORE);
        }

    }

    //EFFECTS : Saves the entire app
    private void saveApp() {
        saveAccount();
        saveSelling();
    }

    // MODIFIES : this
    // EFFECTS : loads Sell (setting the market) that has been saved from file
    private void loadApp() {
        try {
            selling = jsonSellReader.read(SELLING);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonSellReader);
        }
    }

    // MODIFIES : this
    // EFFECTS : loads the account that has been saved from file
    private void loadAccount() {
        try {
            newAccount = jsonAccountReader.read(FASHION_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + FASHION_STORE);
        }
    }

    public void load() {
        loadApp();
        loadAccount();
    }

    // EFFECTS : display the accounts menu. Gives the user the options to view their wishlist, purchase history,
    //           items they have listed, items they have bid on, and an option to return back to the main menu
    public void accountMenu() {
        System.out.println("MY ACCOUNT");
        System.out.println("\tWHAT WOULD YOU LIKE TO DO?");
        System.out.println("\t w ==> VIEW MY WISHLIST");
        System.out.println("\t h ==> VIEW PURCHASE HISTORY");
        System.out.println("\t l ==> VIEW MY LISTINGS");
        System.out.println("\t b ==> VIEW MY CURRENT BIDDINGS");
        System.out.println("\t m ==> BACK TO MAIN MENU");
        String command = input.next();
        if (command.equals("m")) {
            mainMenu();
        } else if (command.equals("w")) {
            wishlistMenu();
        } else if (command.equals("l")) {
            myListingsMenu();
        } else if (command.equals("b")) {
            myBiddingsMenu();
        } else if (command.equals("h")) {
            purchasedMenu();
        } else {
            System.out.println("INVALID INPUT. TRY AGAIN");
            accountMenu();
        }
    }

    // EFFECTS : display the user's wishlist, if wishlist is empty, sends the user back to the main menu
    public void wishlistMenu() {
        if (newAccount.getWishlist().isEmpty()) {
            System.out.println("WISHLIST IS EMPTY! SENDING YOU BACK TO MENU");
        } else {
            System.out.println("======MY WISHLIST======");
            System.out.println(displayNames(newAccount.getWishlist()));
        }
    }

    // EFFECTS : display the list of items the user have listed for sale. If empty, sends the user back to main menu
    public void myListingsMenu() {
        if (newAccount.getMyListings().isEmpty()) {
            System.out.println("YOU HAVE LISTED NO ITEMS. SENDING YOU BACK TO MENU");
        } else {
            System.out.println("======MY LISTINGS======");
            System.out.println(displayNames(newAccount.getMyListings()));
        }
    }

    // EFFECTS : display the list of items the user have bid on. If empty, sends the user back to main menu
    public void myBiddingsMenu() {
        if (newAccount.getMyBiddings().isEmpty()) {
            System.out.println("YOU HAVE PLACED NO BIDS. SENDING YOU BACK TO MENU");
        } else {
            System.out.println("======MY BIDDINGS======");
            System.out.println(displayNames(newAccount.getMyBiddings()));
        }
    }

    // EFFECTS : display the list of items the user have purchased. If empty, sends the user back to main menu
    public void purchasedMenu() {
        if (newAccount.getPurchased().isEmpty()) {
            System.out.println("YOU ARE YET TO PURCHASE AN ITEM. SENDING YOU BACK TO MENU");
        } else {
            System.out.println("======PURCHASED ITEMS======");
            System.out.println(displayNames(newAccount.getPurchased()));
        }
    }

    // EFFECTS : given a user's input and a list of apparels, return the apparel that has the same name as the user's
    //           input. If such apparel is not included in the given list, return null.
    public Apparel findApparelByModel(String name, ArrayList<Apparel> l) {
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getName().equals(name)) {
                return l.get(i);
            }
        }
        return null;
    }

    //EFFECTS : given the user's input and a list of apparels, show information of the item that the user has selected.
    //          If the user has input an item that is not part of the given list, return a statement saying that it is
    //          not a valid item
    public void showItem(String c, ArrayList<Apparel> l) {
        Apparel item = findApparelByModel(c, l);
        if (item == null) {
            System.out.println("not a valid item");
            String command = input.next();
        } else {
            getInfo(item);
            System.out.println("press w to add to wishlist, b to bid or m to go back to main menu");
            String command = input.next();
            handleInputBuy(command, item);
        }
    }

    // Method to display information about the apparel item
    public void getInfo(Apparel i) {
        System.out.println("Model: " + i.getModel());
        System.out.println("Colour: " + i.getColour());
        System.out.println("Buy Now Price: " + i.getBuyPrice());
        System.out.println("Bid Price: " + i.getBidPrice());
    }

    // EFFECTS : handle the user input at the buy menu. Sends user back to main menu, allows user to add item to
    //           their wishlist, allows user to select bid, and if a user's input is invalid, prints out such statement
    public void handleInputBuy(String c, Apparel item) {
        if (c.equals("m")) {
            System.out.println("RETURNING TO MENU");
        } else if (c.equals("w")) {
            newAccount.addToWishlist(item);
            System.out.println("=====ADDED TO WISHLIST====");
        } else if (c.equals("b")) {
            System.out.println("STARTING BID PRICE IS: " + item.getBidPrice());
            System.out.println("PLACE BID: ");
            int bidding = input.nextInt();
            processBid(bidding, item);
        } else {
            System.out.println("INPUT NOT VALID");
            String command = input.next();
            handleInputBuy(command, item);
        }
    }

    // EFFECTS : processes the user's bidding. If bid matches or exceeds the buy now price, the user has purchased the
    //           item (takes the item off the forSale list and adds item to the user's purchased list). If bid is
    //           higher or equal to bid price but less than buy price, sets the item's bid price to the user's bidding
    //           and adds item to the user's bidding list. Else, prints statement and shows item again to the user.
    public void processBid(int b, Apparel i) {
        if (b >= i.getBuyPrice()) {
            System.out.println("BID MATCHES BUY PRICE.... PURCHASING ITEM...");
            selling.getForSale().remove(i);
            newAccount.addToPurchased(i);
            System.out.println("ITEM BOUGHT! RETURNING TO MAIN MENU");
        } else if (i.getBuyPrice() > b && b >= i.getBidPrice()) {
            System.out.println("PLACING BID... ");
            i.setBid(b);
            newAccount.addToMyBiddings(i);
            System.out.println("BID PLACED! RETURNING TO MAIN MENU");
        } else {
            System.out.println("A HIGHER BID ALREADY EXISTS...");
            showItem(i.getName(), selling.getForSale());
        }
    }

    // EFFECTS : displays just the names of the items that are on the given list
    public ArrayList<String> displayNames(ArrayList<Apparel> k) {
        ArrayList<String> names = new ArrayList<String>();
        for (Apparel i : k) {
            names.add(i.getName());
        }
        return names;
    }

    // EFFECTS : Allows user the list an item. Takes in the name, model, colour, and the prices and creates a new
    //           Apparel. Adds the item to my listing, and to the forSale list.
    public void sellMenu() {
        System.out.println("What is the name of your item?");
        String name = input.next();
        System.out.println("What is the model of your item?");
        String model = input.next();
        System.out.println("What is the colour of your item?");
        String colour = input.next();
        System.out.println("How much would you like to sell it for?");
        int buyNow = input.nextInt();
        System.out.println("How much is the starting bid price?");
        int bidPrice = input.nextInt();
        System.out.println("===========PROCESSING========");
        Apparel myItem = new Apparel(name, model,colour,buyNow,bidPrice);
        selling.getForSale().add(myItem);
        newAccount.addToMyListings(myItem);
        System.out.println("======LISTING COMPLETE!=====");
        System.out.println("press a to list another item or m to go back to menu");
        String command = input.next();
        if (command.equals("m")) {
            //stub
        } else {
            sellMenu();
        }
    }


}
