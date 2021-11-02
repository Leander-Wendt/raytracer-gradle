package a03;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Camera {
    double angle, w, h;
    Point origin;

    Camera (double a, double w, double h) {
        this.angle = a;
        this.w = w;
        this.h = h;
        origin = Vector.point(0, 0, 0);
    }

    public Ray shootRay(double x, double y) {
        Direction v = Vector.direction(x, y, - 1 / Math.pow(3, 1 / 2));
        //v = Vector.normalize(v);
        return new Ray(origin, v, 0, 100);
    }
}
