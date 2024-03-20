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


// Định nghĩa lớp Load : load hình ảnh, âm thanh và map từ file
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
            InputStream is = getClass().getResourceAsStream(this.path); //Lấy file từ đường dẫn
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  //Đọc file
            String line = "";   
            do{ //Vòng lặp đọc từng dòng cho đến khi gặp end
                do{ //Vòng lặp đọc từng dòng cho đến khi dòng rỗng
                    line = br.readLine();   //Đọc dòng
                    if(line.equals("nextlayer")||line.equals("end")){   //Nếu gặp nextlayer hoặc end reset cột và dòng
                        col = 0;
                        row = 0;
                        break;
                    }
                    String [] obj = line.split(",");    //Tách chuỗi theo dấu phẩy
                        for(String a: obj){
                            Vector<Integer> v = new Vector<>();
                            v.add(col*64);  
                            v.add(row*64);
                            int kind = Integer.parseInt(a);     //Chuyển chuỗi thành số
                            if(kind==0){    //  Nếu kind = 0 thì tăng cột và tiếp tục
                                col++;
                                continue;
                            }
                            else{
                                kind--; //  Trừ đi 1 vì kind bắt đầu từ 0
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
