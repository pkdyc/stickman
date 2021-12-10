package ballboy.Factory;

import ballboy.model.Entity;
import ballboy.model.Level;

public interface Factory {
    Entity produce(EntityType type, Double xPos, Double yPos);
}
