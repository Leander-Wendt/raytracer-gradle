package a11;

import a03.Ray;
import a10.*;
import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public class RepeatingMat implements Material {
    Sampler albedo;
    Sampler emission;

    public RepeatingMat(Color mC){
        this.albedo = new Constant(mC);
        this.emission = new Constant(Color.black);
    }

    public RepeatingMat(Sampler s){
        this.albedo = s;
        this.emission = new Constant(Color.black);
    }

    @Override
    public Ray scatteringRay(Ray scatR, Hit h) {
        return new Ray(h.x, Vector.add(h.getN(), Vector.rdmDir()), 0.00001, Double.POSITIVE_INFINITY);
    }

    @Override
    public Color emission(Ray r, Hit h) {     
        double u = h.getU() - Math.floor(h.getU());
        double v = h.getV() - Math.floor(h.getV());   
        return emission.getColor(u, v);
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        double u = h.getU() - Math.floor(h.getU());
        double v = h.getV() - Math.floor(h.getV());
        return albedo.getColor(u, v);
    }    
}

