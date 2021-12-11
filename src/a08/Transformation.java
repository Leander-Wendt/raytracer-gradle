package a08;

import a03.Hit;
import a03.Ray;
import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

public class Transformation {
    private Matrix matOut;
    private Matrix matOutN;
    private Matrix matIn;

    public Transformation(Matrix m){
        this.matOut = m;
        this.matIn = Matrix.invert(m);
        this.matOutN = Matrix.transpose(matIn);
    }

    public Ray transformRay(Ray r){
        Point transX0 = Matrix.multiply(matIn, r.x0);
        Direction transD = Matrix.multiply(matIn, r.d);
        return new Ray(transX0, transD, r.tmin, r.tmax);
    }

    public Hit transformHit(Hit h){
        if (h != null) {
            Point transX = Matrix.multiply(matOut, h.x);
            Direction transN = Matrix.multiply(matOutN, h.getN());
            return new Hit(transX, h.t, h.getColor(), transN, h.getMaterial());
        }
        return h;
    }
}

