package Sources.Entity;

import java.util.Vector;

// định nghĩa lớp Ground(nền) kế thừa từ lớp Entity
public class Ground extends Entity {
    public Ground(Vector<Integer> position) {
        super(position);
        this.setGothrough(true);
    }
    
}
