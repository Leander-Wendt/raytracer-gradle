package a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Sphere {
    Point center;
    double radius;
    Color col;

    public Sphere(Point center, double radius, Color c){
        this.center = center;
        this.radius = radius;
        this.col = c;
    }

    public Hit intersect(Ray r){
        Point x0 = Vector.subtract(r.x0, Vector.direction(center.x, center.y, center.z));
        double a = Vector.dotProduct(r.d, r.d);
        double b = 2 * Vector.dotProduct(x0, r.d);
        double c = Vector.dotProduct(x0, x0) - (radius * radius);
        double diskriminante = (b * b) - (4 * a * c);
        if (diskriminante == 0) {
            double t1 = (-1 * b) / (2 * a);
            Point temp = Vector.add(r.x0, Vector.multiply(t1, r.d));
            Direction n = Vector.divide(Vector.subtract(temp, center), radius); 
            return new Hit(temp, t1, col, n);
        }
        if (diskriminante > 0) {
            double t1 = (-1 * b + Math.round(Math.pow(diskriminante, 1.0 / 2.0))) / 2 * a;
            double t2 = (-1 * b - Math.round(Math.pow(diskriminante, 1.0 / 2.0))) / 2 * a;
            if (t1 < t2 && r.isValid(t1)){
                Point temp = Vector.add(r.x0, Vector.multiply(t1, r.d));
                Direction n = Vector.divide(Vector.subtract(temp, center), radius); 
                return new Hit(temp, t1, col, n);
            } else {
                if (r.isValid(t2)){
                    Point temp = Vector.add(r.x0, Vector.multiply(t2, r.d));
                    Direction n = Vector.divide(Vector.subtract(temp, center), radius); 
                    return new Hit(temp, t2, col, n);
                }
            }
        }
        return null;
    }
}
