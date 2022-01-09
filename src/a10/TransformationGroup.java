package a10;

import java.util.ArrayList;
import java.util.List;

import a03.Ray;
import cgtools.Matrix;

public class TransformationGroup implements Shape {
    private List<Shape> shapes;
    private Transformation trans;

    public TransformationGroup () {
        this.shapes = new ArrayList<>();
        this.trans = new Transformation(Matrix.identity);
    }

    public void add(Shape s){
        shapes.add(s);
    }

    public Transformation getTransformation(){
        return trans;
    }

    public void setTransformation(Transformation trans){
        this.trans = trans;
    }

    public List<Shape> getScene(){
        return shapes;
    }

    public void setScene(List<Shape> shapes){
        this.shapes = shapes;
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

