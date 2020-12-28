package gui;

import card.QuizCard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class QuizCardGUI {

    private static Font cardTitleFont = new Font(null, Font.PLAIN, 32);
    private static Font cardDescriptionFont = new Font(null, Font.PLAIN, 24);
    private static Font numCCardFont = new Font(null, Font.PLAIN, 70);
    private static Font metadataFont = new Font(null, Font.PLAIN, 24);

    private static Color cardTitleColor = new Color(0f, 0f, 0f, 0.7f);
    private static Color cardDescriptionColor = new Color(0f, 0f, 0f, 0.6f);
    private static Color cardBorderColor = new Color(224, 224, 224);
    private static Color cardHalfCircleColor = new Color(240/255f, 251/255f, 255/255f, 0.9f);
    private static Color metadataColor = new Color(0f, 0f, 0f, 0.7f);

    private static LayoutManager flowLayoutLeft = new FlowLayout(FlowLayout.LEFT);
    private static LayoutManager flowLayoutCenter = new FlowLayout(FlowLayout.CENTER);
    private static LayoutManager flowLayoutRight = new FlowLayout(FlowLayout.RIGHT);

    private static int cardWidth = 600;
    private static int cardHeight = 276;

    public static JPanel getQuizCardGui(QuizCard quizCard) {
        JPanel borderPanel = new JPanel();
        JPanel quizCardGui = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(cardHalfCircleColor);
                g2d.fillArc(cardWidth - 200,1,cardWidth, cardHeight + 300, 90,90);
            }
        };

        borderPanel.add(quizCardGui);
        borderPanel.setBackground(Color.WHITE);

        Border cardItemPaddingBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        Border cardItemLineBorder = BorderFactory.createLineBorder(cardBorderColor, 2, true);
        Border cardItemCompoundBorder = BorderFactory.createCompoundBorder(cardItemLineBorder, cardItemPaddingBorder);

        quizCardGui.setLayout(new BoxLayout(quizCardGui, BoxLayout.Y_AXIS));
        quizCardGui.setPreferredSize(new Dimension(cardWidth, cardHeight));
        quizCardGui.setBorder(cardItemCompoundBorder);
        quizCardGui.setBackground(Color.WHITE);

        Border cardItemMarginBorder = BorderFactory.createEmptyBorder(10, 50, 10,   50);
        borderPanel.setBorder(cardItemMarginBorder);


        JPanel cardTitlePanel = new JPanel();
        JLabel cardTitle = new JLabel(quizCard.getTitle());
        cardTitle.setFont(cardTitleFont);
        cardTitle.setForeground(cardTitleColor);
        cardTitlePanel.setLayout(flowLayoutLeft);
        cardTitlePanel.setOpaque(false);
        cardTitlePanel.add(cardTitle);

        quizCardGui.add(cardTitlePanel);


        String description = quizCard.getDescription();
        JPanel cardDescPanel = new JPanel();
        JLabel cardDescription = new JLabel();
        cardDescription.setFont(cardDescriptionFont);
        cardDescription.setForeground(cardDescriptionColor);
        if (description != null) {
            cardDescription.setText(description);
        } else {
            cardDescription.setText("This card has no description. :p");
        }
        cardDescPanel.setOpaque(false);
        cardDescPanel.setLayout(flowLayoutLeft);
        cardDescPanel.add(cardDescription);

        quizCardGui.add(cardDescPanel);


        // Show number of concept cards on QuizCard
        int numCCard = quizCard.getConceptCards().size();
        JPanel metadataPanel = new JPanel();


        JPanel numCCardPanel = new JPanel();
        JLabel numCCardLabel = new JLabel(String.valueOf(numCCard));

        numCCardLabel.setFont(numCCardFont);
        numCCardLabel.setForeground(metadataColor);
        numCCardPanel.setOpaque(false);
        numCCardPanel.setLayout(flowLayoutCenter);
        numCCardPanel.add(numCCardLabel);

        JPanel metaTextPanel = new JPanel();
        JLabel metadataText = new JLabel("Concept Card(s)");

        metadataText.setFont(metadataFont);
        metadataText.setForeground(metadataColor);
        metaTextPanel.setOpaque(false);
        metaTextPanel.setLayout(flowLayoutCenter);
        metaTextPanel.add(metadataText);


        metadataPanel.setLayout(new BoxLayout(metadataPanel, BoxLayout.Y_AXIS));
        metadataPanel.add(numCCardPanel);
        metadataPanel.add(metaTextPanel);
        metadataPanel.setOpaque(false);
        metadataPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));


        JPanel metaFlowRightPanel = new JPanel();
        metaFlowRightPanel.add(metadataPanel);
        metaFlowRightPanel.setLayout(flowLayoutRight);
        metaFlowRightPanel.setOpaque(false);

        quizCardGui.add(metaFlowRightPanel);

        return borderPanel;
    }
}
