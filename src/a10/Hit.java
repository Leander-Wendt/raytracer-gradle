package a10;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public class Hit {
    public Point x;
    public double t;
    private Direction n;
    private Color c;
    private Material mat;
    private double u;
    private double v;
    
    public Hit(Point x, double t, Color c, Direction n, Material mat, double u, double v) {
        this.x = x;
        this.c = c;
        this.n = n;
        this.t = t;
        this.mat = mat;
        this.u = u;
        this.v = v;
    }

    public Color getColor() {
        return c;
    }

    public Direction getN(){
        return n;
    }

    public Material getMaterial() {
        return mat;
    }

    public double getU(){
        return u;
    }

    public double getV(){
        return v;
    }
}
