package a05;

import a03.Sphere;
import a04.Background;
import a04.Group;
import a04.Plane;
import a04.Shape;
import cgg.Image;
import cgtools.Color;
import cgtools.Vector;

public class Main {
  public static void main(String[] args) {
    final double width = 480;
    final double height = 270;
    final int ABTASTUNGEN_PRO_PIXEL = 100;
    

    Shape background = new Background(Color.white);
    Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), Double.POSITIVE_INFINITY, new DiffuseMaterial(Color.darkgrey));
    Shape sphere1 = new Sphere(Vector.point(-1.0, -0.25, -1.5), 0.7, new DiffuseMaterial(Color.yellow));
    Shape sphere2 = new Sphere(Vector.point(0.0, -0.25, -1.7), 0.3, new DiffuseMaterial(Color.cyan));
    Shape sphere3 = new Sphere(Vector.point(1.0, -0.25, -1.5), 0.7, new DiffuseMaterial(Color.violet));
    Shape sphere4 = new Sphere(Vector.point(0.1, -0.5, -1.465), 0.05, new DiffuseMaterial(Color.red));
    Shape sphere5 = new Sphere(Vector.point(-0.1, -0.5, -1.465), 0.05, new DiffuseMaterial(Color.gray));
    Group scene_1 = new Group(background);
    scene_1.add(ground);
    scene_1.add(sphere1);        
    scene_1.add(sphere2);    
    scene_1.add(sphere3);
    scene_1.add(sphere4); 
    scene_1.add(sphere5);  
    
    // This class instance defines the contents of the image.
    RecursionRaytracer content = new RecursionRaytracer(width, height, scene_1, 100);
    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image((int) width, (int) height);
    image.superSample(content, ABTASTUNGEN_PRO_PIXEL);

    // Write the image to disk.
    System.out.println("Starting to write the Image!");
    final String filename = "doc/a05-diffuse-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}
