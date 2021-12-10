package ballboy.Factory;

import ballboy.model.Entity;
import ballboy.model.Level;

import java.util.HashMap;

public class GameFactory implements Factory{
    HashMap<EntityType, Factory> factoryList = new HashMap<>();

    public GameFactory() {
        factoryList.put(EntityType.BALLBOY_SMALL,new BallBoyFactory());
        factoryList.put(EntityType.BALLBOY_ORDINARY,new BallBoyFactory());
        factoryList.put(EntityType.BALLBOY_LARGE,new BallBoyFactory());
    }


    @Override
    public Entity produce(EntityType type, Double xPos, Double yPos) {
        if(factoryList.get(type) != null){
            return factoryList.get(type).produce(type,xPos,yPos);
        }else {
            return new BackGroundEntityFactory().produce(type,xPos,yPos);
        }

    }
}
