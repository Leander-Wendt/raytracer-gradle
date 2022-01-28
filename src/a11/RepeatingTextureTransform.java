package a11;

import cgtools.Color;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

public class RepeatingTextureTransform implements Sampler {
    Sampler source;
    Matrix transform;

    public RepeatingTextureTransform(Sampler source, Matrix transform){
        this.source = source;
        this.transform = transform;
    }
    
    @Override
    public Color getColor(double u, double v) {
        Point uv = Vector.point(u, v, 0);
		uv = Matrix.multiply(transform, uv);
        double ui = uv.x - Math.floor(uv.x);
        double vi = uv.y - Math.floor(uv.y);
		return source.getColor(ui, vi);
    }    
}
