package a08;

import java.util.ArrayList;
import java.util.List;

import a03.Hit;
import a03.Ray;
import a04.Shape;
import cgtools.Matrix;

public class TransformationGroup implements Shape {
    private List<Shape> shapes;
    private Transformation trans;

    public TransformationGroup (Shape bg) {
        this.shapes = new ArrayList<>();
        this.shapes.add(bg);
        this.trans = new Transformation(Matrix.identity);
    }

    public void add(Shape s){
        shapes.add(s);
    }
    
    @Override
    public Hit intersect(Ray r) {
        Ray tr = trans.transformRay(r);
        Hit h = null;
        for (Shape s : shapes){
            Hit tempHit = s.intersect(tr);
            if (tempHit != null && (h == null || tempHit.t < h.t)){
                h = tempHit;
            }
        }
        if (h == null){
            return h;
        }
        return trans.transformHit(h);
    }
}
