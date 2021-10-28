package a02;

import static cgtools.Color.*;
import cgg.*;

public class Main {
  public static void main(String[] args) {
    // coloredDisc Image
    final int width = 480;
    final int height = 270;
    // This class instance defines the contents of the image.
    ColoredDiscs content = new ColoredDiscs(width, height, 50);

    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    image.sample(content);

    // Write the image to disk.
    final String filename = "doc/a02-discs.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}
