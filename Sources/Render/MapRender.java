package Sources.Render;

import java.awt.image.*;
import java.util.Vector;

import Sources.Entity.Entity;
import Sources.GameState.PlayState;
import Sources.Tool.Load;
import java.awt.Graphics2D;


public class MapRender extends Render {
    protected Load loadimage;
    protected BufferedImage tile[][];
    protected Vector<Entity> layer;
    public MapRender(PlayState playstate) {
        super(playstate);
        this.layer = this.getPlayState().getMap().getLoadMap().getMaplayer();
        loadimage = new Load("/Image/sokoban_tilesheet.png");
        this.tile = new BufferedImage[110][110];
        setUp();
    }

    public void setUp(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 13; j++){
                this.tile[i][j] = loadimage.LoadSubImage(j, i);
            }
        }
    }
    
    public void Render(Graphics2D g2){
        for(Entity entity: this.layer){
            g2.drawImage(tile[entity.getKind()/13][entity.getKind()%13], entity.getPosition().elementAt(0), entity.getPosition().elementAt(1), null);
}
}
}
