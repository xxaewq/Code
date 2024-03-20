package Sources.Entity;

import java.awt.Rectangle;
import java.util.Vector;


// định nghĩa lớp Crystal (tinh thể)  kế thừa từ lớp Entity
public class Crystal extends Entity {
    private boolean pulled;  
    public Crystal(Vector<Integer> position) {
        super(position);
        this.pulled = false;
        this.setGothrough(true);
        this.setshape(new Rectangle(position.elementAt(0),position.elementAt(1),64,64));
    }
    
    //hàm kiểm tra va chạm với box
    public boolean hit(Vector<Entity> entity){
        for(Entity check: entity){
            if(check instanceof Box){   //nếu là box
                if(check.getshape().intersects(this.getshape())){   //nếu va chạm với box
                    Rectangle rect = check.getshape().intersection(this.getshape());    //lấy kích thước hình chữ nhật giao nhau
                    if(rect.getWidth()<=0||rect.getHeight()<=0){    
                        continue;
                    }
                    int area =(int) (rect.getWidth()*rect.getHeight());  //tính diện tích
                    if(area>=3844){ //nếu diện tích lớn hơn 62*62
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    
    //hàm kéo box vào vị trí tinh thể
    public boolean pullTheBox(Vector<Entity> entity){   
        this.pulled = false;
        for(int i = 0; i < entity.size(); i++){
            Entity check = entity.get(i);
            if(check instanceof Box){
                int pullx = this.getPosition().elementAt(0) - check.getPosition().elementAt(0); //tính hiệu khoảng cách theo chiều x
                int pully = this.getPosition().elementAt(1) - check.getPosition().elementAt(1); //tính hiệu khoảng cách theo chiều y
                if(Math.abs(pullx)<=10 && Math.abs(pully)<=10){
                    if(!((Box) check).getIsPulled()){   //nếu box chưa bị kéo
                        check.setPosition(check.getPosition().elementAt(0)+pullx,check.getPosition().elementAt(1)+pully);   //đặt vị trí mới cho box trùng với tinh thể
                        check.updatePosition();
                        ((Box) check).setIsPulled(true);    //đánh dấu box đã bị kéo
                        this.pulled = true;
                    }
                }
                else if(Math.abs(pullx)<=20&Math.abs(pully)<=20){   //nếu khoảng cách lớn hơn 10 và nhỏ hơn 20
                    ((Box) check).setIsPulled(false);   //đánh dấu box chưa bị kéo
                }
            }
        }
        return this.pulled;
    }
}
