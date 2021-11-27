package a03;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Camera {
    double angle, w, h;
    Point origin;
    boolean turn;

    public Camera (double a, double w, double h) {
        this.angle = a;
        this.w = w;
        this.h = h;
        origin = Vector.point(0, 0, 0);
        this.turn = false;
    }

    public void setOrigin(Point origin){
        this.origin = origin;
    }

    public void turn(){
        this.turn = !turn;
    }

    public Ray shootRay(double x, double y) {
        double xr = x - (w / 2);
        double yr = (h / 2) - y;
        double zr = -1 * ((w / 2) / Math.round(Math.tan(angle / 2)));

        if (turn) {
            zr = zr * -1;
        }
        Direction v = Vector.direction(xr, yr, zr);
        v = Vector.normalize(v);
        return new Ray(origin, v, 0, Double.POSITIVE_INFINITY);
    }
}
