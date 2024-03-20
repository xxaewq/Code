package Sources.Map;

import java.util.Vector;

public class MapManager {
    private Vector<Map> vectormap;
    private int maxMap;
    private int currentMap;
    public MapManager(){
        this.maxMap = 20;
        this.currentMap = 1;
        this.vectormap = new Vector<>();
        setUp();
    }

    public void setUp(){
        for(int i = 0; i < maxMap; i++){
            Map inputmap = new Map("/Map/map0"+(i+1)+".txt",i%3,i/3,i+1);
            inputmap.setNomap(i+1);
            this.vectormap.add(inputmap);
        }
    }
    public Map getCurrentMap() {
        return this.vectormap.elementAt(currentMap-1);
    }
    public int getCurrentMapint() {
        return this.currentMap;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }
    public Vector<Map> getVectormap() {
        return vectormap;
    }

    public void setVectormap(Vector<Map> vectormap) {
        this.vectormap = vectormap;
    }
}
