package cgg.a01;

import cgtools.*;

// Represents the contents of an image. Provides the same color for all pixels.
public class Circle implements Sampler {
  Color bgColor, cColor;
  double cx, cy, r;

  public Circle(Color bgColor, Color cColor, int width, int height) {
    this.bgColor = bgColor;
    this.cColor = cColor;
    cx = width / 2;
    cy = height / 2;
    r = height / 3;
  }

  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    double dx = Math.pow(x - cx, 2);
    double dy = Math.pow(y - cy, 2);
    double d = Math.pow( dx + dy, (double) 1/2);
    if (d <= r) return cColor;
    return bgColor;
  }
}
