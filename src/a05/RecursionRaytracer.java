package a05;

import a03.Camera;
import a03.Hit;
import a03.Ray;
import a04.Group;
import cgtools.Color;
import cgtools.Sampler;

public class RecursionRaytracer implements Sampler {
    Group scene;
    Camera cam;
    int depth;

    public RecursionRaytracer(double w, double h, Group scene, int depth){
        cam = new Camera(Math.PI / 2, w, h);
        this.scene = scene;
        this.depth = depth;
    }
    
    public void changeCamera (Camera cam) {
        this.cam = cam;
    }

    public Camera getCamera(){
        return this.cam;
    }

    @Override
    public Color getColor(double x, double y) {
        return calcRadiance(scene, cam.shootRay(x, y), depth);
    }

    private Color calcRadiance(Group s, Ray r, int depth) {
        if (depth == 0) {
            return Color.black;
        }

        Hit h = s.intersect(r);
        Ray scatRay = h.getMaterial().scatteringRay(r, h);
        if (scatRay != null){
            Color temp = Color.multiply(h.getMaterial().albedo(r, h), calcRadiance(s, scatRay, depth - 1));
            return Color.add(h.getMaterial().emission(r, h), temp);
        }
        return h.getMaterial().emission(r, h);
    }
    
}

