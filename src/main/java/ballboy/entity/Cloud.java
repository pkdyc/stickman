package ballboy.entity;

import ballboy.strategy.Movement;

public class Cloud extends EntityParent{
    Movement movementStrategy;
    public Cloud(double xPos, double yPos, double height, double width, String imagePath, Layer layer , Movement movement) {
        super(xPos, yPos, height, width, imagePath, layer);
        x_velocity = 1;
        this.movementStrategy = movement;
    }
    @Override
    public void tick() {

        left_closest = null;
        right_closest = null;
        up_closest = null;
        down_closest = null;
        floorHeight = getYPos();
        if(movementStrategy != null){
            movementStrategy.move(this);
        }
        movement();

    }

    public void movement(){
        moveLeftAndRight();
    }
}
