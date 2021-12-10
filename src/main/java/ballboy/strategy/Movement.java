package ballboy.strategy;

import ballboy.entity.EntityParent;
import ballboy.entity.Monster;
import ballboy.model.Entity;

public interface Movement {
    void move(EntityParent entity);
}
