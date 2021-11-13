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

    public Background(Color bg) {
        center = Vector.point(0, 0, 0);
        radius = Double.POSITIVE_INFINITY;
        this.bg = bg;
    }

    public Hit intersect(Ray r) {
        if (r.tmax == Double.POSITIVE_INFINITY){
            return new Hit(Vector.point(0, 0, 0), Double.POSITIVE_INFINITY, bg, r.d);
        }
        if (r.tmax == Double.NEGATIVE_INFINITY){
            return new Hit(Vector.point(0, 0, 0), Double.NEGATIVE_INFINITY, bg, r.d);
        }
        return null;
    }
}
