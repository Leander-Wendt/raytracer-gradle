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
    public Double n1;
    public Double n2;

    public Glass(Color mC, Boolean water){
        this.albedo = mC;
        this.emission = Color.black;
        if (water) {
            this.n2 = 1.3;
        } else {
            this.n2 = 1.5;            
        }
        this.n1 = 1.0;
    }


    // if bug, use n1, n2 as local variable 
    @Override
    public Ray scatteringRay(Ray r, Hit h) {
        Direction n = h.getN();
        if (Vector.dotProduct(r.d, n) > 0) {
            n = Vector.negate(n);
            Double temp = n1;
            n1 = n2;
            n2 = temp;
        }

        Direction re = refract(r.d, n, n1, n2);
        if (re != null){
            if (Random.random() > schlick(r.d, n, n1, n2)) {
                return new Ray(h.x, re, 0, Double.POSITIVE_INFINITY);
            } else {                
                return new Ray(h.x, reflect(r.d, n), 0.0001, Double.POSITIVE_INFINITY);
            }
        } else {
            return new Ray(h.x, reflect(r.d, n), 0.0001, Double.POSITIVE_INFINITY);
        }        
    }

    public Direction reflect(Direction d, Direction n) {
        Double a = Vector.dotProduct(n, d);
        Direction b = Vector.multiply(n, 2 * a);
        return Vector.subtract(d, b);
    }
    
    public Direction refract(Direction d, Direction n, Double n1, Double n2) {
        double r1 = n1 / n2;
        double c = Vector.dotProduct(Vector.negate(n), d);
        return Vector.add(Vector.multiply(r1, d), Vector.multiply((r1 * c - Math.sqrt(1 - (r1 * r1) * (1 - c * c))), n));
    }

    public double schlick(Direction d, Direction n, Double n1, Double n2) {
        double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);        
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
