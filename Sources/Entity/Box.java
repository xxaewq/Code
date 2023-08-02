package Sources.Entity;

import java.awt.Rectangle;
import java.util.Vector;

public class Box extends Entity {
    private boolean isPulled;
    public Box(Vector<Integer> position) {
        super(position);
        this.setGothrough(false);
        this.isPulled = false;
        this.setshape(new Rectangle(position.elementAt(0),position.elementAt(1),63,63));
    }
    public void beMoved(Player player){
        int direction = player.getDirection();
        switch(direction){
            case 0:
                this.getPosition().set(1,this.getPosition().elementAt(1) + player.getSpeed());
                break;
            case 1:
                this.getPosition().set(1,this.getPosition().elementAt(1) - player.getSpeed());
                break;
            case 2:
                this.getPosition().set(0,this.getPosition().elementAt(0) + player.getSpeed());
                break;
            case 3:
                this.getPosition().set(0,this.getPosition().elementAt(0) - player.getSpeed());
                break;
        }
        this.updatePosition();
    }
    public boolean hit(Vector<Entity> entities,int direction,int speed){
        Rectangle rect = new Rectangle(0,0,63,63);
        switch(direction){
            case 0:
                rect.setLocation(this.getPosition().elementAt(0), this.getPosition().elementAt(1)+speed);
                break;
            case 1:
                rect.setLocation(this.getPosition().elementAt(0), this.getPosition().elementAt(1)-speed);
                break;
            case 2:
                rect.setLocation(this.getPosition().elementAt(0) + speed, this.getPosition().elementAt(1));
                break;
            case 3:
                rect.setLocation(this.getPosition().elementAt(0) - speed, this.getPosition().elementAt(1));
                break;
        }
        for(Entity check: entities){
            if(!check.getGothrough()){
                if(check!=this&&rect.intersects(check.getshape())){
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
