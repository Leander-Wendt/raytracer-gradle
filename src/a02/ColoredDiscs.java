package a02;

import cgtools.Color;
import cgtools.Sampler;
import java.util.ArrayList;

public class ColoredDiscs implements Sampler {
    private ArrayList<Disc> discs = new ArrayList<Disc>(); 
    int amount;
    ColoredDiscs (int amount) {
        // TODO: push random generated discs into arraylist
        for (int i = 0; i < amount; i++){

        }
        
    }

    public Color getColor(double x, double y) {
        // TODO for each schleife
        for (Disc d : discs){
            
        }
        return null;
    }
}
