package a02;

import static cgtools.Color.*;
import cgg.*;
import cgtools.Color;

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

    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // Hier kommt das Supersampling rein
        if (!black.equals(getPixel(x,y))){

        }
        int counter = 0;
        int result = ABTASTUNGEN_PRO_PIXEL / counter;
        Color.multiply(Color, result);

      }
    }
  }
}
