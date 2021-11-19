package a03;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Ray {
    public Point x0;
    public double tmin, tmax;
    public Direction d;

    public Ray (Point x0, Direction d, double tmin, double tmax){
        this.x0 = x0;
        this.d = d;
        this.tmin = tmin;
        this.tmax = tmax;
    }
    
    public Point pointAt(double t){
        Direction temp = Vector.multiply(d, t);
        return Vector.add(temp, x0);
    }

    public Boolean isValid (double t){
        return tmin <= t && tmax >= t;
    }
}
