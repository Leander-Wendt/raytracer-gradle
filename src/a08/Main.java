package a08;

import a04.Background;
import a04.Shape;
import a05.DiffuseMaterial;
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
      final int ABTASTUNGEN_PRO_PIXEL = 16;

      Shape background = new Background(Color.white);
      Shape ground = new Plane(Vector.point(0.0, -0.5, 0.0), Vector.direction(0, 1, 0), Double.POSITIVE_INFINITY, new Glass(Color.lightblue, true));
      
      TransformationGroup scene = new TransformationGroup();
      
      scene.add(background);
      scene.add(ground);
      scene = forrest(scene, 24, 16);
      
      RecursionRaytracer2 content = new RecursionRaytracer2(width, height, scene, 100);
      Image image = new Image((int) width, (int) height);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL);
  
      final String filename = "doc/a08-1.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);

      Matrix m = Matrix.multiply(Matrix.rotation(Vector.xAxis, -25), Matrix.translation(Vector.point(0, 0, 30)), Matrix.translation(Vector.point(0, 5, 0)));
      content.moveCamera(m);

      image = new Image((int) width, (int) height);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL);

      final String filename2 = "doc/a08-2.png";
      image.write(filename2);
      System.out.println("Wrote image: " + filename2);

      end = System.currentTimeMillis();

      System.out.println("Rendertime: " + (end - start) / 1000 + " seconds || " + ((end - start) / 1000) / 60 + " minutes.");      
      
    }

    private static TransformationGroup forrest(TransformationGroup scene, int w, int h){
        for (int i = -1 * w / 2; i <= w / 2; i += 2){
            for (int j = -1 * h / 2; j <= h / 2; j += 2){
                // Instancing caused bugs for some reason, probably call by reference / value mashup
                Shape stem = new Cylinder(Vector.point(0, 0.0, 0), 0.3, 2, new Mirror(Color.brown, true, 1.0));
                Shape crown = new Sphere(Vector.point(0, 1.5, 0), 0.9, new DiffuseMaterial(Color.mint));
                TransformationGroup tree = new TransformationGroup();
                tree.add(stem);
                tree.add(crown);
                tree.setTransformation(new Transformation(Matrix.translation(Vector.point(i, 0, j))));
                scene.add(tree);
            }
        }
        return scene;
    }
}
