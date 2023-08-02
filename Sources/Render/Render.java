package Sources.Render;
import java.awt.*;

import Sources.GameState.PlayState;

public class Render {
    private PlayState playState;

    public Render(PlayState playState){
        this.playState = playState;
    }
    public void render(Graphics2D g2){
        
    }
    public PlayState getPlayState() {
        return playState;
    }
    public void setPlayState(PlayState playState) {
        this.playState = playState;
    }
}
