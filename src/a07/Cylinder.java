package a07;

import a03.Hit;
import a03.Ray;
import a04.Plane;
import a04.Shape;
import a05.DiffuseMaterial;
import a05.Material;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Cylinder implements Shape {

    public double rad;
    public double h;
    public Point center;
    public Plane bottom;
    public Plane top;
    public Material mat;
    public Color col;

    public Cylinder(Point center, double rad, double h, Material mat) {
        this.center = center;
        this.rad = rad;
        this.h = h;
        this.mat = mat;
        this.col = Color.violet;
        calcEndings();
    }

    public Cylinder(Point center, double rad, double h, Color col) {
        this.center = center;
        this.rad = rad;
        this.h = h;
        this.col = col;
        this.mat = new DiffuseMaterial(col);
        calcEndings();
    }

    private void calcEndings(){
        Point centerFloor = Vector.point(center.x, center.y - h / 2, center.z);
        Point centerCeiling = Vector.point(center.x, center.y + h / 2, center.z);
        this.bottom = new Plane(centerFloor, Vector.direction(0, 1, 0), rad, col);
        this.top = new Plane(centerCeiling, Vector.direction(0, 1, 0), rad, col);
    }

    public Hit intersect(Ray r) {
        Hit bottomHit = bottom.intersect(r);
        Hit topHit = top.intersect(r);
        Hit hEnd = bottomHit;

        if(topHit != null && (bottomHit == null || topHit.t < bottomHit.t)){
            hEnd = topHit;
        }

        Direction dir = r.d;
        Point x0 = translateRayOrigin(r);

        // make Direction 2 dimensional
        Direction dDisc = Vector.direction(dir.x, 0, dir.z);
        Point x0Disc = Vector.point(x0.x, 0, x0.z);
        double t;

        double tClose = sphereIntersect(dDisc, x0Disc, true);
        double tFar = sphereIntersect(dDisc, x0Disc, false);

        if (r.isValid(tClose) && tClose < tFar) {
            t = tClose;
        } else if (r.isValid(tFar) && tClose > tFar) {
            t = tFar;
        } else {
            t = Double.NaN;
        }

        if (Double.isNaN(t) || (hEnd != null && t > hEnd.t)) {
            return hEnd;
        } 
        
        Point x = r.pointAt(t);
        if (x == null) return hEnd;

        double hitY = x.y;
        double floorY = center.y - h / 2;
        double ceilY = center.y + h / 2;

        if (hitY < floorY || hitY > ceilY){ 
            return hEnd; 
        }

        Direction n = Vector.divide((Vector.subtract(x, center)), rad);
        Direction nEnd = Vector.direction(n.x, 0, n.z);
        return new Hit(x, t, col, nEnd, mat);
    }

    // modified intersect Method from Sphere class
    private double sphereIntersect(Direction dir, Point x0, boolean near) {
        double a = Vector.dotProduct(dir, dir);
        double b = 2 * Vector.dotProduct(dir, x0);
        double c = Vector.dotProduct(x0, x0) - rad * rad;
        double diskr = (b * b) - (4 * a * c);
        if (diskr >= 0){
            if (diskr == 0){
                return b / (-2 * a);
            } else {
                if (near) {
                    return ((-1 * b - Math.sqrt(diskr)) / (2 * a));
                } else {
                    return ((-1 * b + Math.sqrt(diskr)) / (2 * a));
                }
            }
        } else {
            return Double.NaN;
        }
    }

    private Point translateRayOrigin(Ray r) {
        Direction translatedDirection = Vector.subtract(Vector.zero, center);
        return Vector.add(r.x0, translatedDirection);
    }
}
