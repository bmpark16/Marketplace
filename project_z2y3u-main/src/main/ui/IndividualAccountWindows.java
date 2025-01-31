package ui;

import model.Apparel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public abstract class IndividualAccountWindows extends GUI {

    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // EFFECTS : initialization and set up of the account windows.
    public IndividualAccountWindows(String message) {
        frame.setTitle(message);
        frame.setSize(1000,700);
        frame.setLayout(new GridLayout(5,1));
        panel.setLayout(new GridLayout(0,1));

        createPanel();
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // EFFECTS : creates and sets up the panels that are added to the frame.
    private JPanel createPanel() {
        panel.add(scrollPane());
        panel.add(bottomPanel());
        return panel;
    }

    // abstract class of the scroll pane, to be implemented by the individual account windows.
    public abstract JScrollPane scrollPane();

    private JPanel bottomPanel() {
        bottom.add(backToAccount());
        bottom.add(menuButton());
        return bottom;
    }

    // EFFECTS : creates a button when pressed, prompts you back to the account menu.
    private JButton backToAccount() {
        JButton backButton = new JButton("Account Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                new AccountMenu();
            }
        });
        return backButton;
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
}
