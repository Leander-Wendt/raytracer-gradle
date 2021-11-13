package a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public class Hit {
    Point x;
    public double t;
    private Direction n;
    private Color c;
    
    public Hit(Point x, double t, Color c, Direction n) {
        this.x = x;
        this.c = c;
        this.n = n;
        this.t = t;
    }

    public Color getColor() {
        return c;
    }

    public Direction getN(){
        return n;
    }
}
