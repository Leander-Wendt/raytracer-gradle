package a10;

import a03.Ray;

public interface Shape {
  public Hit intersect(Ray r);
}
