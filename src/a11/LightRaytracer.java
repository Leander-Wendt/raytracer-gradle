package a11;

import a03.Camera;
import a03.Ray;
import a10.DiffuseMaterial;
import a10.Hit;
import a10.TransformationGroup;
import cgtools.Color;
import cgtools.Matrix;
import cgtools.Sampler;
import cgtools.Vector;

public class LightRaytracer implements Sampler {
    TransformationGroup scene;
    Camera cam;
    int depth;
    World world;

    public LightRaytracer(double w, double h, int depth, World world){
        Matrix v = Matrix.rotation(Vector.yAxis, 0);
        cam = new Camera(Math.PI / 2, w, h, v);
        this.scene = world.getScene();
        this.depth = depth;
        this.world = world;
    }
    
    public void changeCamera (Camera cam) {
        this.cam = cam;
    }

    public void changeWorld (World world) {
        this.world = world;
        this.scene = world.getScene();
    }

    public void moveCamera(Matrix v){
        cam.setV(v);
    }

    public Camera getCamera(){
        return this.cam;
    }

    @Override
    public Color getColor(double x, double y) {
        return calcRadiance(scene, cam.shootRay(x, y), depth);
    }

    private Color calcRadiance(TransformationGroup s, Ray r, int depth) {
        if (depth == 0) {
            return Color.black;
        }

        Hit h = s.intersect(r);
        Ray scatRay = h.getMaterial().scatteringRay(r, h);
        Color lightCol = Color.black;

        if (h.getMaterial() instanceof DiffuseMaterial){
            for (Light l : world.getLights()){
                lightCol = Color.add(lightCol, l.incomingIntensity(h.x, h.getN(), scene));
            }
            lightCol = Color.multiply(lightCol, h.getMaterial().albedo(r, h));
        }

        if (scatRay != null){
            Color temp = Color.multiply(h.getMaterial().albedo(r, h), calcRadiance(s, scatRay, depth - 1));
            return Color.add(h.getMaterial().emission(r, h), temp, lightCol);
        }
        return h.getMaterial().emission(r, h);
    }
    
}



