package gui;

import card.QuizCard;
import repository.CardRepository;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import static utils.Utils.getScreenSize;

public class GUI {
    private final double SCALE = 0.8;
    private JFrame mainFrame = new JFrame("QuizCard");
    private JPanel cardsContainer = new JPanel();
    private JScrollPane cardsContainerScroll = new JScrollPane(cardsContainer);
    private CardRepository repository;
    private Dimension programSize = getScreenSize(SCALE);

    public GUI (CardRepository repository) {
        this.repository = repository;

        cardsContainerScroll.setBorder(null);
        cardsContainerScroll.getViewport().setPreferredSize(getScrollSize());
        cardsContainerScroll.getVerticalScrollBar().setUnitIncrement(16);

        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        mainFrame.add(cardsContainerScroll);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(programSize);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.getContentPane().setBackground(Color.WHITE);
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

    private Dimension getScrollSize() {
        int height = (int) programSize.getHeight();
        int width = (int) programSize.getWidth();
        return new Dimension(width / 2, height - 100);
    }
}
