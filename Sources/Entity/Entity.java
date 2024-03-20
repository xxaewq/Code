package Sources.Entity;

import java.awt.Rectangle;
import java.util.Vector;

//định nghĩa đối tượng tổng quát bất kì 
public class Entity {
    private int kind;   //Loại đối tượng
    private Vector<Integer> position;   //vị trí
    private Rectangle shape;    //hình dạng hiển thị
    private boolean gothrough;  //có thể đi xuyên qua hay không
    public Entity(Vector<Integer> position){
        this.position = new Vector<>();
        this.position = position;
        this.kind = 0;
        this.shape = new Rectangle(this.position.elementAt(0),this.position.elementAt(1),63,63);
    }
    public Vector<Integer> getPosition(){
        return this.position;
    }
    public void updatePosition(){
        this.shape.setLocation(this.position.elementAt(0), this.position.elementAt(1));
    }
    public boolean hit(Entity entity){
        return this.shape.intersects(entity.getshape());
    }
    public Rectangle getshape() {
        return shape;
    }
    public void setshape(Rectangle shape) {
        this.shape = shape;
    }
    public boolean getGothrough() {
        return gothrough;
    }
    public void setGothrough(boolean gothrough) {
        this.gothrough = gothrough;
    }
    public int getKind() {
        return kind;
    }
    public void setKind(int kind) {
        this.kind = kind;
    }
    public void setPosition(Vector<Integer> position) {
        this.position = position;
    }
    public void setPosition(int x,int y) {
        Vector<Integer> newPosition = new Vector<>();
        newPosition.add(x);
        newPosition.add(y);
        this.position = newPosition;
        updatePosition();
    }
}
