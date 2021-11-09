package a04;

import a03.Hit;
import a03.Ray;

public interface Shape {
  public Hit intersect(Ray r);
}
