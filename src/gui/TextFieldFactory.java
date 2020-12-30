package gui;

import javax.swing.*;
import java.awt.*;

public class TextFieldFactory {

    private static LayoutManager flowLayoutLeft = new FlowLayout(FlowLayout.LEFT);

    private static Font labelFont = new Font(null, Font.PLAIN, 28);

    public static JPanel getTextField(String labelText, int column) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField(column);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(flowLayoutLeft);
        labelPanel.add(label);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(labelPanel);
        panel.add(textField);

        label.setFont(labelFont);
        textField.setFont(labelFont);

        panel.setOpaque(false);
        labelPanel.setOpaque(false);

        return panel;
    }

    public static JPanel getTextArea(String labelText, int row, int column) {
        JPanel textAreaPanel = new JPanel();
        JLabel label = new JLabel(labelText);
        JTextArea textArea = new JTextArea(row, column);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(flowLayoutLeft);
        labelPanel.add(label);

        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));
        textAreaPanel.add(labelPanel);
        textAreaPanel.add(textArea);

        label.setFont(labelFont);
        textArea.setFont(labelFont);

        textAreaPanel.setOpaque(false);
        labelPanel.setOpaque(false);

        return textAreaPanel;
    }
}
