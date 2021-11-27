package a06;

import a03.Hit;
import a03.Ray;
import a05.Material;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class Glass implements Material {
    public Color albedo;
    public Color emission;
    public Double n3;
    public Double n4;

    public Glass(Color mC, Boolean water){
        this.albedo = mC;
        this.emission = Color.black;
        if (water) {
            this.n4 = 1.3;
        } else {
            this.n4 = 1.5;            
        }
        this.n3 = 1.0;
    }


    // if bug, use n1, n2 as local variable 
    @Override
    public Ray scatteringRay(Ray r, Hit h) {
        Double n1 = this.n3; 
        Double n2 = this.n4;
        Direction n = h.getN();
        Direction d = r.d;
        Direction temp;
        if (Vector.dotProduct(n, d) > 0) {
            n = Vector.negate(n);
            Double temp2 = n1;
            n1 = n2;
            n2 = temp2;
        }
        if (refract(d, n, n1, n2) == null) {
            temp = reflect(d, n);
        } else {
            if (Random.random() > schlick(d, n, n1, n2)) {
                temp = refract(d, n, n1, n2);
            } else {
                temp = reflect(d, n);
            }
        }        
        return new Ray(h.x, temp, 0.000000001, Double.POSITIVE_INFINITY);
    }

    public Direction reflect(Direction d, Direction n) {
        Double a = 2 * Vector.dotProduct(n, d);
        Direction b = Vector.multiply(n, a);
        return Vector.subtract(d, b);
    }
    
    public Direction refract(Direction d, Direction n, Double n1, Double n2) {
        double r1 = n1 / n2;
        double c = (Vector.dotProduct(n, d));
        double discr = 1 - (r1 * r1) * (1 - c * c);
        return Vector.add(Vector.negate(Vector.multiply(r1, d)), Vector.multiply((r1 * c - Math.sqrt(discr)), n));
    }

    public double schlick(Direction d, Direction n, Double n1, Double n2) {
        double r0 = (n1 - n2) / (n1 + n2);
        r0 = Math.pow(r0, 2);        
        return r0 + (1 - r0) * Math.pow(1 + Vector.dotProduct(d, n), 5);
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return emission;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo;
    }    
}
