package a03;

import cgtools.Color;
import cgtools.Point;
import cgtools.Vector;

public class Hit {
    Point x;
    double t;
    Vector n;
    Color c;
    
    Hit(Point x, double t, Color c, Vector n) {
        this.x = x;
        this.c = c;
        this.n = n;
        this.t = t;
    }
}
