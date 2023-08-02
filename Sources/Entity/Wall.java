package Sources.Entity;

import java.util.Vector;

public class Wall extends Entity{

    public Wall(Vector<Integer> position) {
        super(position);
        this.setGothrough(false);

    }
}
