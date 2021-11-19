package a05;

import a03.Hit;
import a03.Ray;
import cgtools.Color;

public class BackMat implements Material {

    public Color albedo;
    public Color emission;

    public BackMat(Color c){
        this.albedo = Color.black;
        this.emission = c;
    }

    @Override
    public Ray scatteringRay(Ray scatR, Hit h) {
        return null;
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return albedo;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return emission;
    }
    
}
