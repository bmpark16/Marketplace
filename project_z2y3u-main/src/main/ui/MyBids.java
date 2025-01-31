package ui;

import model.Apparel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyBids extends IndividualAccountWindows {

    private JList<Apparel> apparelsBidOn;

    // EFFECTS : creates a new window where you can view items you have placed a bid on.
    public MyBids() {
        super("Items Bid On");
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
                int index = apparelsBidOn.getSelectedIndex();
                if (index != -1) {
                    DefaultListModel<Apparel> model = (DefaultListModel<Apparel>) apparelsBidOn.getModel();
                    Apparel selected = model.getElementAt(index);
                    thisAccount.removeFromBiddings(selected);
                    model.remove(index);
                }
            }
        });
        return removeButton;
    }

    // EFFECTS : makes a new JList instance of the  "myBiddings" list on Account, which allows for the items to be
    //           displayed.
    public JList makeList() {
        ArrayList<Apparel> list = thisAccount.getMyBiddings();
        DefaultListModel<Apparel> listModel = new DefaultListModel<>();

        for (Apparel a : list) {
            listModel.addElement(a);
        }

        apparelsBidOn = new JList(listModel);
        apparelsBidOn.setVisibleRowCount(3);
        return apparelsBidOn;
    }
}
