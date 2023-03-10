package a02;

import cgtools.Color;
import cgtools.Sampler;
import java.util.ArrayList;
import cgtools.Random;

public class ColoredDiscs implements Sampler {
    private ArrayList<Disc> discs = new ArrayList<>();
    int width, height;
    public ColoredDiscs (int width, int height, int amount) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < amount; i++){
            int x = (int) Math.round(Random.random() * width);
            int y = (int) Math.round(Random.random() * height);
            int r = (int) Math.round(Random.random() * 100);
            Color temp = new Color(Random.random(), Random.random(), Random.random());
            discs.add(new Disc(x, y, r, temp));
        }
        sortDiscs();        
    }

    public Color getColor(double x, double y) {
        Color temp = new Color (0, 0, 0);
        for (Disc d : discs){
            if (!d.getColor(x, y).equals(temp)) {
                return d.getColor(x, y);
            }
        }
        return temp;
    }

    public void sortDiscs() {
        boolean sorted = false;
        Disc temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < discs.size() - 1; i++) {
                if (discs.get(i).getRadius() > discs.get(i + 1).getRadius()) {
                    temp = discs.get(i);
                    discs.set(i, discs.get(i + 1));
                    discs.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }
}
