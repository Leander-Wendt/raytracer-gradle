package cgg.a01;

import cgtools.*;
import java.util.ArrayList;

import cgg.Image;

// Represents the contents of an image. Provides the same color for all pixels.
public class PolkaDots implements Sampler {
  Color bgColor, cColor;
  double cx, cy, r;
  int row, column, width, height;

  public PolkaDots(Color bgColor, Color cColor, int width, int height, double r, int amount) {
    this.bgColor = bgColor;
    this.cColor = cColor;
    this.width = width;
    this.height = height;
    this.r = r;
    row = getDivider(amount);
    column = amount / row;
    if (row > column) {
      int temp = row;
      row = column;
      column = temp;
    }
    cx = (width / column) / 2;
    cy = (height / row) / 2;
    genImage();        
  }

  public void genImage () {
    Image image = new Image(width, height);
    // walk trough a parcel of the whole image
    for (int x = 0; x != width / column; x++) {
      for (int y = 0; y != height / row; y++) {
        Color temp = getColor(x, y);
        // copy pixels on all of the parcels of the grid
        for (int i = 0; i < column; i++) {
          for (int k = 0; k < row; k++){
            // temp = new Color( ... ) for changing colors n*i for vertical, n*k for horizontal
            image.setPixel(x + ((width / column) * i ), y + ((height / row) * k), temp);            
          }
        }
      }
    }
    final String filename = "doc/a01-polka-dots.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }

  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    double dx = Math.pow(x - cx, 2);
    double dy = Math.pow(y - cy, 2);
    double d = Math.pow( dx + dy, (double) 1/2);
    if (d <= r) return cColor;
    return bgColor;
  }

  // return the average divider of a given number
  private int getDivider (int num) {
    ArrayList<Integer> temp = new ArrayList<>();
    for (int i = 1; i <= num; i++){
        if (num % i == 0) 
            temp.add(i);
    }
    return temp.get(temp.size() / 2);
  }
}
