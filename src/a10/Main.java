package a10;

import cgg.Image;
import cgtools.Color;
import cgtools.Matrix;
import cgtools.Vector;

public class Main {
    public static void main(String[] args){
      double start = System.currentTimeMillis();
      final double width = 480;
      final double height = 270;
      final int ABTASTUNGEN_PRO_PIXEL = 100;

      Shape background = new Background(new BackMat(new TextureTransform(new Texture("doc/sky.jpg"), Matrix.rotation(Vector.yAxis, 0))));
      Shape ground = new Plane(Vector.point(0, -1, 0), Vector.direction(0, 1, 0), 100, new DiffuseMaterial(new ChessGrid(Color.white, Color.black, 1000)));
      Sphere earth = new Sphere(Vector.point(2, 0, -5), 1, new DiffuseMaterial(new TextureTransform(new Texture("doc/erde.jpg"), Matrix.rotation(Vector.yAxis, 10))));
      Sphere polka = new Sphere(Vector.point(-2, 0, -5), 1, new DiffuseMaterial(new TextureTransform(new PolkaDots(Color.violet, Color.cyan, 0.4), Matrix.scaling(20, 20, 0))));

      TransformationGroup scene = new TransformationGroup();
      
      scene.add(background);
      scene.add(ground);
      scene.add(earth);
      scene.add(polka);
      
      RecursionRaytracer2 content = new RecursionRaytracer2(width, height, scene, 100);

      Image image = new Image((int) width, (int) height);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL, 8);

      

      final String filename = "doc/a10-1.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);  

      Matrix m = Matrix.multiply(Matrix.rotation(Vector.xAxis, -25), Matrix.translation(Vector.point(0, 0, 10)), Matrix.translation(Vector.point(0, 5, 0)));
      content.moveCamera(m);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL, 8);


      final String filename2 = "doc/a10-2.png";
      image.write(filename2);
      System.out.println("Wrote image: " + filename2);  

      double end = System.currentTimeMillis();
      System.out.println("Rendertime: " + (end - start) / 1000 + " seconds || " + ((end - start) / 1000) / 60 + " minutes.");    
    }

}
