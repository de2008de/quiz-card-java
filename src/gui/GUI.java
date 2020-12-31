package gui;

import card.QuizCard;
import repository.CardRepository;
import utils.Utils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.UUID;

import static utils.Utils.getScreenSize;

public class GUI {

    private static GUI gui;

    private JMenuBar menuBar;
    private JFrame mainFrame = new JFrame("QuizCard");
    private JPanel cardsContainer = new JPanel();
    private JPanel viewingPanel = new JPanel();
    private JScrollPane viewingScrollPanel = new JScrollPane(viewingPanel);
    private JFrame createFrame = CreateFrameFactory.getCreateFrame(cardsContainer);
    private JScrollPane cardsContainerScroll = new JScrollPane(cardsContainer);
    private CardRepository repository = CardRepository.getInstance();
    private Dimension programSize = getScreenSize();

    private LayoutManager mainGridLayout = new GridLayout(1, 2);

    private UUID viewingQCUUID = null;

    private static Color viewingPanelBGColor = new Color(250, 250, 250);

    private Font menuFont = new Font(null, Font.PLAIN, 24);

    private JFileChooser fc = new JFileChooser();

    private GUI () {

        viewingScrollPanel.getVerticalScrollBar().setUnitIncrement(16);

        cardsContainerScroll.setBorder(null);
        cardsContainerScroll.getViewport().setPreferredSize(Utils.getScrollSize());
        cardsContainerScroll.getVerticalScrollBar().setUnitIncrement(16);

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
        JMenu qcMenu = new JMenu("QuizCard");
        JMenu fileMenu = new JMenu("File");

        fileMenu.setFont(menuFont);
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setFont(menuFont);
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save card repository
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showDialog(null, "Save");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    Utils.saveCSV(repository.getQuizCards(), file.getPath());
                } else {

                }
            }
        });
        fileMenu.add(saveMenuItem);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.setFont(menuFont);
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load csv into card repository
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnVal = fc.showDialog(null, "Load");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    Utils.loadCSV(file.getPath());
                } else {

                }
            }
        });
        fileMenu.add(loadMenuItem);

        qcMenu.setFont(menuFont);
        JMenuItem createQCMenuItem = new JMenuItem("Create");
        createQCMenuItem.setFont(menuFont);
        createQCMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCreateFrameVisible();
            }
        });
        qcMenu.add(createQCMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(qcMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    public void addCardsToPanel() {
        cardsContainer.removeAll();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));
        List<QuizCard> quizCardList = repository.getQuizCards();
        for (QuizCard quizCard : quizCardList) {
            cardsContainer.add(QuizCardGUI.getQuizCardGui(quizCard));
        }
        cardsContainer.revalidate();
    }

    private JScrollPane updateViewingPanel() {
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
        return viewingScrollPanel;
    }
}
