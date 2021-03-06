package utils;

import card.ConceptCard;
import card.QuizCard;
import gui.GUI;
import repository.CardRepository;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Utils {
    private static Toolkit t = Toolkit.getDefaultToolkit();
    private static final double SCALE = 0.8;
    private static CardRepository repository = CardRepository.getInstance();
    private static String delimiter = "/qc-delimiter/";
    private static String qcComma = "/qc-comma/";
    private static String encoding = "UTF-8";

    public static Dimension getScreenSize() {
        Dimension d = t.getScreenSize();
        d.setSize(d.getWidth()*SCALE, d.getHeight()*SCALE);
        return d;
    }

    public static Dimension getScrollSize() {
        int height = (int) getScreenSize().getHeight();
        int width = (int) getScreenSize().getWidth();
        return new Dimension(width / 3, height - 100);
    }

    public static void saveCSV(List<QuizCard> quizCardList, String path) {
        // Open file to save
        path = path + "\\quizcard.qc";
        File csvOutputFile = new File(path);
        List<String> csvStringList = new ArrayList<>();
        for (QuizCard qc : quizCardList) {
            List<String> stringList = qc.getStrings();
            // Convert to CSV
            String csvString = stringList.stream()
                    .collect(Collectors.joining(qcComma));
            csvString += delimiter;
            csvStringList.add(csvString);
        }
        try (PrintWriter pw = new PrintWriter(csvOutputFile, encoding)) {
            csvStringList.stream()
                    .forEach(pw::print);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void loadCSV(String path) {
        try (Scanner scanner = new Scanner(new File(path), encoding)) {
            repository.clearRepository();
            scanner.useDelimiter(delimiter);
            while (scanner.hasNext()) {
                String csvString = scanner.next();
                String[] csvStrings = csvString.split(qcComma);
                QuizCard qc = new QuizCard();
                qc.setTitle(csvStrings[0]);
                qc.setDescription(csvStrings[1]);
                // Create concept cards
                for (int i = 2; i < csvStrings.length; i += 2) {
                    ConceptCard cc = new ConceptCard();
                    cc.setTerm(csvStrings[i]);
                    cc.setDefinition(csvStrings[i+1]);
                    qc.addConceptCard(cc);
                }
                repository.addQuizCard(qc);
            }

            GUI.getInstance().addCardsToPanel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
