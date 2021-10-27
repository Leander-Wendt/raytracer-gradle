package a02;

import cgtools.Color;
import cgtools.Sampler;
import java.util.ArrayList;
import cgtools.Random;

public class ColoredDiscs implements Sampler {
    private ArrayList<Disc> discs = new ArrayList<>();
    int width, height;
    ColoredDiscs (int width, int height, int amount) {
        Random rng = new Random(); 
        this.width = width;
        this.height = height;
        for (int i = 0; i < amount; i++){
            // change params for different discs
            // x, y, r, c
            int x = (int) Math.round(rng.random() * width);
            int y = (int) Math.round(rng.random() * height);
            int r = (int) Math.round(rng.random() * 100);
            Color temp = new Color((int) Math.round(rng.random() * 255), (int) Math.round(rng.random() * 255), (int) Math.round(rng.random() * 255));
            discs.add(new Disc( x, y, r, temp));
        }
        sortDiscs();        
    }

    public Color getColor(double x, double y) {
        // TODO for each schleife
        Color temp = new Color (0, 0, 0);
        for (Disc d : discs){
            if (!d.getColor(x, y).equals(temp)) {
                return d.getColor(x, y);
            }
        }
        return temp;
    }

    public void sortDiscs() {
        // figure out Java Comparator
        discs.sort();
    }
}
