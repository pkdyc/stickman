package ballboy.strategy;

import ballboy.entity.EntityParent;
import ballboy.entity.Monster;
import ballboy.model.GameLevel;

public class Tracking implements Movement{
    @Override
    public void move(EntityParent monster) {
        double heroX = GameLevel.gameLevel.getHeroX();
        monster.movingLeft = false;
        monster.movingRight = false;
        if(monster.getXPos() < heroX){
            monster.movingRight = true;
            return;
        }

        if(monster.getXPos() > heroX){
            monster.movingLeft = true;
            return;
        }

    }
}
