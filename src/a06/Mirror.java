package a06;

import a03.Hit;
import a03.Ray;
import a05.Material;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Vector;

public class Mirror implements Material {
    public Color albedo;
    public Color emission;
    public Boolean matt;
    public Double rauigKeit;

    public Mirror(Color mC, Boolean matt, Double rauigKeit){
        this.albedo = mC;
        this.emission = Color.black;
        this.matt = matt;
        this.rauigKeit = rauigKeit;
    }

    @Override
    public Ray scatteringRay(Ray r, Hit h) {
        Direction mirroredR = Vector.subtract(r.d, Vector.multiply(h.getN(), 2 * Vector.dotProduct(h.getN(), r.d))); 
        if (matt) {
            mirroredR = Vector.add(mirroredR, Vector.multiply(Vector.rdmDir(), rauigKeit));
        }
        return new Ray(h.x, mirroredR, 0.00001, Double.POSITIVE_INFINITY);
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
