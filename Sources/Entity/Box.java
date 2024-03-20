package Sources.Entity;
import java.awt.Rectangle;
import java.util.Vector;


// định nghĩa lớp box kê thừa từ lớp Entity
public class Box extends Entity {
    private boolean isPulled;   // bị đẩy hay không
    public Box(Vector<Integer> position) {
        super(position);
        this.setGothrough(false);
        this.isPulled = false;
        this.setshape(new Rectangle(position.elementAt(0),position.elementAt(1),63,63));
    }

    //hàm di chuyển box
    public void beMoved(Player player){
        int direction = player.getDirection();
        switch(direction){
            case 0: // đi xuống
                this.getPosition().set(1,this.getPosition().elementAt(1) + player.getSpeed());
                break;
            case 1: // đi lên
                this.getPosition().set(1,this.getPosition().elementAt(1) - player.getSpeed());
                break;
            case 2: // sang phải
                this.getPosition().set(0,this.getPosition().elementAt(0) + player.getSpeed());
                break;
            case 3: // sang trái
                this.getPosition().set(0,this.getPosition().elementAt(0) - player.getSpeed());
                break;
        }
        this.updatePosition();
    }

    //hàm kiểm tra box hiện tại có va cham với các đối tượng khác hay không
    public boolean hit(Vector<Entity> entities,int direction,int speed){
        Rectangle rect = new Rectangle(0,0,63,63);
        switch(direction){
            case 0: // đi xuống
                rect.setLocation(this.getPosition().elementAt(0), this.getPosition().elementAt(1)+speed);
                break;
            case 1: // đi lên
                rect.setLocation(this.getPosition().elementAt(0), this.getPosition().elementAt(1)-speed);
                break;
            case 2: // sang phải
                rect.setLocation(this.getPosition().elementAt(0) + speed, this.getPosition().elementAt(1));
                break;
            case 3: // sang trái
                rect.setLocation(this.getPosition().elementAt(0) - speed, this.getPosition().elementAt(1));
                break;
        }
        for(Entity check: entities){
            if(!check.getGothrough()){  //không thể đi qua
                if(check!=this&&rect.intersects(check.getshape())){ //không va chạm với chính nó và giao nhau với các đối tượng khác
                    return true;
                }
            }
        }
        return false;
    }
    public void setIsPulled(boolean isPulled){
        this.isPulled = isPulled;
    }
    public boolean getIsPulled(){
        return this.isPulled;
    }
}
