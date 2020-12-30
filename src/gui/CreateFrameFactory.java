package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateFrameFactory {

    private static JFrame createFrame = new JFrame("Create Quiz Card");
    private static JPanel conceptCardPanel;

    private static LayoutManager flowLayoutLeft = new FlowLayout(FlowLayout.LEFT);

    private static Font btnFont = new Font(null, Font.PLAIN, 26);
    private static Font titledBorderFont = new Font(null, Font.PLAIN, 24);

    public static JFrame getCreateFrame() {
        // Create panel for creating new cards
        JPanel createPanel = new JPanel();
        JScrollPane createScrollPane = new JScrollPane(createPanel);
        createScrollPane.setBorder(null);
        createScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.PAGE_AXIS));
        createPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 100, 50));

        JPanel qcTextFieldPanel = new JPanel();
        qcTextFieldPanel.setLayout(new BoxLayout(qcTextFieldPanel, BoxLayout.PAGE_AXIS));

        // Create panel for QuizCard title text field
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(flowLayoutLeft);
        titlePanel.setOpaque(false);
        titlePanel.add(TextFieldFactory.getTextField("Title:", 20));

        // Create panel for QuizCard description text field
        JPanel descPanel = new JPanel();
        descPanel.setLayout(flowLayoutLeft);
        descPanel.setOpaque(false);
        descPanel.add(TextFieldFactory.getTextField("Description:", 20));

        qcTextFieldPanel.add(titlePanel);
        qcTextFieldPanel.add(descPanel);
        TitledBorder qcTitledBorder = BorderFactory.createTitledBorder("Quiz Card");
        qcTitledBorder.setTitleFont(titledBorderFont);
        qcTextFieldPanel.setBorder(qcTitledBorder);

        // Create panel for buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(flowLayoutLeft);
        btnPanel.setOpaque(false);
        btnPanel.add(getAddConceptCardBtn());

        // Create a panel for all concept cards
        conceptCardPanel = new JPanel();
        conceptCardPanel.setOpaque(false);
        conceptCardPanel.setLayout(new BoxLayout(conceptCardPanel, BoxLayout.PAGE_AXIS));
        conceptCardPanel.add(getConceptCardInputPanel());

        createPanel.add(qcTextFieldPanel);
        createPanel.add(conceptCardPanel);
        createPanel.add(btnPanel);

        createFrame.getContentPane().add(createScrollPane);
        createFrame.setLocation(100, 0);
        createFrame.pack();

        return createFrame;
    }

    public static void addConceptCardInputField() {
        conceptCardPanel.add(getConceptCardInputPanel());
        createFrame.revalidate();
    }

    private static JPanel getConceptCardInputPanel() {
        JPanel ccTextFieldPanel = new JPanel();
        ccTextFieldPanel.setLayout(new BoxLayout(ccTextFieldPanel, BoxLayout.PAGE_AXIS));

        // Create panel for concept card term text field
        JPanel termPanel = new JPanel();
        termPanel.setLayout(flowLayoutLeft);
        termPanel.setOpaque(false);
        termPanel.add(TextFieldFactory.getTextField("Term:", 10));

        // Create panel for concept card definition text field
        JPanel defPanel = new JPanel();
        defPanel.setLayout(flowLayoutLeft);
        defPanel.setOpaque(false);
        defPanel.add(TextFieldFactory.getTextArea("Definition:", 5, 20));

        ccTextFieldPanel.add(termPanel);
        ccTextFieldPanel.add(defPanel);
        ccTextFieldPanel.setBackground(Color.YELLOW);

        return ccTextFieldPanel;
    }

    private static JButton getAddConceptCardBtn() {
        JButton btn = new JButton("Add");
        btn.setFont(btnFont);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addConceptCardInputField();
            }
        });
        return btn;
    }
}
