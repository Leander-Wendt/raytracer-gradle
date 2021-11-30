package a03;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public class Camera {
    double angle, w, h;
    Point origin;
    Matrix v;

    public Camera (double a, double w, double h, Matrix v) {
        this.angle = a;
        this.w = w;
        this.h = h;
        origin = Vector.point(0, 0, 0);
        this.v = v;
    }

    public void setOrigin(Point origin){
        this.origin = origin;
    }

    public void setV(Matrix v){
        this.v = v;
    }

    public Ray shootRay(double x, double y) {
        double xr = x - (w / 2);
        double yr = (h / 2) - y;
        double zr = -1 * ((w / 2) / Math.round(Math.tan(angle / 2)));

        Direction dir = Vector.direction(xr, yr, zr);
        dir = Vector.normalize(dir);        
        dir = Matrix.multiply(v, dir);
        Point ursprung = Matrix.multiply(v, origin);
        return new Ray(ursprung, dir, 0, Double.POSITIVE_INFINITY);
    }
}
