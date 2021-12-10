package ballboy.strategy;

import ballboy.entity.EntityParent;
import ballboy.entity.Monster;
import ballboy.model.Entity;

public class MoveRight implements Movement{
    @Override
    public void move(EntityParent entityParent) {
        entityParent.movingRight = true;
    }



}
