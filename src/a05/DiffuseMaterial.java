package a05;

import a03.Hit;
import a03.Ray;
import cgtools.Color;
import cgtools.Vector;

public class DiffuseMaterial implements Material {
    public Color albedo;
    public Color emission;

    public DiffuseMaterial(Color mC){
        this.albedo = mC;
        this.emission = Color.black;
    }

    @Override
    public Ray scatteringRay(Ray scatR, Hit h) {
        return new Ray(h.x, Vector.add(h.getN(), Vector.rdmDir()), 0.000001, Double.POSITIVE_INFINITY);
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return emission;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo;
    }    
}
