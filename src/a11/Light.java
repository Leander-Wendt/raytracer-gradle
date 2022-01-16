package a11;

import a10.Shape;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public interface Light {
    public Color incomingIntensity(Point hit, Direction normal, Shape scene);
}
