package cgg.a01;

import cgtools.*;
import java.util.ArrayList;

// Represents the contents of an image. Provides the same color for all pixels.
public class PolkaDots implements Sampler {
  Color bgColor, cColor;
  double cx, cy, r;
  int row, column;

  public PolkaDots(Color bgColor, Color cColor, double width, double height, double r, int amount) {
    this.bgColor = bgColor;
    this.cColor = cColor;
    cx = width / 2;
    cy = height / 2;
    this.r = r;
    row = getDivider(amount);
    column = amount / row;
    if (row > column) swap(row, column);
  }

  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    double dx = Math.pow(x - cx, 2);
    double dy = Math.pow(y - cy, 2);
    double d = Math.pow( dx + dy, (double) 1/2);
    if (d <= r) return cColor;
    return bgColor;
  }

  private int getDivider (int num) {
    ArrayList<Integer> temp = new ArrayList<>();
    for (int i = 1; i <= num; i++){
        if (num % i == 0) 
            temp.add(i);
    }
    return temp.get(temp.size());
  }

  private void swap (int a, int b){
    int temp = a;
    a = b;
    b = temp;
  }
}
