package a04;

import a03.Hit;
import a03.Ray;
import a05.DiffuseMaterial;
import a05.Material;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Plane implements Shape {
    public double radius;
    public Point p;
    public Direction n;
    public Color c;
    public Material mat;

    public Plane(Point p, Direction n, double radius, Color c) {
        this.radius = radius;
        this.p = p;
        this.n = n;
        this.c = c;
        this.mat = new DiffuseMaterial(c);
    }

    public Plane(Point p, Direction n, double radius, Material mat) {
        this.radius = radius;
        this.p = p;
        this.n = n;
        this.c = Color.gray;
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
                return new Hit(hPoint, t, c, n, mat);
            }
            return null;
        }
        return null;    
    } 
}
