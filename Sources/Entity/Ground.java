package Sources.Entity;

import java.util.Vector;

public class Ground extends Entity {
    public Ground(Vector<Integer> position) {
        super(position);
        this.setGothrough(true);
    }
    
}
