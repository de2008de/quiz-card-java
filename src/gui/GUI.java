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

    private LayoutManager flowLayoutCenter = new FlowLayout(FlowLayout.CENTER);
    private LayoutManager flowLayoutLeft = new FlowLayout(FlowLayout.LEFT);

    private LayoutManager mainGridLayout = new GridLayout(1, 2);
    private LayoutManager createGridLayout = new GridLayout(1, 2);

    public GUI (CardRepository repository) {
        this.repository = repository;

        cardsContainerScroll.setBorder(null);
        cardsContainerScroll.getViewport().setPreferredSize(getScrollSize());
        cardsContainerScroll.getVerticalScrollBar().setUnitIncrement(16);

        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        mainFrame.add(cardsContainerScroll);


        // Create panel for creating new cards
        JPanel createPanel = new JPanel();
        JScrollPane createScrollPane = new JScrollPane(createPanel);
        createScrollPane.setBorder(null);
        createScrollPane.getViewport().setPreferredSize(getScrollSize());
        createScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        createPanel.setLayout(createGridLayout);
        createPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
        createPanel.setBackground(new Color(250, 250, 250));

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(flowLayoutLeft);
        textFieldPanel.setOpaque(false);
        textFieldPanel.setPreferredSize(getScrollSize());
        textFieldPanel.add(TextFieldFactory.getTextField("Title:", 20));
        textFieldPanel.add(TextFieldFactory.getTextField("Description:", 20));
        
        createPanel.add(textFieldPanel);
        mainFrame.add(createScrollPane);


        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(programSize);
        mainFrame.setLayout(mainGridLayout);
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
        return new Dimension(width / 3, height - 100);
    }
}
