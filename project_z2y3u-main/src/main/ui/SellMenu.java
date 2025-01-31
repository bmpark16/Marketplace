package ui;

import model.Apparel;
import model.Selling;
import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class SellMenu extends GUI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JTextField name;
    JTextField model;
    JTextField colour;
    JTextField buyNow;
    JTextField bidPrice;

    // EFFECTS : creates a new window where you can list items up for sale.
    public SellMenu() {
        frame.setTitle("List Your Item");
        frame.setSize(1000,700);
        frame.setLayout(new GridLayout(5,1));
        name = new JTextField("Brand of Item", 1);
        model = new JTextField("Model Of Item", 1);
        colour = new JTextField("Colour of Item", 1);
        buyNow = new JTextField("Buy Now Price (Enter Number)",1);
        bidPrice = new JTextField("Initial Bid Price (Enter Number)", 1);

        createPanels();
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // EFFECTS : sets ups the panels that are used in this window.
    private JPanel createPanels() {
        panel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        panel.setLayout(new GridLayout(0,1));
        panel.add(name);
        panel.add(model);
        panel.add(colour);
        panel.add(buyNow);
        panel.add(bidPrice);
        panel.add(createBottomPanel());
        return panel;
    }

    // EFFECTS : Creates the panels, where all the buttons and the bidding text field is located.
    private JPanel createBottomPanel() {
        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(listButton());
        bottom.add(mainMenuButton());

        return bottom;
    }

    // REQUIRES : the textfield is filled in with the correct type.
    // EFFECTS : creates a button when pressed, constructs a new Apparel with the information put on the textfield and
    //           lists the item. Adds to the "forSale" list on Selling and "myListings" list on Account.
    private JButton listButton() {
        JButton listButton = new JButton("List My Item");
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = name.getText();
                String m = model.getText();
                String c = colour.getText();
                int p = Integer.parseInt(buyNow.getText());
                int b = Integer.parseInt(bidPrice.getText());

                Apparel item =  new Apparel(n,m,c,p,b);
                thisSelling.addToSell(item);
                thisAccount.addToMyListings(item);

                JOptionPane.showMessageDialog(rootPane, "LISTED SUCCESSFULLY", "CONFIRMATION", WIDTH);
                clearFields();
            }
        });
        return listButton;
    }

    // EFFECTS : clears the textfield and returns it back to the original state.
    private void clearFields() {
        name.setText("Name of Item");
        model.setText("Model of Item");
        colour.setText("Colour of Item");
        buyNow.setText("Buy Now Price");
        bidPrice.setText("Initial Bid Price");
    }

    // EFFECTS : creates a button when pressed, prompts you back to the main menu.
    private JButton mainMenuButton() {

        JButton close = new JButton("Main Menu");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        return close;

    }


}
