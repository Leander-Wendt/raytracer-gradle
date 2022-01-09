package a10;

import cgtools.Color;
import cgtools.Sampler;

public class ChessGrid implements Sampler {
	Color cx;
	Color cy;
	int n;

	public ChessGrid(Color cx, Color cy, int n) {
		this.cx = cx;
		this.cy = cy;
		this.n = n;
	}

	public Color getColor(double u, double v) {
		int ui = (int) Math.floor((u - Math.floor(u)) * n);
		int vi = (int) Math.floor((v - Math.floor(v)) * n);

		if((ui + vi) % 2 == 0){
			return cx;
		} else {
			return cy;
		}
	}
}
