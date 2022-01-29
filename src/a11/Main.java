package a11;

import a10.Shape;
import cgg.Image;
import cgtools.Color;
import cgtools.Matrix;
import cgtools.Vector;
import a03.Camera;
import a10.*;

public class Main {
    public static void main(String[] args){
      double start = System.currentTimeMillis();
      final double width = 1920;
      final double height = 1080;
      final int ABTASTUNGEN_PRO_PIXEL = 100;

      Shape background = new Background(new BackMat(new TextureTransform(new Texture("doc/desert_sky.jpg"), Matrix.rotation(Vector.yAxis, 0))));
      Shape ground = new Plane(Vector.point(0, -1, 0), Vector.direction(0, 1, 0), 500, new RepeatingMat(new RepeatingTextureTransform(new Texture("doc/seamless_sand.jpg"), Matrix.scaling(75, 75, 0))));
      Sphere shaihulud = new Sphere(Vector.point(0, 0, -6), 3, new DiffuseMaterial(new TextureTransform(new Texture("doc/stone.jpg"), Matrix.scaling(1, 1, 0))));
      Sphere teeth = new Sphere(Vector.point(0, 0.05, -5.625), 2.72, new DiffuseMaterial(new TextureTransform(new Texture("doc/baleen.jpg"), Matrix.rotation(Vector.xAxis, 0))));

      Sphere a = new Sphere(Vector.point(-0.025, 0, -0.6), 0.02, Color.black);
      Sphere b = new Sphere(Vector.point(-0.025, 0.02, -0.6), 0.02, Color.black);
      Sphere bb = new Sphere(Vector.point(-0.025, 0.04, -0.6), 0.02, Color.black);
      Sphere c = new Sphere(Vector.point(0.025, 0, -0.6), 0.02, Color.black);
      Sphere d = new Sphere(Vector.point(0.025, 0.02, -0.6), 0.02, Color.black);
      Sphere dd = new Sphere(Vector.point(0.025, 0.04, -0.6), 0.02, Color.black);

      Sphere hip = new Sphere(Vector.point(0, 0.06, -0.6), 0.041, Color.black);
      Sphere torso = new Sphere(Vector.point(0, 0.08, -0.6), 0.041, Color.black);

      Sphere neck = new Sphere(Vector.point(0, 0.1, -0.6), 0.01, Color.black);
      Sphere head = new Sphere(Vector.point(0, 0.11, -0.6), 0.03, Color.black);
      
      Sphere lb = new Sphere(Vector.point(-0.04, 0.08, -0.6), 0.02, Color.black);
      Sphere lh = new Sphere(Vector.point(-0.06, 0.05, -0.6), 0.015, Color.black);

      Sphere rb = new Sphere(Vector.point(0.04, 0.08, -0.6), 0.02, Color.black);
      Sphere rh = new Sphere(Vector.point(0.06, 0.05, -0.6), 0.015, Color.black);

      TransformationGroup scene = new TransformationGroup();      
      scene.add(background);
      scene.add(ground);
      scene.add(teeth);
      scene.add(shaihulud);
      scene.add(a);
      scene.add(b);
      scene.add(c);
      scene.add(d);
      scene.add(bb);
      scene.add(dd);
      scene.add(hip);
      scene.add(torso);
      scene.add(head);
      scene.add(neck);
      scene.add(lb);
      scene.add(lh);
      scene.add(rb);
      scene.add(rh);
      Light l1 = new Directlight(Vector.direction(1, 1, 0), new Color(2, 2 * 0.8, 1));
      World world = new World(scene);
      world.addLight(l1);
      LightRaytracer content = new LightRaytracer(width, height, 100, world);

      Camera cam = content.getCamera();
      cam.setV(Matrix.multiply(Matrix.translation(0, 1, 1), Matrix.rotation(Vector.xAxis, -5)));
      content.changeCamera(cam);

      Image image = new Image((int) width, (int) height);
      image.superSample(content, ABTASTUNGEN_PRO_PIXEL, 8);      

      final String filename = "doc/cgg-competition-ws-21-921663.png";
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

