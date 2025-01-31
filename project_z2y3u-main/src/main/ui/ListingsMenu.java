package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import model.Account;
import model.Selling;
import model.Apparel;

public class ListingsMenu extends GUI  {
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
    private JList apparelsListed;
    private JTextField bid = new JTextField("Place Your Bid");


    // EFFECTS : creates a window where you can view items for sale.
    public ListingsMenu() {
        frame.setTitle("ITEMS FOR SALE");
        frame.setSize(1000,700);
        frame.setLayout(new GridLayout(5,1));
        panel.setLayout(new GridLayout(0,1));

        createPanel();
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // EFFECTS : sets ups the panels that are used in this window.
    private JPanel createPanel() {
        panel.add(scrollPane());
        panel.add(bottomPanel());
        return panel;
    }

    // EFFECTS : Creates the panels, where all the buttons and the bidding text field is located.
    private JPanel bottomPanel() {
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.add(addButton());
        bottom.add(menuButton());
        bottom.add(bid);
        bottom.add(placeBid());
        return bottom;
    }


    // REQUIRES : an item has been selected in the window.
    // EFFECTS : creates a button when pressed, places the bid on the selected item.
    private JButton placeBid() {
        JButton bidButton = new JButton("Place Bid");
        bidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = apparelsListed.getSelectedIndex();
                if (index != -1) {
                    DefaultListModel<Apparel> model = (DefaultListModel<Apparel>) apparelsListed.getModel();
                    Apparel selected = model.getElementAt(index);
                    int bidAmount = Integer.parseInt(bid.getText());
                    processBid(bidAmount, selected, index);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Please Select an Item ", "MESSAGE", WIDTH);
                }
            }
        });
        return bidButton;
    }

    // EFFECTS : takes in an integer and an apparel, and processed the bid depending on the bid amount.
    //           If higher or equal to buy now, automatically purchases the item and adds item to the "Purchased" list.
    //           If between buy now and bid price, places the bid and adds item to "My Biddings". If lower than the
    //           current bid price, doesn't process the bid.
    private void processBid(int b, Apparel i, int modelIndex) {
        DefaultListModel<Apparel> model = (DefaultListModel<Apparel>) apparelsListed.getModel();
        if (b >= i.getBuyPrice()) {
            thisSelling.getForSale().remove(i);
            model.remove(modelIndex);
            thisAccount.addToPurchased(i);
            JOptionPane.showMessageDialog(rootPane, "ITEM BOUGHT!", "MESSAGE", WIDTH);
        } else if (i.getBuyPrice() > b && b >= i.getBidPrice()) {
            i.setBid(b);
            thisAccount.addToMyBiddings(i);
            JOptionPane.showMessageDialog(rootPane, "BID PLACED!", "MESSAGE", WIDTH);
        } else {
            JOptionPane.showMessageDialog(rootPane, "HIGHER BID EXISTS!", "MESSAGE", WIDTH);
        }
    }

    // REQUIRES : an item is selected
    // EFFECTS : creates a button when pressed, adds the selected item to my wishlist
    private JButton addButton() {
        JButton addToWishlist = new JButton("Add To Wishlist");
        addToWishlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = apparelsListed.getSelectedIndex();
                if (index != -1) {
                    DefaultListModel<Apparel> model = (DefaultListModel<Apparel>) apparelsListed.getModel();
                    Apparel selected = model.getElementAt(index);
                    if (!thisAccount.getWishlist().contains(selected)) {
                        thisAccount.addToWishlist(selected);
                        JOptionPane.showMessageDialog(rootPane, "ADDED TO WISHLIST!", "MESSAGE", WIDTH);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "ALREADY IN WISHLIST", "MESSAGE", WIDTH);
                    }
                }
            }
        });
        return addToWishlist;
    }

    // EFFECTS : creates a button when pressed, prompts you back to the main menu.
    private JButton menuButton() {
        JButton mainMenu = new JButton("Main Menu");
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        return mainMenu;
    }

    // EFFECTS : makes a new JList instance of the "ForSale" list on Selling, which allows for the items to be
    //           displayed.
    private JList makeList() {
        ArrayList<Apparel> list = thisSelling.getForSale();
        DefaultListModel<Apparel> listModel = new DefaultListModel<>();

        for (Apparel a : list) {
            listModel.addElement(a);
        }

        apparelsListed = new JList(listModel);
        apparelsListed.setVisibleRowCount(3);
        return apparelsListed;
    }

    // EFFECTS : makes a scroll pane that is added to the frame from the JList made.
    private JScrollPane scrollPane() {
        JScrollPane scrollPane = new JScrollPane(makeList());
        return scrollPane;
    }





}
