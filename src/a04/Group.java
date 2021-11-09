package a04;

import java.util.ArrayList;

import a03.Hit;
import a03.Ray;

public class Group implements Shape {
    public ArrayList<Shape> shapes = new ArrayList<>();

    public Group () {

    }

    public void add(Shape s){
        shapes.add(s);
    }
    
    @Override
    public Hit intersect(Ray r) {
        Hit result = null;
        for (Shape s : shapes){
            Hit h = s.intersect(r);
            if (h != null && result.t > h.t){
                result = h;
            }
        }
        return result;
    }
}
