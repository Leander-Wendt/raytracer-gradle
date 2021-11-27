package a06;

import a03.Sphere;
import a04.Background;
import a04.Group;
import a04.Plane;
import a04.Shape;
import a05.DiffuseMaterial;
import a05.RecursionRaytracer;
import cgg.Image;
import cgtools.Color;
import cgtools.Vector;

public class Main {
    public static void main(String[] args) {
      final double width = 480;
      final double height = 270;
      final int ABTASTUNGEN_PRO_PIXEL = 100;
      
  
      Shape background = new Background(Color.white);
      Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), Double.POSITIVE_INFINITY, new DiffuseMaterial(Color.lightgrey));
      Shape sphere1 = new Sphere(Vector.point(0.0, 0.5, -2.0), 0.5, new Glass(Color.white, false));
      Shape sphere2 = new Sphere(Vector.point(0.0, 0.0, 12.0), 0.5, Color.cyan);
      Shape sphere3 = new Sphere(Vector.point(0.0, 0.0, 2.0), 1.0, new DiffuseMaterial(Color.green));
      Shape sphere4 = new Sphere(Vector.point(-1.0, 0.0, -4.0), 1.0, new DiffuseMaterial(Color.violet));
      Group scene1 = new Group(background);
      scene1.add(ground);
      scene1.add(sphere1);
      scene1.add(sphere2); 
      scene1.add(sphere4);         
      scene1.add(sphere2);    
      scene1.add(sphere3);
      
      
      // This class instance defines the contents of the image.
      RecursionRaytracer content = new RecursionRaytracer(width, height, scene1, 100);
      // Creates an image and iterates over all pixel positions inside the image.
      Image image = new Image((int) width, (int) height);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL);
  
      // Write the image to disk.
      final String filename = "doc/a06-mirrors-glass-1.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);


      
      // Tests:
      /*
      Glass mat = new Glass(Color.white, true);
      Direction d0 = Vector.direction(0.0, 0.0, 0.0);
      Direction d1 = Vector.direction(0.707, -0.707, 0.0);
      Direction d2 = Vector.direction(0.707, 0.707, 0.0);
      Direction d3 = Vector.direction(0.995, -0.1, 0.0);
      Direction d4 = Vector.direction(-0.1, 0.995, 0.0);
    
      Direction n0 = Vector.direction(0.0, 1.0, 0.0);

      System.out.println("Tests:");

      System.out.println(mat.reflect(d0, n0));
      System.out.println(mat.reflect(d1, n0));
      System.out.println(mat.reflect(d2, n0));
      System.out.println("////////////");
      System.out.println(mat.schlick(d2, n0, 1.0, 1.5));
      System.out.println(mat.schlick(d2, n0, 1.5, 1.0));
      System.out.println(mat.schlick(d3, n0, 1.0, 1.5));
      System.out.println(mat.schlick(d3, n0, 1.5, 1.0));
      System.out.println("////////////");
      System.out.println(mat.refract(d2, n0, 1.0, 1.5));
      System.out.println(mat.refract(d2, n0, 1.5, 1.0));
      System.out.println(mat.refract(d3, n0, 1.0, 1.5));
      System.out.println(mat.refract(d3, n0, 1.5, 1.0));
      System.out.println(mat.refract(d4, n0, 1.0, 1.5));
      System.out.println(mat.refract(d4, n0, 1.5, 1.0));*/
    }
}