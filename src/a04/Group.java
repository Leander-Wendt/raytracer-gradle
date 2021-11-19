package a04;

import java.util.ArrayList;
import java.util.List;

import a03.Hit;
import a03.Ray;

public class Group implements Shape {
    private List<Shape> shapes;

    public Group (Shape bg) {
        shapes = new ArrayList<>();
        shapes.add(bg);
    }

    public void add(Shape s){
        shapes.add(s);
    }
    
    @Override
    public Hit intersect(Ray r) {
        Hit result = shapes.get(0).intersect(r);
        for (Shape s : shapes){
            Hit h = s.intersect(r);
            if (h != null && result.t > h.t){
                result = h;
            }
        }
        return result;
    }
}
