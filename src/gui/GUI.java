package gui;

import card.QuizCard;
import repository.CardRepository;
import utils.Utils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static utils.Utils.getScreenSize;

public class GUI {

    private JMenuBar menuBar;
    private JFrame mainFrame = new JFrame("QuizCard");
    private JFrame createFrame = CreateFrameFactory.getCreateFrame();
    private JPanel cardsContainer = new JPanel();
    private JScrollPane cardsContainerScroll = new JScrollPane(cardsContainer);
    private CardRepository repository;
    private Dimension programSize = getScreenSize();

    private LayoutManager mainGridLayout = new GridLayout(1, 2);

    public GUI (CardRepository repository) {
        this.repository = repository;

        cardsContainerScroll.setBorder(null);
        cardsContainerScroll.getViewport().setPreferredSize(Utils.getScrollSize());
        cardsContainerScroll.getVerticalScrollBar().setUnitIncrement(16);

        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        mainFrame.getContentPane().add(cardsContainerScroll);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(programSize);
        mainFrame.setLayout(mainGridLayout);
        mainFrame.getContentPane().setBackground(Color.WHITE);

        createMenuBar();
    }

    public void start() {
        addCardsToPanel();

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void setCreateFrameVisible() {
        createFrame.setVisible(true);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Create");

        menu.setFont(new Font(null, Font.PLAIN, 24));
        JMenuItem createQCMenuItem = new JMenuItem("Create QuizCard");
        createQCMenuItem.setFont(new Font(null, Font.PLAIN, 24));
        createQCMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCreateFrameVisible();
            }
        });
        menu.add(createQCMenuItem);

        menuBar.add(menu);
        mainFrame.setJMenuBar(menuBar);
    }

    private void addCardsToPanel() {
        List<QuizCard> quizCardList = repository.getQuizCards();
        for (QuizCard quizCard : quizCardList) {
            cardsContainer.add(QuizCardGUI.getQuizCardGui(quizCard));
        }
    }
}
