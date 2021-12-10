package ballboy.entity;

import ballboy.strategy.Movement;

public class Monster extends EntityParent{
    Double blood;
    Movement movementStrategy;
    public Monster(double xPos, double yPos, double height, double width, String imagePath, Layer layer, Double blood, Movement movementStraegy) {
        super(xPos, yPos, height, width, imagePath, layer);
        System.out.println("load image for monster");
        this.blood = blood;
        this.movementStrategy = movementStraegy;
    }

    @Override
    public void tick() {
        if(movementStrategy != null){
            movementStrategy.move(this);
        }
        movement();

    }
}
