import gui.GUI;
import repository.CardRepository;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        GUI gui = GUI.getInstance();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.start();
            }
        });
    }
}
