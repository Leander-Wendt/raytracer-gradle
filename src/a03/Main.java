package a03;

public class Main {
  public static void main(String[] args) {
    // coloredDisc Image
    final double width = 480;
    final double height = 270;
    //final int ABTASTUNGEN_PRO_PIXEL = 100;
    // This class instance defines the contents of the image.
    // ColoredDiscs content = new ColoredDiscs(width, height, 50);

    // 3.2 Tests:
    // 1 / root(3) ~ 1.73
    Camera cam = new Camera(Math.PI / 2, width, height);
    Ray temp = cam.shootRay(10, 10);
    System.out.println(temp.x0);
    temp = cam.shootRay(0, 0);
    System.out.println(temp.d);
    temp = cam.shootRay(5, 5);
    System.out.println(temp.d);
    temp = cam.shootRay(10, 10);
    System.out.println(temp.d);
    // Creates an image and iterates over all pixel positions inside the image.
    /*Image image = new Image(width, height);
    image.sample(content);

    // Write the image to disk.
    final String filename = "doc/a03-three-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);*/
  }
}
