package a11;

import a10.Shape;
import cgg.Image;
import cgtools.Color;
import cgtools.Matrix;
import cgtools.Vector;
import a10.*;

public class Main {
    public static void main(String[] args){
      double start = System.currentTimeMillis();
      final double width = 480;
      final double height = 270;
      final int ABTASTUNGEN_PRO_PIXEL = 100;

      Shape background = new Background(new BackMat(Color.black));
      Shape ground = new Plane(Vector.point(0, -1, 0), Vector.direction(0, 1, 0), 100, Color.darkgrey);
      Sphere earth = new Sphere(Vector.point(-1, 0, -5), 1, new DiffuseMaterial(new TextureTransform(new Texture("doc/erde.jpg"), Matrix.rotation(Vector.yAxis, 10))));
      Sphere s1 = new Sphere(Vector.point(2, 0, -5), 1, new DiffuseMaterial(Color.violet));
      TransformationGroup scene = new TransformationGroup();      
      scene.add(background);
      scene.add(ground);
      scene.add(s1);
      scene.add(earth);
      Light l1 = new Pointlight(Vector.point(0, 1, -4), new Color(7, 4, 0));
      Light l2 = new Directlight(Vector.direction(1, 1, 0), new Color(2, 2, 2));
      World world = new World(scene);
      world.addLight(l2);
      LightRaytracer content = new LightRaytracer(width, height, 100, world);

      Image image = new Image((int) width, (int) height);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL, 8);      

      final String filename = "doc/a11-2.png";
      image.write(filename);
      System.out.println("Wrote image: " + filename);  

      /*World world2 = new World(scene);
      world.addLight(l2);
        
      content.changeWorld(world2);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL, 8);

      final String filename2 = "doc/a11-2.png";
      image.write(filename2);
      System.out.println("Wrote image: " + filename2);  */

      double end = System.currentTimeMillis();
      System.out.println("Rendertime: " + (end - start) / 1000 + " seconds || " + ((end - start) / 1000) / 60 + " minutes.");    
    }

}

