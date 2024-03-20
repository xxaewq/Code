package Sources.GameState;

import java.awt.Graphics2D;
import java.util.Vector;
import java.awt.event.*;

import Sources.GamePanel;
import Sources.Entity.Crystal;
import Sources.Entity.Entity;
import Sources.Entity.Player;
import Sources.Map.Map;
import Sources.Render.MapRender;
import Sources.Render.PlayerRender;
import Sources.Tool.KeyHandler;

// Định nghĩa trạng thái chơi game
public class PlayState extends GameState {
    private Player player;
    private Map map;
    private MapRender mapRender;
    private PlayerRender playerRender;
    private Vector<Entity> entities;
    private Vector<Entity> crystals;
    private boolean music;
    public int counter;

    public PlayState(GamePanel gamepanel, Map map) {
        super(gamepanel);
        this.counter = 0;
        this.map = map;
        this.music = false;
        this.player = new Player(this.map.getLoadAndLoadMap().getPlayerPosition());
        this.playerRender = new PlayerRender(this);
        this.entities = new Vector<>();
        this.crystals = new Vector<>();
        this.mapRender = new MapRender(this);
        this.entities = this.getMap().getLoadMap().getEntities();
        this.crystals = this.getMap().getLoadMap().getCrystals();
    }

    @Override
    public void input(KeyHandler keyHandler) {
        if (keyHandler.getkeypresses()[(int) 'W'] || keyHandler.getkeypresses()[(int) 'A']
                || keyHandler.getkeypresses()[(int) 'D'] || keyHandler.getkeypresses()[(int) 'S']) {
            if (keyHandler.getkeypresses()[(int) 'S']) {
                player.setDirection(0);
            }
            if (keyHandler.getkeypresses()[(int) 'W']) {
                player.setDirection(1);
            }
            if (keyHandler.getkeypresses()[(int) 'D']) {
                player.setDirection(2);
            }
            if (keyHandler.getkeypresses()[(int) 'A']) {
                player.setDirection(3);
            }
            if (!this.player.hit(this.entities)) { // Nếu không va chạm với các entity khác
                this.player.move();
                player.setRunning(true);
            }
        } else {
            player.setRunning(false);
        }
        if (keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE]) { // Nếu nhấn phím ESC thì tạm dừng game
            keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE] = false;
            counter++;
            if (counter < 2) {
                this.getGamepanel().getGamestatemanager().addState(new GamePause(this.getGamepanel())); // Tạm dừng game
            } else {
                if (counter > 9) {
                    if (counter % 10 == 0)
                        this.getGamepanel().getGamestatemanager().addState(new GamePause(this.getGamepanel()));
                }
            }
        } else {
            counter = 0;
        }
    }

    @Override
    public void render(Graphics2D g) {
        this.mapRender.Render(g);
        this.playerRender.render(g);
    }

    @Override
    public void update() {
        for (Entity entity : this.crystals) { // Duyệt qua các tinh thể
            if (((Crystal) entity).pullTheBox(entities)) { // Nếu tinh thể kéo box
                this.getGamepanel().playSound1(2); // Phát âm thanh
            }
        }
        if (checkWinning()) { // Nếu thắng
            if (music) {
                this.getGamepanel().stopSoundEffect(); // Tắt nhạc nền
                this.music = false;
            }
            int i = this.getGamepanel().getGameDataStore().getMapunlock(); // Lấy map đã mở
            int currentmap = this.getGamepanel().getMapManager().getCurrentMapint();
            if (currentmap < 17) {
                currentmap++;
            }
            this.getGamepanel().getGameDataStore().setMapunlock(Math.max(i, currentmap)); // Cập nhật map đã mở
            this.getGamepanel().getSaveandload().save(); // Lưu lại
            this.getGamepanel().getMapManager().setCurrentMap(currentmap); // Chuyển sang map tiếp theo
            this.getGamepanel().getGamestatemanager().addState(new WinningState(this.getGamepanel())); // Chuyển sang
                                                                                                       // trạng thái
                                                                                                       // thắng
        } else {
            if (player.isRunning()) { // Nếu nhân vật đang di chuyển thì phát nhạc nền lặp đi lặp lại
                if (!music)
                    this.getGamepanel().playSoundEffect(1);
                this.music = true;
            } else {
                if (music) {
                    this.getGamepanel().stopSoundEffect();
                    this.music = false;
                }
            }
        }
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public MapRender getMapRender() {
        return mapRender;
    }

    public void setMapRender(MapRender mapRender) {
        this.mapRender = mapRender;
    }

    public PlayerRender getPlayerRender() {
        return playerRender;
    }

    public void setPlayerRender(PlayerRender playerRender) {
        this.playerRender = playerRender;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean checkWinning() { // Kiểm tra điều kiện thắng
        for (Entity check : this.crystals) { // Duyệt qua các tinh thể
            Crystal input = (Crystal) check; // ép kiểu
            if (!input.hit(this.entities)) { // Nếu tinh thể không va chạm với box
                return false;
            }
        }
        return true;
    }
}
