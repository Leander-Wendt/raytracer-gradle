package a10;

import a03.Ray;
import cgtools.Color;

public interface Material {
    Ray scatteringRay (Ray scatR, Hit h);
    Color emission (Ray r, Hit h);
    Color albedo (Ray r, Hit h);
}
