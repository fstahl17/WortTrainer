package fstahl.view;

import javax.swing.*;

public class WortFrame extends JFrame {
    public WortFrame(WortPanel panel) {
        super("Wort Trainer");
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 400, 300);
        this.setVisible(true);
    }
}
