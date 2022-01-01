package a09;

import a04.Background;
import a04.Shape;
import a05.DiffuseMaterial;
import cgg.Image;
import cgtools.Color;
import cgtools.Matrix;
import cgtools.Random;
import cgtools.Vector;
import a04.Plane;
import a08.RecursionRaytracer2;
import a08.Transformation;
import a08.TransformationGroup;
import a03.Sphere;

public class Main {
    public static void main(String[] args){

      final double width = 480;
      final double height = 270;
      final int ABTASTUNGEN_PRO_PIXEL = 100;

      Shape background = new Background(Color.white);
      Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), Double.POSITIVE_INFINITY, new DiffuseMaterial(Color.lightblue));
      
      TransformationGroup scene = new TransformationGroup();
      
      scene.add(background);
      scene.add(ground);
      scene = square(scene, 30, 30);
      
      RecursionRaytracer2 content = new RecursionRaytracer2(width, height, scene, 100);
      Matrix m = Matrix.multiply(Matrix.rotation(Vector.xAxis, -25), Matrix.translation(Vector.point(0, 0, 10)), Matrix.translation(Vector.point(0, 5, 0)));
      content.moveCamera(m);

      Image image = new Image((int) width, (int) height);
      for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++){          
        double start = System.currentTimeMillis();
        image.superSample(content, ABTASTUNGEN_PRO_PIXEL, i);
        double end = System.currentTimeMillis();
        System.out.println("Rendertime: " + (end - start) / 1000 + " seconds || " + ((end - start) / 1000) / 60 + " minutes.");    
      }
  
      final String filename = "doc/a09-benchmark-scene.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);  
    }

    private static TransformationGroup square(TransformationGroup scene, int w, int h){
        int count = 0;
        for (double i = -1 * w / 2; i <= w / 2; i += 1.5){
            for (double j = -1 * h / 2; j <= h / 2; j += 1.5){
                // Instancing caused bugs for some reason, probably call by reference / value mashup
                Shape crown = new Sphere(Vector.point(0, 0, 0), Random.random(), new DiffuseMaterial(new Color(Random.random(), Random.random(), Random.random())));
                TransformationGroup tree = new TransformationGroup();
                tree.add(crown);
                tree.setTransformation(new Transformation(Matrix.translation(Vector.point(i, 0, j))));
                scene.add(tree);
                count++;
            }
        }
        System.out.println(count + " Spheres in scene.");
        return scene;
    }
}
