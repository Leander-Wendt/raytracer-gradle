package a03;

import cgtools.Color;
import cgtools.Vector;

public class Main {
  public static void main(String[] args) {
    // coloredDisc Image
    final double width = 480;
    final double height = 270;
    // final int ABTASTUNGEN_PRO_PIXEL = 100;
    // This class instance defines the contents of the image.
    // ColoredDiscs content = new ColoredDiscs(width, height, 50);

    // 3.2 Tests:
    // 1 / root(3) ~ 1.73
    // Camera cam = new Camera(Math.PI / 2, width, height);
    Sphere s = new Sphere(Vector.point(0, 0, -2), 1, new Color(0,0,0));
    Ray r = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 0, -1), 0, Double.POSITIVE_INFINITY);
    Hit result = s.intersect(r);
    System.out.println("Erwartet: 0, 0, -1");
    System.out.println("Erwartet: 0, 0, 1");
    System.out.println(result.x);
    System.out.println(result.n);

    s = new Sphere(Vector.point(0, -1, -2), 1, new Color(0, 0, 0));
    r = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 0, -1), 0, Double.POSITIVE_INFINITY);
    result = s.intersect(r);
    System.out.println("Erwartet: 0, 0, -2");
    System.out.println("Erwartet: 0, 1, 0");
    System.out.println(result.x);
    System.out.println(result.n);

    s = new Sphere(Vector.point(0, 0, -2), 1, new Color(0, 0, 0));
    r = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 1, -1), 0, Double.POSITIVE_INFINITY);
    result = s.intersect(r);
    System.out.println("Erwartet: NULL");
    System.out.println("Erwartet: NULL");
    System.out.println(result);
    System.out.println(result);

    s = new Sphere(Vector.point(0, 0, -2), 1, new Color(0, 0, 0));
    r = new Ray(Vector.point(0, 0, -4), Vector.direction(0, 0, -1), 0, Double.POSITIVE_INFINITY);
    result = s.intersect(r);
    System.out.println("Erwartet: NULL");
    System.out.println("Erwartet: NULL");
    System.out.println(result);
    System.out.println(result);

    s = new Sphere(Vector.point(0, 0, -4), 1, new Color(0, 0, 0));
    r = new Ray(Vector.point(0, 0, 0), Vector.direction(0, 0, -1), 0, 2);
    result = s.intersect(r);
    System.out.println("Erwartet: NULL");
    System.out.println("Erwartet: NULL");
    System.out.println(result);
    System.out.println(result);
    
    // Creates an image and iterates over all pixel positions inside the image.
    /*Image image = new Image(width, height);
    image.sample(content);

    // Write the image to disk.
    final String filename = "doc/a03-three-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);*/
  }
}
