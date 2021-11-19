package a04;

import a03.Camera;
import a03.Hit;
import cgtools.Color;
import cgtools.Sampler;

public class Raytracer_2 implements Sampler{
    Group scene;
    Camera cam;

    public Raytracer_2 (double width, double height, Group scene) {
        cam = new Camera(Math.PI / 3, width, height);
        this.scene = scene;        
    }

    public Color getColor(double x, double y) {
        Hit h = scene.intersect(cam.shootRay(x, y));
        if (h.t == Double.POSITIVE_INFINITY){
            return h.getColor();
        }
        return Color.shade(h.getN(), h.getColor());
    }

    public void setScene(Group scene) {
        this.scene = scene;
    }
    
}
