package a04;

import a03.Hit;
import a03.Ray;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Plane implements Shape {
    public double anchor;
    public Point p;
    public Direction n;
    Color c;

    public Plane(double anchor, Point p, Direction n) {
        this.anchor = anchor;
        this.p = p;
        this.n = n;
        c = new Color(0.75, 0.75, 0.75);
    }

    public Hit intersect (Ray r) {
        Direction temp = Vector.subtract(p, r.x0);
        double a = Vector.dotProduct(temp, n);
        double b = Vector.dotProduct(r.d, n);
        if (b != 0){    
            double t = a / b;
            if (Vector.length(Vector.direction(Vector.add(r.x0, Vector.subtract(p, Vector.multiply(t, r.d))))) < anchor && r.isValid(t)) {
                Point hPoint = Vector.add(r.x0, Vector.multiply(t, r.d));
                return new Hit(hPoint, t, c, n);
            }
            return null;
        } else {
            return null;
        }
    } 
}
