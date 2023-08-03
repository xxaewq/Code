package Sources.Entity;

import java.awt.Rectangle;
import java.util.Vector;

public class Crystal extends Entity {
    private boolean pulled;
    public Crystal(Vector<Integer> position) {
        super(position);
        this.pulled = false;
        this.setGothrough(true);
        this.setshape(new Rectangle(position.elementAt(0),position.elementAt(1),64,64));
    }
    
    public boolean hit(Vector<Entity> entity){
        for(Entity check: entity){
            if(check instanceof Box){
                if(check.getshape().intersects(this.getshape())){
                    Rectangle rect = check.getshape().intersection(this.getshape());
                    if(rect.getWidth()<=0||rect.getHeight()<=0){
                        continue;
                    }
                    int area =(int) (rect.getWidth()*rect.getHeight());
                    if(area>=3600){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean pullTheBox(Vector<Entity> entity){
        this.pulled = false;
        for(int i = 0; i < entity.size(); i++){
            Entity check = entity.get(i);
            if(check instanceof Box){
                int pullx = this.getPosition().elementAt(0) - check.getPosition().elementAt(0);
                int pully = this.getPosition().elementAt(1) - check.getPosition().elementAt(1);
                if(Math.abs(pullx)<=10 && Math.abs(pully)<=10){
                    if(!((Box) check).getIsPulled()){
                        
                        check.setPosition(check.getPosition().elementAt(0)+pullx,check.getPosition().elementAt(1)+pully);
                        check.updatePosition();
                        ((Box) check).setIsPulled(true);
                        this.pulled = true;
                    }
                }
                else if(Math.abs(pullx)<=20&Math.abs(pully)<=20){
                    ((Box) check).setIsPulled(false);
                }
            }
        }
        return this.pulled;
    }
}
