package a11;

import a03.Ray;
import a10.Shape;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Pointlight implements Light {

    public final Point p;
    public final Color intensity;

    public Pointlight (Point p, Color intensity){
        this.p = p;
        this.intensity = intensity;
    }

    @Override
    public Color incomingIntensity(Point hit, Direction normal, Shape scene) {
        Direction d = Vector.subtract(p, hit);
        Direction dNormalized = Vector.normalize(d);

        Ray shadowRay = new Ray (hit, dNormalized, 0.0001, Vector.length(d));
        if (scene.intersect(shadowRay) != null) {
            return Color.black;
        }

        Color radiance = Color.multiply(Color.divide(intensity, Math.pow(Vector.length(d),2)), Vector.dotProduct(dNormalized, normal));
        return radiance;
    }
    
}
