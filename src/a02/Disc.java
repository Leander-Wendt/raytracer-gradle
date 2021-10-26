package a02;

import cgtools.Color;
import cgtools.Sampler;

public class Disc implements Sampler {
    private int width, height, cx, cy, radius;
    private Color c, bg;

    Disc (int width, int height, int x, int y, int radius, Color c) {
        this.width = width;
        this.height = height;
        cx = x;
        cy = y;
        this.c = c;
        bg = new Color(0, 0, 0);       
    }

    public Color getColor(double x, double y) {
        double dx = Math.pow(x - cx, 2);
        double dy = Math.pow(y - cy, 2);
        double d = Math.pow( dx + dy, (double) 1/2);
        if (d <= radius) return c;
        return bg;
      }
    
}
