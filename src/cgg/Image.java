  package cgg;

import cgg.a01.PolkaDots;
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
    img[i] = Math.pow(color.r, (1 / 2.2));
    img[i + 1] = Math.pow(color.g, (1 / 2.2));
    img[i + 2] = Math.pow(color.b, (1 / 2.2));
    //img[i] = color.r;
    //img[i+1] = color.g;
    //img[i+2] = color.b;
  }

  public void write(String filename) {
    ImageWriter.write(filename, img, width, height);
  }

  public void sample(Sampler s) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        setPixel(x, y, s.getColor(x, y));
      }
    }
  }

  public void superSample(Sampler s, int abtastungen) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color temp = s.getColor(x, y);
        if(!temp.equals(new Color(0, 0, 0))){
          // Abtastungen aufteilen in ein gleichmäßiges Raster
          int n = PolkaDots.getDivider(abtastungen);
          int counter = 0;
          Color farbe = temp;
          for (int xi = 0; xi < n; xi++){
            for (int yi = 0; yi < n; yi++){
              double rx = Random.random();
              double ry = Random.random();
              double xs = x + (xi + rx) / n;
              double ys = y + (yi + ry) / n;
              farbe = Color.add(farbe, s.getColor(xs, ys));
            }
          }
          farbe = Color.divide(farbe, 100);
          setPixel(x, y, farbe);
        } else {
          setPixel(x, y, temp);
        }
      }
    }
  }
}
