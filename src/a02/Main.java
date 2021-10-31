package a02;

import cgg.*;

public class Main {
  public static void main(String[] args) {
    // coloredDisc Image
    final int width = 480;
    final int height = 270;
    final int ABTASTUNGEN_PRO_PIXEL = 100;
    // This class instance defines the contents of the image.
    ColoredDiscs content = new ColoredDiscs(width, height, 50);

    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    image.sample(content);

    // Write the image to disk.
    final String filename = "doc/a02-discs.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);

    ColoredDiscs content2 = new ColoredDiscs(width, height, 50);
    Image image2 = new Image(width, height);
    image2.superSample(content2, ABTASTUNGEN_PRO_PIXEL);

    // Write the image to disk.
    final String filename2 = "doc/a02-discs-supersampling.png";
    image2.write(filename2);
    System.out.println("Wrote image: " + filename2);
  }
}
