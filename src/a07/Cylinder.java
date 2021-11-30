package cgg.a07;

import cgg.a03.*;
import cgg.a04.*;
import cgg.a05.*;
import cgtools.*;

//Quelle zum überlegen:
//https://www.sofatutor.com/mathematik/geometrie/geometrie-kennenlernen/zylinder-kegel-pyramide-und-kugel-kennenlernen 

public class Cylinder implements Shape {

    public final double radius;
    public final double height;
    public final Point center;
    public final Plane floor;
    public final Plane ceiling;
    public final Material mat;
    public final Color color;

    public Cylinder(Point center, double radius, double height, Material mat) {
        this.radius = radius;
        this.center = center;
        this.height = height;
        this.mat = mat;

        //calculate floor an ceiling
        Point centerFloor = Vector.point(center.x, center.y - height / 2, center.z);
        Point centerCeiling = Vector.point(center.x, center.y + height / 2, center.z);
        this.floor = new Plane(centerFloor, Vector.direction(0, 1, 0), radius, mat);
        this.ceiling = new Plane(centerCeiling, Vector.direction(0, 1, 0), radius, mat);
        this.color = Color.red; // default
    }

    public Cylinder(Point center, double radius, double height, Color color) {
        this.radius = radius;
        this.center = center;
        this.height = height;
        this.color = color;
        this.mat = new DiffuseMat(color); // default

        //calculate floor an ceiling
        Point centerFloor = Vector.point(center.x, center.y - height / 2, center.z);
        Point centerCeiling = Vector.point(center.x, center.y + height / 2, center.z);
        this.floor = new Plane(centerFloor, Vector.direction(0, 1, 0), radius, color);
        this.ceiling = new Plane(centerCeiling, Vector.direction(0, 1, 0), radius, color);
    }

    //von Shepre geklaut vorläufig zum abändern

    public Hit intersect(Ray r) {

        Hit floorHit = floor.intersect(r);
        Hit ceilHit = ceiling.intersect(r);

        Hit hEnd = floorHit;

        if(ceilHit != null && (floorHit==null || ceilHit.t<floorHit.t)){
            hEnd = ceilHit;
        }

        // ray attributes
        Direction d = r.d;
        Point x0 = translateRayOrigin(r);

        //set y 0 to make sphere to disc
        Direction dDisc = Vector.direction(d.x, 0, d.z);
        Point x0Disc = Vector.point(x0.x, 0, x0.z);

        // multiplier for raydirection for hit
        double t;

        // add from Aufgabe 6
        double tClose = hitMultipier(dDisc, x0Disc, true);
        double tFar = hitMultipier(dDisc, x0Disc, false);

        if (r.isValid(tClose)&&tClose<tFar) {
            t = tClose;
        } else if (r.isValid(tFar)&&tFar<tClose) {
            t = tFar;
        } else {
            t = Double.NaN;
        }
        //

        if (Double.isNaN(t)||(hEnd != null && t>hEnd.t)) return hEnd; // no intersection or ceil or floor hit
        // calculate intersection point
        Point x = r.pointAt(t);
        if (x == null) return hEnd; // no intersection or ceil or floor hit

        // y-coordinate of Point x has so be between y-coordinate of floor and Ceiling
        double hitY = x.y;
        double floorY = center.y - height/2;
        double ceilY = center.y + height/2;

        if(hitY<floorY || hitY>ceilY) return hEnd; // no intersection with because not within the height of cylinder

        // normal vector of disc for hit point cylinder wall (y-coordinate always 0)
        Direction n = Vector.divide((Vector.subtract(x, center)), radius);
        Direction nWall = Vector.direction(n.x, 0, n.z);
        // create hit
        return new Hit(t, x, nWall, color, mat);
    }

    private double hitMultipier(Direction d, Point x0, boolean isClose) {
        // Calculate parameter for Check intersection based on ray parameters
        double a = Vector.dotProduct(d, d);
        double b = 2 * Vector.dotProduct(d, x0);
        double c = Vector.dotProduct(x0, x0) - radius * radius;
        // System.out.println("a: "+a);
        // System.out.println("b: "+b);
        // System.out.println("c: "+c);
        double dis = (b * b) - (4 * a * c);
        // System.out.println("dis: "+dis);
        if (dis < 0)
            return Double.NaN; // no intersection
        if (dis == 0) { // one intersection
            return b / ((-2) * a);
        } else { // two intersections
            if (isClose) {
                return (((-1) * b - Math.sqrt(dis)) / (2 * a));
            } else {
                return (((-1) * b + Math.sqrt(dis)) / (2 * a));
            }
        }
    }

    private Point translateRayOrigin(Ray r) {
        Point x0 = r.x0;
        // translate sphere center
        Point coordinateOrigin = Vector.point(0, 0, 0);
        Direction transDirection = Vector.subtract(coordinateOrigin, center);
        Point x0trans = Vector.add(x0, transDirection);
        // System.out.println("x0trans: "+x0trans);
        return x0trans;
    }

}
