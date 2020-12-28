import gui.GUI;
import repository.CardRepository;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        CardRepository cardRepository = new CardRepository();
        GUI gui = new GUI(cardRepository);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.start();
            }
        });
    }
}
