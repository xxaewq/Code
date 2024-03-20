package Sources.GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import Sources.GamePanel;
import Sources.Map.Map;
import Sources.Tool.KeyHandler;
import java.awt.event.*;
import java.awt.BasicStroke;

// Định nghĩa trạng thái chiến thắng
public class WinningState extends GameState {
    private int counter;
    private int choice;

    public WinningState(GamePanel gamepanel) {
        super(gamepanel);
        this.choice = 1; // Lựa chọn mặc định là 1(Next Map)
        this.counter = 0;
    }

    @Override
    public void input(KeyHandler keyHandler) {
        if (keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE]) {
            keyHandler.getkeypresses()[KeyEvent.VK_ESCAPE] = false;
            counter++;
            if (counter < 2) {
                this.getGamepanel().getGamestatemanager().popState();
            } else {
                if (counter > 19) {
                    if (counter % 20 == 0)
                        this.getGamepanel().getGamestatemanager().popState();
                }
            }
        } else if (keyHandler.getkeypresses()[(int) 'S']) {
            counter++;
            if (counter < 2) {
                choice++;
            } else {
                if (counter > 19) {
                    if (counter % 20 == 0)
                        choice++;
                }
            }
            if (choice > 4) {
                choice = 4;
            }
        } else if (keyHandler.getkeypresses()[(int) 'W']) {
            counter++;
            if (counter < 2) {
                choice--;
            } else {
                if (counter > 19) {
                    if (counter % 20 == 0)
                        choice--;
                }
            }
            if (choice > 4) {
                choice = 4;
            }
            if (choice < 1) {
                choice = 1;
            }
        } else if (keyHandler.getkeypresses()[KeyEvent.VK_ENTER]) { // Enter
            keyHandler.getkeypresses()[KeyEvent.VK_ENTER] = false;
            if (counter < 2) {
                if (this.choice == 1) { // Nếu lựa chọn là 1(Next Map)
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getMapManager()
                            .setCurrentMap(this.getGamepanel().getMapManager().getCurrentMapint()); // Tăng map hiện
                                                                                                        // tại lên 1
                     System.out.println(this.getGamepanel().getMapManager().getCurrentMapint());
                    this.getGamepanel().getGamestatemanager().addState(
                            new PlayState(this.getGamepanel(), this.getGamepanel().getMapManager().getCurrentMap())); // Chuyển
                                                                                                                      // sang
                                                                                                                      // trạng
                                                                                                                      // thái
                                                                                                                      // chơi
                                                                                                                      // game
                } else if (this.choice == 2) { // Nếu lựa chọn là 2(Restart)
                    this.getGamepanel().getGamestatemanager().popState();
                    Map inputpath = ((PlayState) this.getGamepanel().getGamestatemanager().states.lastElement())
                            .getMap(); // Lấy map hiện tại
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().addState(new PlayState(this.getGamepanel(), inputpath));
                } else if (this.choice == 3) { // Nếu lựa chọn là 3(Map Selection)
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().addState(new MapSelection(this.getGamepanel()));
                }
                if (this.choice == 4) { // Nếu lựa chọn là 4(Quit)
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().popState();
                    this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel())); // Chuyển
                                                                                                           // sang trạng
                                                                                                           // thái menu
                }
            } else {
                if (counter > 9) {
                    if (counter % 10 == 0)
                        if (this.choice == 1) {
                            // this.getGamepanel().getGamestatemanager().popState();
                            // this.getGamepanel().getGamestatemanager().addState(new
                            // MapSelection(this.getGamepanel()));

                            this.getGamepanel().getGamestatemanager().popState();
                            this.getGamepanel().getGamestatemanager().popState();
                            this.getGamepanel().getMapManager()
                                    .setCurrentMap(this.getGamepanel().getMapManager().getCurrentMapint() + 1);
                            // System.out.println(this.getGamepanel().getMapManager().getCurrentMapint());
                            this.getGamepanel().getGamestatemanager().addState(new PlayState(this.getGamepanel(),
                                    this.getGamepanel().getMapManager().getCurrentMap()));

                        } else if (this.choice == 2) {
                            // this.getGamepanel().getGamestatemanager().popState();
                            // this.getGamepanel().getGamestatemanager().addState(new
                            // MapSelection(this.getGamepanel()));

                            this.getGamepanel().getGamestatemanager().popState();
                            Map inputpath = ((PlayState) this.getGamepanel().getGamestatemanager().states.lastElement())
                                    .getMap(); // Lấy map hiện tại
                            this.getGamepanel().getGamestatemanager().popState();
                            this.getGamepanel().getGamestatemanager()
                                    .addState(new PlayState(this.getGamepanel(), inputpath));
                        } else if (this.choice == 3) {
                            // this.getGamepanel().getGamestatemanager().popState();
                            // this.getGamepanel().getGamestatemanager().addState(new
                            // MapSelection(this.getGamepanel()));

                            this.getGamepanel().getGamestatemanager().popState();
                            this.getGamepanel().getGamestatemanager().popState();
                            this.getGamepanel().getGamestatemanager().addState(new MapSelection(this.getGamepanel()));

                        }
                    if (choice == 4) {
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().popState();
                        this.getGamepanel().getGamestatemanager().addState(new GameMenu(this.getGamepanel()));
                    }
                }
            }

        } else {
            counter = 0;
        }
    }

    public int getStringLenth(Graphics2D g, String input) {
        int txtLength = (int) g.getFontMetrics().getStringBounds(input, g).getWidth();
        return txtLength;
    }


    //Vẽ giao diện trạng thái chiến thắng
    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(0, 0, 2000, 2000);
        g.setFont(GameStateManager.font_bong);
        g.setColor(Color.red);
        g.setFont(g.getFont().deriveFont(50F));
        g.drawString("Congratulation", 700 - getStringLenth(g, "Congratulation") / 2, 200);
        g.setColor(Color.black);
        g.setFont(g.getFont().deriveFont(30F));
        g.drawString("Next Map", 700 - getStringLenth(g, "Next Map") / 2, 300);
        g.drawString("Restart", 700 - getStringLenth(g, "Restart") / 2, 400);
        g.drawString("Map Seclection", 700 - getStringLenth(g, "Map Selection") / 2, 500);
        g.drawString("Quit", 700 - getStringLenth(g, "Quit") / 2, 600);
        g.drawString("=>", 400, (choice + 2) * 100);
        g.setStroke(new BasicStroke(5));
        g.drawRect(350, 100, 700, 600);

    }

    @Override
    public void update() {

    }

}
