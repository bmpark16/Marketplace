package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.Event;
import model.EventLog;
import model.Account;
import model.Selling;
import persistence.JsonAccountReader;
import persistence.JsonSellReader;
import persistence.JsonWriter;


public class GUI extends JFrame {

    private static final String FASHION_STORE = "./data/fashionstore.json";
    private static final String SELLING = "./data/selling.json";

    GridBagConstraints constraint = new GridBagConstraints();
    private JsonWriter jsonWriter = new JsonWriter();
    private JsonAccountReader jsonAccountReader = new JsonAccountReader();
    private JsonSellReader jsonSellReader = new JsonSellReader();
    protected static Account thisAccount = new Account();
    protected static Selling thisSelling = new Selling();
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();


    // EFFECTS : Creates the main menu that appears when you first run Main.
    public GUI() {
        frame.getContentPane().add(createPanel());
        frame.setSize(1000,700);
        //frame.setLayout(new GridLayout(7,1));
        frame.setLayout(new GridBagLayout());
        frame.setTitle("Fashion Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
            }
        });
    }

    private void printLog(EventLog e) {
        for (Event event : e) {
            System.out.println(event.toString());
        }
    }

    // MODIFIES : this
    // EFFECTS : creates and sets up the panels that are added to the frame.
    private JPanel createPanel() {
        panel.setPreferredSize(new Dimension(800,600));
        panel.setLayout(new GridBagLayout());
        addImageToLabel(new JLabel());
        constraint.gridheight = 1;
        constraint.gridy = 2;
        panel.add(listingsButton(), constraint);
        constraint.gridy = 3;
        panel.add(accountMenuButton(), constraint);
        constraint.gridy = 4;
        panel.add(listItemButton(), constraint);
        constraint.gridy = 5;
        panel.add(saveButton(), constraint);
        constraint.gridy = 6;
        panel.add(loadButton(), constraint);
        constraint.gridy = 7;
        panel.add(quitButton(), constraint);
        return panel;
    }

    // EFFECTS : loads in a saved image and adds it to the panel to be displayed on the top of the screen.
    private void addImageToLabel(JLabel j) {
        constraint.gridheight = 2;
        constraint.gridy = 0;
        j.setIcon(new ImageIcon("./data/logo.png"));
        j.setPreferredSize(new Dimension(800,250));
        panel.add(j, constraint);
    }

    // EFFECTS : creates a button when pressed, creates a new Window where you can view the items that are listed for
    //           sale
    private JButton listingsButton() {
        JButton listingsButton = new JButton("View Listings");
        listingsButton.setPreferredSize(new Dimension(800,50));
        listingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListingsMenu();
            }
        });
        return listingsButton;
    }

    // EFFECTS : creates a button when pressed, creates a new "Account" window where you can then view your lists.
    private JButton accountMenuButton() {
        JButton accountMenuButton = new JButton("My Account");
        accountMenuButton.setPreferredSize(new Dimension(800,50));
        accountMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountMenu();
            }
        });
        return accountMenuButton;
    }

    // EFFECTS : creates a button when pressed, creates a new Window where you can list items of your own.
    private JButton listItemButton() {
        JButton listItemButton = new JButton("List Items");
        listItemButton.setPreferredSize(new Dimension(800,50));
        listItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SellMenu();
            }
        });
        return listItemButton;
    }

    // MODIFIES : this
    // EFFECTS : creates a button when pressed, saves the state of the app.
    private JButton saveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(800,50));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAccount();
                saveSelling();
                JOptionPane.showMessageDialog(rootPane, "Data Saved!", "Saved", WIDTH);
            }
        });
        return saveButton;
    }

    // EFFECTS : runs JsonWriter and saves the current state of the Account class.
    private void saveAccount() {
        try {
            jsonWriter.open(FASHION_STORE);
            jsonWriter.write(thisAccount);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FASHION_STORE);
        }
    }

    // EFFECTS : saves the current market information to file
    private void saveSelling() {
        try {
            jsonWriter.open(SELLING);
            jsonWriter.write(thisSelling);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + FASHION_STORE);
        }

    }

    // EFFECTS : creates a button when pressed, loads the previously state of the app.
    private JButton loadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.setPreferredSize(new Dimension(800,50));
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAccount();
                loadSelling();
                JOptionPane.showMessageDialog(rootPane, "Data Loaded!", "Loaded", WIDTH);
            }
        });
        return loadButton;
    }

    // EFFECTS : creates the JsonAccountReader and reads the pre-saved Account information
    private void loadAccount() {
        try {
            thisAccount = jsonAccountReader.read(FASHION_STORE);
        } catch (IOException exception) {
            System.out.println("Unable to load Account from file: " + FASHION_STORE);
        }
    }

    // EFFECTS : creates the JsonSellReader and reads the pre-saved Selling class information
    private void loadSelling() {
        try {
            thisSelling = jsonSellReader.read(SELLING);
        } catch (IOException exception) {
            System.out.println("Unable to load Selling from file: " + SELLING);
        }
    }


    // EFFECTS : creates a button when pressed, quits the app.
    private JButton quitButton() {
        JButton quitButton = new JButton("QUIT");
        quitButton.setPreferredSize(new Dimension(800,50));
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        return quitButton;
    }

}
