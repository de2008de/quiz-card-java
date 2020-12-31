package gui;

import card.ConceptCard;
import card.QuizCard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class QuizCardViewingFactory {

    private static Font titleFont = new Font(null, Font.PLAIN, 36);
    private static Font descFont = new Font(null, Font.PLAIN, 24);
    private static Font termFont = new Font(null, Font.BOLD, 28);
    private static Font defFont = new Font(null, Font.PLAIN, 20);

    private static LayoutManager flowLayoutLeft = new FlowLayout(FlowLayout.LEFT);

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
            ccPanel.setOpaque(false);
            // Set concept card border
            JPanel borderPnel = new JPanel();
            Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1, true);
            Border marginBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
            Border compound = BorderFactory.createCompoundBorder(marginBorder, lineBorder);
            borderPnel.setBorder(compound);
            borderPnel.setLayout(new FlowLayout(FlowLayout.LEFT));
            borderPnel.setOpaque(false);
            borderPnel.add(ccPanel);

            borderPnel.setPreferredSize(new Dimension(600, 200));

            // Create concept card term panel
            JPanel termPanel = new JPanel();
            JLabel term = new JLabel(cc.getTerm());
            term.setFont(termFont);
            termPanel.setLayout(flowLayoutLeft);
            termPanel.setOpaque(false);
            termPanel.add(term);

            // Create concept card definition panel
            JPanel defPanel = new JPanel();
            JLabel def = new JLabel(cc.getDefinition());
            def.setFont(defFont);
            defPanel.setLayout(flowLayoutLeft);
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
