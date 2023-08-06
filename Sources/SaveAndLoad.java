package Sources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAndLoad {
    protected GamePanel gamepanel;
    public SaveAndLoad(GamePanel gamepanel){
        this.gamepanel = gamepanel;
    }

        public void save(){
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
                GameDataStore gameDataStore = new GameDataStore();
                gameDataStore.mapunlock = this.gamepanel.getGameDataStore().getMapunlock();
                oos.writeObject(gameDataStore);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        public void load(){
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));
                GameDataStore dataStore = (GameDataStore) ois.readObject();
                this.gamepanel.getGameDataStore().mapunlock = dataStore.mapunlock;
            
            }catch(Exception e){
                e.printStackTrace();
            }
        }
}
