package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

import ui.GUI;

public class AccountMenu extends GUI {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    
    //EFFECTS : constructs the account menu, similar to the "My Profile" menu on apps or websites
    public AccountMenu() {
        frame.setTitle("My Account");
        frame.setSize(1000,700);
        frame.setLayout(new GridLayout(5,1));

        createPanel();
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // EFFECTS : sets ups the panels that are used in this window.
    private JPanel createPanel() {
        panel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        panel.setLayout(new GridLayout(0,1));

        panel.add(wishlistButton());
        panel.add(purchasedButton());
        panel.add(myListingsButton());
        panel.add(bidsButton());
        panel.add(closeButton());

        return panel;

    }

    // EFFECTS : creates a button when pressed, creates a new Window where you can view the items you have placed bids
    //           on.
    private JButton bidsButton() {
        JButton bids = new JButton("My Bids");
        bids.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!thisAccount.getMyBiddings().isEmpty()) {
                    new MyBids();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "NOTHING TO SHOW!", "MESSAGE", WIDTH);
                }
            }
        });
        return bids;
    }

    // EFFECTS : creates a button when pressed, creates a new Window where you can view the items you have listed.
    private JButton myListingsButton() {
        JButton listings = new JButton("My Listings");
        listings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!thisAccount.getMyListings().isEmpty()) {
                    new MyListings();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "NOTHING TO SHOW!", "MESSAGE", WIDTH);
                }
            }
        });
        return listings;
    }

    // EFFECTS : creates a button when pressed, creates a new Window where you can view the items you have purchased.
    private JButton purchasedButton() {
        JButton purchased = new JButton("Purchased History");
        purchased.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!thisAccount.getPurchased().isEmpty()) {
                    new MyPurchased();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "NOTHING TO SHOW!", "MESSAGE", WIDTH);
                }
            }
        });
        return purchased;
    }

    // EFFECTS : creates a button when pressed, creates a new Window where you can view the items you have added to
    //           your wishlist.
    private JButton wishlistButton() {
        JButton wishlist = new JButton("My Wishlist");
        wishlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!thisAccount.getWishlist().isEmpty()) {
                    new MyWishlist();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "NOTHING TO SHOW!", "MESSAGE", WIDTH);
                }
            }
        });
        return wishlist;
    }


    // EFFECTS : creates a button when pressed, closes the Account Menu, and prompts you back to the main menu.
    private JButton closeButton() {
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
