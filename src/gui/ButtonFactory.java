package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonFactory {

    private static Font controlBtnFont = new Font(null, Font.PLAIN, 26);

    public static JButton getControlButton(String text, ActionListener actionListener) {
        JButton jBtn = new JButton(text);
        jBtn.addActionListener(actionListener);
        jBtn.setFont(controlBtnFont);
        return jBtn;
    }
}
