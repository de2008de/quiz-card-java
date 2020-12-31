package gui;

import card.ConceptCard;
import card.QuizCard;
import services.CardService;
import services.CardServiceImpl;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CreateFrameFactory {

    private static JFrame createFrame = new JFrame("Create Quiz Card");
    private static JPanel conceptCardPanel;

    private static LayoutManager flowLayoutLeft = new FlowLayout(FlowLayout.LEFT);

    private static Font titledBorderFont = new Font(null, Font.PLAIN, 24);

    private static CardService cardService = new CardServiceImpl();

    private static List<ConceptCardInput> conceptCardInputList = new ArrayList<>();

    private static JTextField titleField;
    private static JTextField descField;

    private static JScrollPane createScrollPane;
    private static JPanel createPanel;
    private static JPanel mainContainerPanel = new JPanel();

    public static JFrame getCreateFrame(JPanel cardsContainer) {
        // Create panel for creating new cards
        createPanel = new JPanel();
        createScrollPane = new JScrollPane(
                createPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        createScrollPane.setBorder(null);
        createScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.PAGE_AXIS));

        JPanel qcTextFieldPanel = new JPanel();
        qcTextFieldPanel.setLayout(new BoxLayout(qcTextFieldPanel, BoxLayout.PAGE_AXIS));

        // Create panel for QuizCard title text field
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(flowLayoutLeft);
        titlePanel.setOpaque(false);
        JPanel titleFieldPanel = TextFieldFactory.getTextField("Title:", 20);
        titleField = (JTextField) titleFieldPanel.getComponent(1);
        titlePanel.add(titleFieldPanel);

        // Create panel for QuizCard description text field
        JPanel descPanel = new JPanel();
        descPanel.setLayout(flowLayoutLeft);
        descPanel.setOpaque(false);
        JPanel descTextFieldPanel = TextFieldFactory.getTextField("Description:", 20);
        descField = (JTextField) descTextFieldPanel.getComponent(1);
        descPanel.add(descTextFieldPanel);

        qcTextFieldPanel.add(titlePanel);
        qcTextFieldPanel.add(descPanel);
        TitledBorder qcTitledBorder = BorderFactory.createTitledBorder("Quiz Card");
        qcTitledBorder.setTitleFont(titledBorderFont);
        Border qcMarginBorder = BorderFactory.createEmptyBorder(0, 0, 0, 30);
        Border qcCompoundBorder = BorderFactory.createCompoundBorder(qcMarginBorder, qcTitledBorder);
        qcTextFieldPanel.setBorder(qcCompoundBorder);

        // Create panel for buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(flowLayoutLeft);
        btnPanel.setOpaque(false);
        btnPanel.add(getAddConceptCardBtn());

        // Create a panel for all concept cards
        conceptCardPanel = new JPanel();
        conceptCardPanel.setOpaque(false);
        conceptCardPanel.setLayout(new BoxLayout(conceptCardPanel, BoxLayout.PAGE_AXIS));
        addConceptCardInputField();

        createPanel.add(qcTextFieldPanel);
        createPanel.add(conceptCardPanel);
        createPanel.add(btnPanel);

        // Create panel for submit button at the top
        JPanel topBtnPanel = new JPanel();
        topBtnPanel.setOpaque(false);
        topBtnPanel.setLayout(flowLayoutLeft);
        topBtnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        topBtnPanel.add(ButtonFactory.getControlButton("Submit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuizCard quizCard = new QuizCard();
                quizCard.setTitle(titleField.getText());
                quizCard.setDescription(descField.getText());
                // Add concept cards to quiz card
                for (ConceptCardInput cci : conceptCardInputList) {
                    ConceptCard cc = new ConceptCard();
                    cc.setTerm(cci.termTextField.getText());
                    cc.setDefinition(cci.defTextArea.getText());
                    quizCard.addConceptCard(cc);
                }

                cardService.addQuizCard(quizCard);
                cardsContainer.add(QuizCardGUI.getQuizCardGui(quizCard), 0);
                cardsContainer.revalidate();
            }
        }));

        // Create main container panel
        mainContainerPanel.setLayout(new BoxLayout(mainContainerPanel, BoxLayout.PAGE_AXIS));
        mainContainerPanel.setOpaque(false);
        mainContainerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 100, 50));
        mainContainerPanel.add(topBtnPanel);
        mainContainerPanel.add(createScrollPane);

        createFrame.getContentPane().add(mainContainerPanel);
        createFrame.setLocation(100, 0);
        createFrame.pack();

        return createFrame;
    }

    public static void addConceptCardInputField() {
        conceptCardPanel.add(getConceptCardInputPanel());
        mainContainerPanel.revalidate();
    }

    private static JPanel getConceptCardInputPanel() {
        JPanel ccTextFieldPanel = new JPanel();
        ccTextFieldPanel.setLayout(new BoxLayout(ccTextFieldPanel, BoxLayout.PAGE_AXIS));

        TitledBorder ccTitledBorder = BorderFactory.createTitledBorder("Concept Card");
        ccTitledBorder.setTitleFont(titledBorderFont);
        Border marginBorder = BorderFactory.createEmptyBorder(30, 0, 0, 30);
        Border compound = BorderFactory.createCompoundBorder(marginBorder, ccTitledBorder);
        ccTextFieldPanel.setBorder(compound);

        // Create panel for concept card term text field
        JPanel termPanel = new JPanel();
        termPanel.setLayout(flowLayoutLeft);
        termPanel.setOpaque(false);
        JPanel termTextFieldPanel = TextFieldFactory.getTextField("Term:", 10);
        termPanel.add(termTextFieldPanel);

        // Create panel for concept card definition text field
        JPanel defPanel = new JPanel();
        defPanel.setLayout(flowLayoutLeft);
        defPanel.setOpaque(false);
        JPanel defTextAreaPanel = TextFieldFactory.getTextArea("Definition:", 5, 20);
        defPanel.add(defTextAreaPanel);

        ccTextFieldPanel.add(termPanel);
        ccTextFieldPanel.add(defPanel);

        // Save text field and area in a list
        ConceptCardInput cci = new ConceptCardInput();
        cci.termTextField = (JTextField) termTextFieldPanel.getComponent(1);
        cci.defTextArea = (JTextArea) defTextAreaPanel.getComponent(1);
        conceptCardInputList.add(cci);

        return ccTextFieldPanel;
    }

    private static JButton getAddConceptCardBtn() {
        JButton btn = ButtonFactory.getControlButton("+", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addConceptCardInputField();
            }
        });
        return btn;
    }

    private static class ConceptCardInput {
        JTextField termTextField;
        JTextArea defTextArea;
    }
}
