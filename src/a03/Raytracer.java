package a03;

import java.util.ArrayList;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Sampler;
import cgtools.Vector;

public class Raytracer implements Sampler {
    ArrayList<Sphere> scene;
    Camera cam;

    public Raytracer(double w, double h) {
        cam = new Camera(Math.PI / 2, w, h);
        scene = new ArrayList<>();
        scene.add(new Sphere(Vector.point(-50, 0, -100), 25, new Color(1, 0, 1)));
        scene.add(new Sphere(Vector.point(0, 0, -100), 25, new Color(0, 0.5, 0)));
        scene.add(new Sphere(Vector.point(50, 0, -100), 25, new Color(0.5, 0, 0)));
    }

    @Override
    public Color getColor(double x, double y) {
        for (int i = 0; i < scene.size(); i++){
            Sphere s = scene.get(i);
            Hit h = s.intersect(cam.shootRay(x, y));
            if (h != null) {
                return shade(h.getN(), h.getColor());
            }
        }
        return new Color(0.1, 0.1, 0.1);
    }

    static Color shade(Direction normal, Color color) {
        Direction lightDir = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Color.multiply(0.1, color);
        Color diffuse = Color.multiply(0.9 * Math.max(0, Vector.dotProduct(lightDir, normal)), color);
        return Color.add(ambient, diffuse);
    }
    
}
