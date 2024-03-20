package Sources;
import java.io.Serializable;

//Dinh nghia lop GameDataStore : luu tru du lieu game
public class GameDataStore implements Serializable {
    private static final long serialVersionUID = 1L;
	public int mapunlock = 0;
    public int getMapunlock() {
        return mapunlock;
    }
    public void setMapunlock(int mapunlock) {
        this.mapunlock = mapunlock;
    }

}
