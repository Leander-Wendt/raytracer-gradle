package a04;

import a03.Hit;
import a03.Ray;
import a05.Material;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Plane implements Shape {
    public double radius;
    public Point p;
    public Direction n;
    Color c;
    Material mat;

    // Anchor aus Normalenvektor berechenen, oder andersrum FUUUUCK
    public Plane(Point p, Direction n, Color c, double radius) {
        this.radius = radius;
        this.p = p;
        this.n = n;
        this.c = c;
    }

    public Plane(Point p, Direction n, Material mat, double radius) {
        this.radius = radius;
        this.p = p;
        this.n = n;
        this.mat = mat;
    }

    public Hit intersect (Ray r) {
        Direction temp = Vector.subtract(p, r.x0);
        double a = Vector.dotProduct(temp, n);
        double b = Vector.dotProduct(r.d, n);
        if (b != 0){    
            double t = a / b;
            if (Vector.length(Vector.direction(Vector.add(r.x0, Vector.subtract(p, Vector.multiply(t, r.d))))) < radius && r.isValid(t)) {
                Point hPoint = Vector.add(r.x0, Vector.multiply(t, r.d));
                return new Hit(hPoint, t, c, n);
            }
            return null;
        }
        return null;    
    } 
}
