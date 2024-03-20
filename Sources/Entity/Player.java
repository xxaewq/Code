package Sources.Entity;
import java.awt.Rectangle;
import java.util.Vector;



// định nghĩa lớp Player(nhân vật) kế thừa từ lớp Entity
public class Player extends Entity {
    private int speed;  //tốc độ di chuyển
    private int direction;  //hướng di chuyển
    private boolean running;    //đang chạy hay không
    
    public Player(Vector<Integer> position) {
        super(position);
        this.running = false; 
        this.speed = 4;
        this.direction = 0; //mặc định hướng đi xuống
        this.setGothrough(false);
    }


    public void move(){
        switch(direction){
            case 0: // đi xuống
                this.getPosition().set(1,this.getPosition().elementAt(1) + this.speed);
                break;
            case 1: // đi lên
                this.getPosition().set(1,this.getPosition().elementAt(1) - this.speed);
                break;
            case 2: // sang phải
                this.getPosition().set(0,this.getPosition().elementAt(0) + this.speed);
                break;
            case 3: // sang trái
                this.getPosition().set(0,this.getPosition().elementAt(0) - this.speed);
                break;
        }
        updatePosition();
    }
    public int getDirection() {
        return this.direction;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }

    //phương thức kiểm tra va chạm với các đối tượng khác ngoại trừ entity được truyền vào
    public boolean hit(Vector<Entity> entities, Entity entity){
        Rectangle rect = new Rectangle(0,0,44,44);  //tạo hình chữ nhật với kích thước 44x44
        switch(direction){  //khi nhân vật di chuyển thì hcn sẽ di chuyển trước để kiểm tra va chạm
            case 0: // đi xuống
                rect.setLocation(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)+speed+10);
                break;
            case 1: // đi lên
                rect.setLocation(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)-speed+10);
                break;
            case 2: // sang phải
                rect.setLocation(this.getPosition().elementAt(0) + speed+10, this.getPosition().elementAt(1)+10);
                break;
            case 3: // sang trái
                rect.setLocation(this.getPosition().elementAt(0) - speed+10, this.getPosition().elementAt(1)+10);
                break;
        }
        for(Entity check: entities){    //duyệt qua các đối tượng
            if(!check.getGothrough()){  //nếu không thể đi qua
                if(rect.intersects(check.getshape())){ //nếu giao nhau với các đối tượng khác
                    if(check!=entity){  //không va chạm với đối tượng được truyền vào
                        return true;
                    }
                }

            }
        }
        return false;
    }


    //phương thức kiểm tra va chạm với các đối tượng khác
    public boolean hit(Vector<Entity> entities){
        Rectangle rect = new Rectangle(0,0,44,44);
        switch(direction){
            case 0: // đi xuống
                rect.setLocation(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)+speed+10);
                break;
            case 1: // đi lên
                rect.setLocation(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)-speed+10);
                break;
            case 2: // sang phải
                rect.setLocation(this.getPosition().elementAt(0) + speed+10, this.getPosition().elementAt(1)+10);
                break;
            case 3: // sang trái
                rect.setLocation(this.getPosition().elementAt(0) - speed+10, this.getPosition().elementAt(1)+10);
                break;
        }
        for(Entity check: entities){
            if(!check.getGothrough()){      //nếu không thể đi qua
                if(rect.intersects(check.getshape())){   //nếu giao nhau với các đối tượng khác
                    if(check instanceof Box){   //nếu là box
                        if(!((Box)check).hit(entities,this.direction,this.speed)&&!hit(entities,check)){    //nếu không bị chặn bởi box và không bị chặn bởi các đối tượng khác
                            ((Box) check).beMoved(this);    //di chuyển box theo hướng nhân vật
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    else{

                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void updatePosition(){
        this.getshape().setBounds(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)+10, 44, 44);
    }

    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public boolean isRunning() {
        return running;
    }


    public void setRunning(boolean running) {
        this.running = running;
    }

}
