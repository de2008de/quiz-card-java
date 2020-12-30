package utils;

import java.awt.*;

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
}
