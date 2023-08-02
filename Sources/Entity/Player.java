package Sources.Entity;
import java.awt.Rectangle;
import java.util.Vector;
public class Player extends Entity {
    private int speed;
    private int direction;
    private boolean running;
    


    public Player(Vector<Integer> position) {
        super(position);
        this.running = false;
        this.speed = 4;
        this.direction = 0;
        this.setGothrough(false);
    }


    public void move(){
        switch(direction){
            case 0:
                this.getPosition().set(1,this.getPosition().elementAt(1) + this.speed);
                break;
            case 1:
                this.getPosition().set(1,this.getPosition().elementAt(1) - this.speed);
                break;
            case 2:
                this.getPosition().set(0,this.getPosition().elementAt(0) + this.speed);
                break;
            case 3:
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
    public boolean hit(Vector<Entity> entities, Entity entity){
        Rectangle rect = new Rectangle(0,0,44,44);
        switch(direction){
            case 0:
                rect.setLocation(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)+speed+10);
                break;
            case 1:
                rect.setLocation(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)-speed+10);
                break;
            case 2:
                rect.setLocation(this.getPosition().elementAt(0) + speed+10, this.getPosition().elementAt(1)+10);
                break;
            case 3:
                rect.setLocation(this.getPosition().elementAt(0) - speed+10, this.getPosition().elementAt(1)+10);
                break;
        }
        for(Entity check: entities){
            if(!check.getGothrough()){
                if(rect.intersects(check.getshape())){
                    if(check!=entity){
                        return true;
                    }
                }

            }
        }
        return false;
    }
    public boolean hit(Vector<Entity> entities){
        Rectangle rect = new Rectangle(0,0,44,44);
        switch(direction){
            case 0:
                rect.setLocation(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)+speed+10);
                break;
            case 1:
                rect.setLocation(this.getPosition().elementAt(0)+10, this.getPosition().elementAt(1)-speed+10);
                break;
            case 2:
                rect.setLocation(this.getPosition().elementAt(0) + speed+10, this.getPosition().elementAt(1)+10);
                break;
            case 3:
                rect.setLocation(this.getPosition().elementAt(0) - speed+10, this.getPosition().elementAt(1)+10);
                break;
        }
        for(Entity check: entities){
            if(!check.getGothrough()){
                if(rect.intersects(check.getshape())){
                    if(check instanceof Box){   
                        if(!((Box)check).hit(entities,this.direction,this.speed)&&!hit(entities,check)){
                            ((Box) check).beMoved(this);
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
        this.getshape().setBounds(this.getPosition().elementAt(0)+12, this.getPosition().elementAt(1)+12, 40, 40);
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
