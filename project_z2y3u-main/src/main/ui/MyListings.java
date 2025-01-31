package ui;

import model.Apparel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyListings extends IndividualAccountWindows {

    private JList<Apparel> apparelsListed;

    // EFFECTS : creates a new window where you can view the items you have listed for sale.
    public MyListings() {
        super("My Listings");
        bottom.add(removeButton());
    }

    // EFFECTS : makes a scroll pane that is added to the frame from the JList made.
    @Override
    public JScrollPane scrollPane() {
        JScrollPane scrollPane = new JScrollPane(makeList());
        return scrollPane;
    }

    // REQUIRES : an item is selected.
    // EFFECTS : creates a button when pressed, removes the selected item off the list.
    public JButton removeButton() {
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = apparelsListed.getSelectedIndex();
                if (index != -1) {
                    DefaultListModel<Apparel> model = (DefaultListModel<Apparel>) apparelsListed.getModel();
                    Apparel selected = model.getElementAt(index);
                    thisAccount.removeListing(selected);
                    model.remove(index);
                }
            }
        });
        return removeButton;
    }

    // EFFECTS : makes a new JList instance of the  "myBiddings" list on Account, which allows for the items to be
    //           displayed.
    public JList makeList() {
        ArrayList<Apparel> list = thisAccount.getMyListings();
        DefaultListModel<Apparel> listModel = new DefaultListModel<>();

        for (Apparel a : list) {
            listModel.addElement(a);
        }

        apparelsListed = new JList(listModel);
        apparelsListed.setVisibleRowCount(3);
        //Apparel selectedApparel = (Apparel) apparelsListed.getSelectedValue();
        return apparelsListed;
    }
}
