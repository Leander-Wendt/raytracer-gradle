package a11;

import a03.Ray;
import a10.Shape;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Directlight implements Light {
    public final Direction dir;
    public final Color lightColor;

    public Directlight(Direction dir, Color lightColor){
        this.dir = Vector.normalize(dir);
        this.lightColor = lightColor;
    }

    @Override
    public Color incomingIntensity(Point hit, Direction normal, Shape scene) {
        
        Ray shadow = new Ray(hit, dir, 0.0001, Double.MAX_VALUE);
        if (scene.intersect(shadow) != null){
            return Color.black;
        }
        Color light = Color.multiply(lightColor, Vector.dotProduct(dir, normal));
        return light;
    }
}
