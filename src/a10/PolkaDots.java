package a10;

import cgtools.Color;
import cgtools.Sampler;

public class PolkaDots implements Sampler{

    public double radius;
    public Color backgroundColor, dotColor;

    public PolkaDots(Color dotColor, Color backColor, double radius){
        this.backgroundColor = backColor;
        this.dotColor = dotColor;
        this.radius = radius;
    }

    @Override
    public Color getColor(double u, double v) {
        u = u - Math.floor(u);
        v = v - Math.floor(v);
        
        double widthDistance = Math.pow(0.5 - u, 2);
        double heigthDistance = Math.pow(0.5 - v, 2);
        double centerDistance = Math.sqrt(widthDistance + heigthDistance);

        if (centerDistance <= radius){
            return dotColor;
        } else {
            return backgroundColor;
        }
    }
    
}
