package a11;

import java.util.ArrayList;

import a10.TransformationGroup;


public class World {
    public final ArrayList<Light> lights;
    public final TransformationGroup scene;

    public World(TransformationGroup scene){
        this.lights = new ArrayList<>();
        this.scene = scene;
    }

    public void addLight(Light l){
        lights.add(l);
    }
    
    public ArrayList<Light> getLights(){
        return lights;
    }

    public TransformationGroup getScene(){
        return scene;
    }
}
