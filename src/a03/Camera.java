package a03;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

public class Camera implements Sampler {
    double angle, w, h;
    Point origin;

    Camera (double a, double w, double h) {
        this.angle = a;
        this.w = w;
        this.h = h;
        origin = Vector.point(0, 0, 0);
    }

    public Ray shootRay(double x, double y) {
        double xr = x - (w / 2);
        double yr = (h / 2) - y;
        double zr = -1 * ((w / 2) / Math.round(Math.tan(angle / 2)));

        Direction v = Vector.direction(xr, yr, zr);
        v = Vector.normalize(v);
        return new Ray(origin, v, 0, 100);
    }

    @Override
    public Color getColor(double x, double y) {
        Hit h = Sphere.intersect(shootRay(x, y));
        return shade (h.n, h.c);
    }
    static Color shade(Direction normal, Color color) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Color.multiply(0.1, color);
        Color diffuse = Color.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
        return Color.add(ambient, diffuse);
    }
}
