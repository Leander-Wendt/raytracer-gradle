package a04;

import a03.Hit;
import a03.Ray;
import cgtools.Color;
import cgtools.Point;
import cgtools.Vector;

public class Background implements Shape {
    Color bg;
    Point center;
    double radius;

    public Background() {
        center = Vector.point(0, 0, 0);
        radius = Double.POSITIVE_INFINITY;
        bg = new Color(0.01, 0.01, 0.01);
    }

    public Hit intersect(Ray r) {
        return new Hit(Vector.point(0, 0, 0), Double.POSITIVE_INFINITY, bg, Vector.direction(0, 0, 0));
    }
}
