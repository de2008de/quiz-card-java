package gui;

import card.ConceptCard;
import card.QuizCard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class QuizCardViewingFactory {

    private static Font titleFont = new Font(null, Font.PLAIN, 36);
    private static Font descFont = new Font(null, Font.PLAIN, 24);
    private static Font termFont = new Font(null, Font.PLAIN, 28);
    private static Font defFont = new Font(null, Font.PLAIN, 20);

    private static Color termColor = new Color(0, 0, 0, 0.7f);
    private static Color defColor = new Color(0, 0, 0, 0.6f);

    private static LayoutManager flowLayoutLeft = new FlowLayout(FlowLayout.LEFT);

    private static Color ccBgColors = new Color(252/255f, 244/255f, 212/255f, 0.7f);

    public static JPanel getQuizCardViewing(QuizCard quizCard) {
        JPanel qcPanel = new JPanel();
        qcPanel.setOpaque(false);
        qcPanel.setLayout(new BoxLayout(qcPanel, BoxLayout.PAGE_AXIS));

        JPanel containerTitleDescPanel = new JPanel();
        containerTitleDescPanel.setLayout(new BoxLayout(containerTitleDescPanel, BoxLayout.PAGE_AXIS));
        containerTitleDescPanel.setOpaque(false);
        qcPanel.add(containerTitleDescPanel);

        // Create title panel
        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel(quizCard.getTitle());
        title.setFont(titleFont);
        titlePanel.setOpaque(false);
        titlePanel.setLayout(flowLayoutLeft);
        titlePanel.add(title);
        containerTitleDescPanel.add(titlePanel);

        // Create description panel
        JPanel descPanel = new JPanel();
        String descStr = quizCard.getDescription();
        if (descStr == null) {
            descStr = "No description.";
        }
        JLabel desc = new JLabel(descStr);
        desc.setFont(descFont);
        descPanel.setOpaque(false);
        descPanel.setLayout(flowLayoutLeft);
        descPanel.add(desc);
        containerTitleDescPanel.add(descPanel);

        // Create concept cards panel
        List<ConceptCard> conceptCardList = quizCard.getConceptCards();
        for (ConceptCard cc : conceptCardList) {
            JPanel ccPanel = new JPanel();
            // Set concept card border
            JPanel borderPnel = new JPanel();
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1, true);
            Border paddingBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
            Border compound = BorderFactory.createCompoundBorder(lineBorder, paddingBorder);
            ccPanel.setBorder(compound);
            ccPanel.setBackground(ccBgColors);
            ccPanel.setLayout(new BoxLayout(ccPanel, BoxLayout.PAGE_AXIS));

            Border marginBorder =  BorderFactory.createEmptyBorder(20, 20, 20, 20);
            borderPnel.setBorder(marginBorder);
            borderPnel.setLayout(new BoxLayout(borderPnel, BoxLayout.PAGE_AXIS));
            borderPnel.setOpaque(false);
            borderPnel.add(ccPanel);

            // Create concept card term panel
            JPanel termPanel = new JPanel();
            JTextArea term = new JTextArea(cc.getTerm());
            term.setFont(termFont);
            term.setForeground(termColor);
            term.setOpaque(false);
            termPanel.setLayout(new BoxLayout(termPanel, BoxLayout.PAGE_AXIS));
            termPanel.setOpaque(false);
            termPanel.add(term);

            // Create concept card definition panel
            JPanel defPanel = new JPanel();
            JTextArea def = new JTextArea(cc.getDefinition());
            def.setFont(defFont);
            def.setForeground(defColor);
            def.setOpaque(false);
            def.setLineWrap(true);
            def.setWrapStyleWord(true);
            defPanel.setLayout(new BoxLayout(defPanel, BoxLayout.PAGE_AXIS));
            defPanel.setOpaque(false);
            defPanel.add(def);

            ccPanel.setLayout(new BoxLayout(ccPanel, BoxLayout.PAGE_AXIS));
            ccPanel.add(termPanel);
            ccPanel.add(defPanel);

            qcPanel.add(borderPnel);
        }

        return qcPanel;
    }
}
