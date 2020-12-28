package gui;

import card.QuizCard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class QuizCardGUI {

    private static Font cardTitleFont = new Font(null, Font.BOLD, 28);
    private static Font cardDescriptionFont = new Font(null, Font.PLAIN, 24);

    public static JPanel getQuizCardGui(QuizCard quizCard) {
        JPanel borderPanel = new JPanel();
        JPanel quizCardGui = new JPanel();

        borderPanel.add(quizCardGui);
        quizCardGui.setLayout(new BoxLayout(quizCardGui, BoxLayout.Y_AXIS));
        quizCardGui.setPreferredSize(new Dimension(500, 200));

        Border cardItemPaddingBorder = BorderFactory.createEmptyBorder(20, 10, 20, 10);
        Border cardItemMarginBorder = BorderFactory.createEmptyBorder(30, 50, 30,   50);
        Border cardItemLineBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        Border cardItemCompoundBorder = BorderFactory.createCompoundBorder(cardItemLineBorder, cardItemPaddingBorder);
        cardItemCompoundBorder = BorderFactory.createCompoundBorder(cardItemMarginBorder, cardItemCompoundBorder);
        borderPanel.setBorder(cardItemCompoundBorder);

        JLabel cardTitle = new JLabel(quizCard.getTitle());
        cardTitle.setFont(cardTitleFont);

        quizCardGui.add(cardTitle);

        String description = quizCard.getDescription();
        JLabel cardDescription = new JLabel();
        cardDescription.setFont(cardDescriptionFont);
        cardDescription.setForeground(Color.GRAY);
        if (description != null) {
            cardDescription.setText(description);
        } else {
            cardDescription.setText("This card has no description. :p");
        }
        quizCardGui.add(cardDescription);

        return borderPanel;
    }
}
