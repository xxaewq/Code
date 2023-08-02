package Sources.Tool;
import javax.imageio.ImageIO;
import Sources.Entity.Box;
import Sources.Entity.Crystal;
import Sources.Entity.Entity;
import Sources.Entity.Ground;
import Sources.Entity.Wall;

import java.awt.image.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
public class Load {
    protected String path;
    protected Vector<Entity> entities;
    protected Vector<Entity> crystals;
    protected Vector<Integer> PlayerPosition;

    public Load(String path){
        this.path = path;
        this.entities = new Vector<>();
        this.PlayerPosition = new Vector<>();
        this.crystals = new Vector<>();
    }

    public BufferedImage LoadImage(){
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(this.path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    
    public BufferedImage LoadSubImage(int col,int row){
        return this.LoadImage().getSubimage(col*64, row*64, 64, 64);
    }

    public void LoadSound(){

    }
    public Vector<Entity> LoadMap(){
        int col = 0;
        int row = 0;
        try {
            InputStream is = getClass().getResourceAsStream(this.path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            do{
                do{
                    line = br.readLine();
                    if(line.equals("nextlayer")||line.equals("end")){
                        col = 0;
                        row = 0;
                        break;
                    }
                    String [] obj = line.split(",");
                        for(String a: obj){
                            Vector<Integer> v = new Vector<>();
                            v.add(col*64);
                            v.add(row*64);
                            int kind = Integer.parseInt(a);
                            if(kind==0){
                                col++;
                                continue;
                            }
                            else{
                                kind--;
                            }
                            if(96<kind&&kind<101||83<kind&&kind<88){
                                Wall thing = new Wall(v);
                                thing.setKind(kind);
                                this.entities.add(thing);
                            }
                            else if(87<kind&&kind<91){
                                Ground thing = new Ground(v);
                                thing.setKind(kind);
                                entities.add(thing);
                            }
                            else if(kind==52){
                                this.PlayerPosition = v;
                            }
                            else if(kind==25||kind==38||kind==51||kind==64||kind==77){
                                Crystal thing = new Crystal(v);
                                thing.setKind(kind);
                                entities.add(thing);
                                crystals.add(thing);
                            }
                            else{
                                Box thing = new Box(v);
                                thing.setKind(kind);
                                entities.add(thing);
                            }
                            col++;
                        }
                        col = 0;
                        row++;
                }while(!line.isEmpty());
        }while(!line.equals("end"));
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }
    public Vector<Entity> getEntities() {
        return entities;
    }
    public Vector<Integer> getPlayerPosition() {
        return PlayerPosition;
    }
    public void setPlayerPosition(Vector<Integer> playerPosition) {
        PlayerPosition = playerPosition;
    }

}
