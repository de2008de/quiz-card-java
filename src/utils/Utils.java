package utils;

import card.QuizCard;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    private static Toolkit t = Toolkit.getDefaultToolkit();
    private static final double SCALE = 0.8;

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
        path = path + "\\quizcard.csv";
        File csvOutputFile = new File(path);
        List<String> csvStringList = new ArrayList<>();
        for (QuizCard qc : quizCardList) {
            List<String> stringList = qc.getStrings();
            // Convert to CSV
            String csvString = stringList.stream()
                    .collect(Collectors.joining(","));
            csvStringList.add(csvString);
        }
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            csvStringList.stream()
                    .forEach(pw::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
