package ballboy.Factory;

import ballboy.entity.BallBoy;
import ballboy.model.Entity;
import ballboy.model.Level;

public class BallBoyFactory implements Factory {

    @Override
    public Entity produce(EntityType type, Double xPos, Double yPos) {

        String imagePath = "ch_stand1.png";

        switch (type){
            case BALLBOY_SMALL:
                return new BallBoy(xPos,yPos,40,30 - 7,imagePath,Entity.Layer.FOREGROUND);
            case BALLBOY_ORDINARY:
                return new BallBoy(xPos,yPos,40 * 1.3,30 * 1.3,imagePath,Entity.Layer.FOREGROUND);
            case BALLBOY_LARGE:
                return new BallBoy(xPos,yPos,40 * 1.7,30 * 1.7,imagePath,Entity.Layer.FOREGROUND);
        }
        return null;
    }
}
