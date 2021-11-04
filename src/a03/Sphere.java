package a03;

import cgtools.Point;

public class Sphere {
    Point center;
    double radius;

    public Sphere(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public Hit intersect(Ray r){
        return null;
    }
}
