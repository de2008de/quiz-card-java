package utils;

import java.awt.*;

public class Utils {
    private static Toolkit t = Toolkit.getDefaultToolkit();
    public static Dimension getScreenSize(double scale) {
        Dimension d = t.getScreenSize();
        d.setSize(d.getWidth()*scale, d.getHeight()*scale);
        return d;
    }
}
