package Sources.Entity;

import java.util.Vector;


// định nghĩa lớp Wall(tường) kế thừa từ lớp Entity
public class Wall extends Entity{

    public Wall(Vector<Integer> position) {
        super(position);
        this.setGothrough(false);

    }
}
