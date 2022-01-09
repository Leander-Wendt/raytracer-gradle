package a10;

import cgtools.Color;
import cgtools.Sampler;

public class Emission implements Sampler {
    Color emission;

    Emission (Color emission){
        this.emission = emission;
    }

    @Override
    public Color getColor(double u, double v) {
        return emission;
    }
}