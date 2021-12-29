package a04;

import a03.Hit;
import a03.Ray;
import a09.BoundingBox;

public interface Shape {
  public Hit intersect(Ray r);
  //public BoundingBox bounds();
}
