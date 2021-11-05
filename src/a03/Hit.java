package a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Hit {
    Point x;
    double t;
    Direction n;
    Color c;
    
    public Hit(Point x, double t, Color c, Direction n) {
        this.x = x;
        this.c = c;
        this.n = n;
        this.t = t;
    }
}
