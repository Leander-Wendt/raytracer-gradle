package a03;

import a05.Material;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public class Hit {
    public Point x;
    public double t;
    private Direction n;
    private Color c;
    private Material mat;
    
    public Hit(Point x, double t, Color c, Direction n, Material mat) {
        this.x = x;
        this.c = c;
        this.n = n;
        this.t = t;
        this.mat = mat;
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
}
