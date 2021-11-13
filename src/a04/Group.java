package a04;

import java.util.ArrayList;
import java.util.List;

import a03.Hit;
import a03.Ray;
import cgtools.Color;
import cgtools.Vector;

public class Group implements Shape {
    private List<Shape> shapes;

    public Group () {
        shapes = new ArrayList<>();
    }

    public void add(Shape s){
        shapes.add(s);
    }
    
    @Override
    // Rekursiv implementieren
    public Hit intersect(Ray r) {
        Hit result = shapes.get(0).intersect(r);//new Hit(Vector.point(0, 0, 0), Double.POSITIVE_INFINITY, Color.darkgrey, Vector.direction(Vector.zero));
        for (Shape s : shapes){
            Hit h = s.intersect(r);
            if (h != null && result.t > h.t){
                result = h;
            }
        }
        return result;
    }
}
