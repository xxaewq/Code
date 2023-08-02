package Sources.Render;
import java.awt.image.*;

import Sources.Entity.Entity;
import Sources.GameState.PlayState;

import java.awt.Graphics2D;

public class EntityRender extends Render {
    BufferedImage image;
    public EntityRender(PlayState playstate, BufferedImage image) {
        super(playstate);
        this.image = image;
    }
    public void Render(Graphics2D g2, Entity entity){
        g2.drawImage(this.image,entity.getPosition().elementAt(0),entity.getPosition().elementAt(1),null);
    }
}
