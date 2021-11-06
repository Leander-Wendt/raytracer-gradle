package a03;

import cgg.Image;

public class Main {
  public static void main(String[] args) {
    // coloredDisc Image
    final double width = 480;
    final double height = 270;
    final int ABTASTUNGEN_PRO_PIXEL = 100;
    // This class instance defines the contents of the image.
    Raytracer content = new Raytracer(width, height);

    // Camera cam = new Camera(Math.PI / 2, width, height);
    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image((int) width, (int) height);
    image.superSample(content, ABTASTUNGEN_PRO_PIXEL);

    // Write the image to disk.
    final String filename = "doc/a03-three-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}
