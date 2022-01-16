package a10;

import a03.Ray;
import cgtools.Color;
import cgtools.Point;
import cgtools.Vector;

public class Background implements Shape {
    Constant bg;
    Point center;
    double radius;
    Material mat;

    public Background(Color bg) {
        center = Vector.point(0, 0, 0);
        radius = Double.POSITIVE_INFINITY;
        this.bg = new Constant(bg);
        this.mat = new BackMat(bg);
    }

    public Background(Material mat) {
        center = Vector.point(0, 0, 0);
        radius = Double.POSITIVE_INFINITY;
        this.bg = new Constant(Color.black);
        this.mat = mat;
    }

    public Hit intersect(Ray r) {
        double inclination = Math.acos(r.d.y);
	    double azimuth = Math.PI + Math.atan2(r.d.x, r.d.z);
	    double u = azimuth / (2 * Math.PI);
	    double v = inclination / Math.PI;
        if (r.tmax == Double.POSITIVE_INFINITY || r.tmax == Double.NEGATIVE_INFINITY){
            return new Hit(Vector.point(0, 0, 0), Double.POSITIVE_INFINITY, bg.getColor(u, v), r.d, mat, u, v);
        }
        //if (r.tmax == Double.NEGATIVE_INFINITY){
        //    return new Hit(Vector.point(0, 0, 0), Double.NEGATIVE_INFINITY, bg, r.d, mat, u, v);
        //}
        return null;
    }
}
