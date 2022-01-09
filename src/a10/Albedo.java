package a10;

import cgtools.Color;
import cgtools.Sampler;

public class Albedo implements Sampler {
    Color albedo;

    Albedo (Color albedo){
        this.albedo = albedo;
    }

    @Override
    public Color getColor(double u, double v) {
        return albedo;
    }
}
