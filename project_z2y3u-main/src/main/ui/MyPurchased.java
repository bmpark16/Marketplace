package ui;

import model.Apparel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPurchased extends IndividualAccountWindows {

    private JList<Apparel> apparelsPurchased;

    // EFFECTS : creates a new window where you can view items that you have bought.
    public MyPurchased() {
        super("Purchase History");
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
    private JButton removeButton() {
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = apparelsPurchased.getSelectedIndex();
                if (index != -1) {
                    DefaultListModel<Apparel> model = (DefaultListModel<Apparel>) apparelsPurchased.getModel();
                    Apparel selected = model.getElementAt(index);
                    thisAccount.removeFromPurchased(selected);
                    model.remove(index);
                }
            }
        });
        return removeButton;
    }

    // EFFECTS : makes a new JList instance of the "purchased" list on Account, which allows for the items to be
    //           displayed.
    public JList makeList() {
        ArrayList<Apparel> list = thisAccount.getPurchased();
        DefaultListModel<Apparel> listModel = new DefaultListModel<>();

        for (Apparel a : list) {
            listModel.addElement(a);
        }

        apparelsPurchased = new JList(listModel);
        apparelsPurchased.setVisibleRowCount(3);
        return apparelsPurchased;
    }
}
