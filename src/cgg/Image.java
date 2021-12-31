package cgg;

import java.util.ArrayList;
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

  public void superSample(Sampler s, int abtastungen) {
    int cores = 10; // Runtime.getRuntime().availableProcessors();
    System.out.println("Using " + cores + " cores. " + "Available cores: " + Runtime.getRuntime().availableProcessors());
    try {
      superSampleHelper(s, abtastungen);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    //ExecutorService pool = Executors.newFixedThreadPool(cores);
    
    /*int n = 10;
    ArrayList<Future<Integer>> values = new ArrayList<>();
    for (int i = 0; i < n; i++){
      final int j = i;
      values.add(pool.submit(() -> j * j));
    }*/
    /*Thread[] threads = new Thread[cores];

    for (int i = 0; i < cores; i++){
      final int core = i;
      threads[i] = new Thread(){
        public void run() {
          superSampleHelper(s, abtastungen);
        }
      };
      threads[i].start();
    }

    for (int i = 0; i < cores; i++){
      try{
      threads[i].join();
      }
      catch (Exception e){
        System.out.println("Fehler beim zusammenfügen der Threads.");
      }
    }*/   
  }

  private void superSampleHelper(Sampler s, int abtastungen) throws InterruptedException, ExecutionException {
    for (int x = 0; x < width; x++) {      
      for (int y = 0; y < height; y++) {
        int n = PolkaDots.getDivider(abtastungen);
        final int fx = x;
        final int fy = y;
        Callable<Color> calculateOnePixel = new Callable<Color>() {
          public Color call() {
            Color farbe = s.getColor(fx, fy);
            for (int xi = 0; xi < n; xi++){
              for (int yi = 0; yi < n; yi++){
                double rx = Random.random();
                double ry = Random.random();
                double xs = fx + (xi + rx) / n;
                double ys = fy + (yi + ry) / n;
                //Color farbe = pixel.get();
                farbe = Color.add(farbe, s.getColor(xs, ys));
              }
            }
            return Color.divide(farbe, 100);
          }
        };
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Color> pixel = pool.submit(calculateOnePixel);
        Color farbe = pixel.get();
        //Color farbe = s.getColor(x, y);        
        setPixel(x, y, farbe);
        pool.shutdown();
      } 
    }
  }
}
