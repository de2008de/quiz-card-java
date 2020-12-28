package gui;

import card.QuizCard;
import repository.CardRepository;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.List;

import static utils.Utils.getScreenSize;

public class GUI {
    private final double SCALE = 0.8;
    private JFrame mainFrame = new JFrame("QuizCard");
    private JPanel cardsContainer = new JPanel();
    private JScrollPane cardsContainerScroll = new JScrollPane(cardsContainer);
    private CardRepository repository;

    public GUI (CardRepository repository) {
        this.repository = repository;

        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        mainFrame.add(cardsContainerScroll);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(getScreenSize(SCALE));
        mainFrame.setLayout(new FlowLayout());
    }

    public void start() {
        addCardsToPanel();

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void addCardsToPanel() {
        List<QuizCard> quizCardList = repository.getQuizCards();
        for (QuizCard quizCard : quizCardList) {
            cardsContainer.add(QuizCardGUI.getQuizCardGui(quizCard));
        }
    }
}
