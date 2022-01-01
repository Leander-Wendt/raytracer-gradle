package cgg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

  public void superSample(Sampler s, int abtastungen, int cores) {
    try {

      System.out.println("Using " + cores + " cores. " + "Available cores: " + Runtime.getRuntime().availableProcessors());    
      ExecutorService pool = Executors.newFixedThreadPool(cores);
      List farben = new ArrayList<Future<Color>>();

      for (int x = 0; x < width; x++) {      
        for (int y = 0; y < height; y++) {
          int n = PolkaDots.getDivider(abtastungen);
          final int fx = x;
          final int fy = y;

          Callable<Color> calculateOnePixel = new Callable<Color>() {
          public Color call() {
            Color farbe = new Color(0, 0, 0);
            for (int xi = 0; xi < n; xi++){
              for (int yi = 0; yi < n; yi++){
                double rx = Random.random();
                double ry = Random.random();
                double xs = fx + (xi + rx) / n;
                double ys = fy + (yi + ry) / n;
                farbe = Color.add(farbe, s.getColor(xs, ys));
              }
            }
            return Color.divide(farbe, n * n);
          }
        };

        Future<Color> pixel = pool.submit(calculateOnePixel);
        farben.add(pixel);     
        } 
      }

      for (int x = 0; x < width; x++) {
        int offset = x * height;
        for (int y = 0; y < height; y++) {
          int index = offset + y;
          Future<Color> pixel = (Future<Color>) farben.get(index);
          Color color = pixel.get();
          setPixel(x, y, color);
        }
      }
      pool.shutdown();
    } 
    
    catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }   
  }
}
