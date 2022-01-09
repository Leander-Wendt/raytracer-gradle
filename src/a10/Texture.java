package a10;

import cgtools.Color;
import cgtools.ImageTexture;
import cgtools.Sampler;

public class Texture implements Sampler {

	ImageTexture texture;

	public Texture(String filename){
		texture = new ImageTexture(filename);
	}

	public Color getColor(double u, double v){
		Color color = texture.getColor(u,v);
		double r = Math.pow(color.r, 2.2);
		double g = Math.pow(color.g, 2.2);
		double b = Math.pow(color.b, 2.2);
		return new Color(r,g,b);
	}

}
