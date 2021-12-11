package a08;

import a04.Background;
import a04.Shape;
import a05.DiffuseMaterial;
import a05.RecursionRaytracer;
import cgg.Image;
import cgtools.Color;
import cgtools.Matrix;
import cgtools.Vector;
import a06.Glass;
import a06.Mirror;
import a04.Plane;
import a07.Cylinder;
import a03.Sphere;

public class Main {
    public static void main(String[] args){
        double start = System.currentTimeMillis();
      double end;

      final double width = 480;
      final double height = 270;
      final int ABTASTUNGEN_PRO_PIXEL = 100;

      Shape background = new Background(Color.white);
      Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), Double.POSITIVE_INFINITY, new Glass(Color.lightblue, true));
      Group scene = new Group(background);
      scene.add(ground);
      
      circleTrees(5, 1, 1, 1, scene);
      circleTrees(10, 1, 1, 1, scene);
      circleTrees(20, 1, 1, 1, scene);
      circleTrees(30, 1, 1, 1, scene);      
      
      RecursionRaytracer content = new RecursionRaytracer(width, height, scene, 100);
      Image image = new Image((int) width, (int) height);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL);
  
      final String filename = "doc/a08-1.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);

      Matrix m = Matrix.multiply(Matrix.rotation(Vector.xAxis, -5), Matrix.translation(Vector.point(0, 0, 5)), Matrix.translation(Vector.point(0, 5, 0)));
      content.moveCamera(m);

      image = new Image((int) width, (int) height);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL);

      final String filename2 = "doc/a08-2.png";
      image.write(filename2);
      System.out.println("Wrote image: " + filename2);

      end = System.currentTimeMillis();

      System.out.println("Rendertime: " + (end - start) / 1000 + " seconds");      
      
    }

    private static void circleTrees(int anzahlObjekte, double w, double h, int multiplikator, Group scene){
        double angle = (Math.PI * 2.0) / anzahlObjekte;
        double radiusX = multiplikator * anzahlObjekte + w;
        double radiusY = multiplikator * anzahlObjekte + h;
        double startX = 0;
        double startY = 0;

        for (int i = 0; i < anzahlObjekte; i++) {
          double midPosX = (Math.cos(angle * i) * radiusX) + startX;
          double midPosY = (Math.sin(angle * i) * radiusY) + startY;
          Shape stem = new Cylinder(Vector.point(midPosX, 0.0, midPosY), 0.3, 2, new Mirror(Color.brown, true, 1.0));
          Shape crown = new Sphere(Vector.point(midPosX, 1.5, midPosY), 0.9, new DiffuseMaterial(Color.mint));
          scene.add(stem);
          scene.add(crown);
        }
    }
}
