package gui;

import card.QuizCard;
import repository.CardRepository;
import utils.Utils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

import static utils.Utils.getScreenSize;

public class GUI {

    private static GUI gui;

    private JMenuBar menuBar;
    private JFrame mainFrame = new JFrame("QuizCard");
    private JPanel cardsContainer = new JPanel();
    private JPanel viewingPanel = new JPanel();
    private JFrame createFrame = CreateFrameFactory.getCreateFrame(cardsContainer);
    private JScrollPane cardsContainerScroll = new JScrollPane(cardsContainer);
    private CardRepository repository = CardRepository.getInstance();
    private Dimension programSize = getScreenSize();

    private LayoutManager mainGridLayout = new GridLayout(1, 2);

    private UUID viewingQCUUID = null;

    private static Color viewingPanelBGColor = new Color(250, 250, 250);

    private GUI () {
        cardsContainerScroll.setBorder(null);
        cardsContainerScroll.getViewport().setPreferredSize(Utils.getScrollSize());
        cardsContainerScroll.getVerticalScrollBar().setUnitIncrement(16);

        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        mainFrame.getContentPane().add(cardsContainerScroll);
        mainFrame.getContentPane().add(updateViewingPanel());

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(programSize);
        mainFrame.setLayout(mainGridLayout);
        mainFrame.getContentPane().setBackground(Color.WHITE);

        createMenuBar();
    }

    public static GUI getInstance() {
        if (gui != null) {
            return gui;
        }
        gui = new GUI();
        return gui;
    }

    public void start() {
        addCardsToPanel();

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public void setViewingQCUUID(UUID qcUUID) {
        this.viewingQCUUID = qcUUID;
        updateViewingPanel();
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

    private JPanel updateViewingPanel() {
        viewingPanel.removeAll();
        viewingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        viewingPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));
        viewingPanel.setBackground(viewingPanelBGColor);
        QuizCard qc = repository.getQCbyUUID(viewingQCUUID);
        if (qc != null) {
            viewingPanel.add(QuizCardViewingFactory.getQuizCardViewing(qc));
        } else {
            viewingPanel.add(new JLabel("No QuizCard is selected."));
        }
        viewingPanel.revalidate();
        return viewingPanel;
    }
}
