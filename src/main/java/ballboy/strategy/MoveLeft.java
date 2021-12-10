package ballboy.strategy;

import ballboy.entity.EntityParent;
import ballboy.entity.Monster;

public class MoveLeft implements Movement{
    @Override
    public void move(EntityParent monster) {
        monster.movingLeft = true;
    }



}
