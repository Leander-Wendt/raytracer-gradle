package a10;

import a03.Ray;
import cgtools.Color;
import cgtools.Sampler;

public class BackMat implements Material {

    public Sampler albedo;
    public Sampler emission;

    public BackMat(Color c){
        this.albedo = new Albedo(Color.black);
        this.emission = new Emission(c);
    }

    public BackMat(Sampler c){
        this.albedo = new Albedo(Color.black);
        this.emission = c;
    }

    @Override
    public Ray scatteringRay(Ray scatR, Hit h) {
        return null;
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
