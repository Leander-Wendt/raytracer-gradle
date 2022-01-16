package a10;

import a03.Ray;
import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public class DiffuseMaterial implements Material {
    Sampler albedo;
    Sampler emission;

    public DiffuseMaterial(Color mC){
        this.albedo = new Constant(mC);
        this.emission = new Constant(Color.black);
    }

    public DiffuseMaterial(Sampler s){
        this.albedo = s;
        this.emission = new Constant(Color.black);
    }

    @Override
    public Ray scatteringRay(Ray scatR, Hit h) {
        return new Ray(h.x, Vector.add(h.getN(), Vector.rdmDir()), 0.00001, Double.POSITIVE_INFINITY);
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return emission.getColor(h.getU(), h.getV());
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo.getColor(h.getU(), h.getV());
    }    
}
