package Sources.GameState;

import java.util.Vector;
import Sources.GamePanel;
import Sources.Tool.KeyHandler;
import java.awt.*;

// Quản lý trạng thái của game
public class GameStateManager {
    public Vector<GameState> states;
    public  GamePanel gamepanel;
    public static Font font_rainyHeart;
    public static Font font_bong;

    // Khởi tạo
    public GameStateManager(GamePanel gamepanel) {
        this.gamepanel = gamepanel;
        this.states = new Vector<>();
        this.states.add(new GameMenu(this.gamepanel));
        try {
            font_bong =Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Font/04B_30__.TTF"));
            font_rainyHeart = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/Font/rainyhearts.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật trạng thái cuối cùng trong vector
    public void update(){
        states.lastElement().update();
    }
    public void render(Graphics2D g2){
        for(GameState gamestate : this.states){
            gamestate.render(g2);
    }
}
    public void input(KeyHandler keyHandler){
        states.lastElement().input(keyHandler);
    }
    public void addState(GameState state){
        this.states.add(state);
    }
    public void popState(){
        this.states.remove(this.states.lastElement());
    }
    public void popState(GameState gamestate){
        this.states.remove(gamestate);
    }
    public Vector<GameState> getStates() {
        return states;
    }
    public void setStates(Vector<GameState> states) {
        this.states = states;
    }
}