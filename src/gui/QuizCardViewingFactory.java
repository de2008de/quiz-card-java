package gui;

import card.QuizCard;

import javax.swing.*;

public class QuizCardViewingFactory {

    public static JPanel getQuizCardViewing(QuizCard quizCard) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(quizCard.getTitle()));
        return panel;
    }

}
