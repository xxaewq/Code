package Sources.Render;
import java.awt.Graphics2D;
import java.awt.image.*;
import java.util.Vector;

import Sources.GameState.PlayState;
import Sources.Tool.Load;

public class PlayerRender extends Render {
    protected Vector<Vector<BufferedImage>> playeractionimages;
    protected Load loadimage;
    private int actionstate;
    public PlayerRender(PlayState playstate) {
        super(playstate);
        this.actionstate = 0;
        this.loadimage = new Load("/Image/sokoban_tilesheet.png");
        this.playeractionimages = new Vector<Vector<BufferedImage>>();
        this.SetUp();
    }
    
    public void SetUp(){
        BufferedImage input;
        for(int i = 0; i < 4;i++){
            Vector<BufferedImage> action = new Vector<>();
            for(int j = 0; j < 3; j++){
                if(i<2){
                    input = loadimage.LoadSubImage(j + i * 3, 4);
                }
                else{
                    input = loadimage.LoadSubImage(j + (i-2)*3,6);
                }
                action.add(input);
            }
            this.playeractionimages.add(action);
        }
       
    }


    public void render(Graphics2D g2){
        if(this.getPlayState().getGamepanel().getKeyHandler().getkeypresses()[(int) 'W']||
        this.getPlayState().getGamepanel().getKeyHandler().getkeypresses()[(int) 'S']||
        this.getPlayState().getGamepanel().getKeyHandler().getkeypresses()[(int) 'A']||
        this.getPlayState().getGamepanel().getKeyHandler().getkeypresses()[(int) 'D'])
        {
            if(this.getPlayState().getGamepanel().getGamestatemanager().getStates().size()==1)
            this.actionstate++;
            if(this.actionstate>=30){
                this.actionstate = 0;
            }
        }
        g2.drawImage(playeractionimages.elementAt(this.getPlayState().getPlayer().getDirection()).elementAt(this.actionstate/10), this.getPlayState().getPlayer().getPosition().elementAt(0), this.getPlayState().getPlayer().getPosition().elementAt(1),null);
}
}
