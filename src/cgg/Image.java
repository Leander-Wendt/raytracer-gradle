  package cgg;

import cgtools.*;

public class Image {
  private int width;
  private int height;
  private double[] img;
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    img = new double[width * height * 3];    
  }

  public void setPixel(int x, int y, Color color) {
    int i = (((y * width) + x) * 3);
    img[i] = color.r;
    img[i + 1] = color.g;
    img[i + 2] = color.b;

  }

  public void write(String filename) {
    ImageWriter.write(filename, img, width, height);
  }

  /*public void sample(Sampler s) {
    notYetImplemented();
  }

  private void notYetImplemented() {
    System.err.println("Please complete the implementation of class cgg.Image as part of assignment 1.");
    System.exit(1);
  }*/
}
