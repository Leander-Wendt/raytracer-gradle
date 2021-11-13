package a04;

import a03.Sphere;
import cgg.Image;
import cgtools.Color;
import cgtools.Vector;

public class Main {
  public static void main(String[] args) {
    final double width = 480;
    final double height = 270;
    final int ABTASTUNGEN_PRO_PIXEL = 100;

    Group scene_1 = new Group();
    Group scene_2 = new Group();

    Shape background = new Background(Color.darkgrey);
    Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), Color.gray, Double.POSITIVE_INFINITY);
    Shape globe1 = new Sphere(Vector.point(-1, -0.25, -2.5), 0.7, Color.red);
    Shape globe2 = new Sphere(Vector.point(0, -0.25, -2.5), 0.6, Color.green);
    Shape globe3 = new Sphere(Vector.point(1, -0.25, -2.5), 0.7, Color.blue);
    // Background must be first in List   
    scene_1.add(background);     
    scene_1.add(ground);
    scene_1.add(globe1);        
    scene_1.add(globe3);    
    scene_1.add(globe2);

    Shape sky = new Background(new Color(0.1, 0.1, 0.2));
    Shape ocean = new Plane(Vector.point(0.0, -0.49, 0.0), Vector.direction(0, 0.1, 0), Color.blue, Double.POSITIVE_INFINITY);
    Shape boat = new Plane(Vector.point(-1, -0.4, 0.0), Vector.direction(0, 0.2, 0), new Color(0.9, 0.45, 0.1), 2);
    Shape island = new Sphere(Vector.point(0.9, -1, -1), 1, new Color (1, 1, 0.5));
    Shape moon = new Sphere(Vector.point(-8, 2, -12), 1, new Color (1, 1, 0.8)); 
    Shape moon2 = new Sphere(Vector.point(-10, 7, -20), 1, new Color (1, 0.4, 0.4)); 
    // Background must be first in List
    scene_2.add(sky);
    scene_2.add(ocean);        
    scene_2.add(island);
    scene_2.add(boat);    
    scene_2.add(moon);
    scene_2.add(moon2);

    
    // This class instance defines the contents of the image.
    Raytracer_2 content = new Raytracer_2(width, height, scene_1);
    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image((int) width, (int) height);
    image.superSample(content, ABTASTUNGEN_PRO_PIXEL);

    // Write the image to disk.
    final String filename = "doc/a04-three-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);

    content.setScene(scene_2);

    image = new Image((int) width, (int) height);
    image.superSample(content, ABTASTUNGEN_PRO_PIXEL);

    // Write the image to disk.
    final String filename2 = "doc/a04-scene.png";
    image.write(filename2);
    System.out.println("Wrote image: " + filename2);
  }
}

