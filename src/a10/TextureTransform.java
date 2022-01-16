package a10;

import cgtools.Color;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

public class TextureTransform implements Sampler {
    Sampler source;
    Matrix transform;

    public TextureTransform(Sampler source, Matrix transform){
        this.source = source;
        this.transform = transform;
    }
    
    @Override
    public Color getColor(double u, double v) {
        Point uv = Vector.point(u, v, 0);
		uv = Matrix.multiply(transform, uv);
		return source.getColor(uv.x, uv.y);
    }    
}