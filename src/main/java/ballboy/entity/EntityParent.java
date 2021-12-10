package ballboy.entity;

import ballboy.model.Entity;
import ballboy.model.GameLevel;
import javafx.scene.image.Image;

public class EntityParent implements Entity {
    Image image;
    double xPos;
    double yPos;
    double height;
    boolean deleted;
    double width;
    public boolean movingLeft;
    public boolean movingRight;
    public Entity up_closest = null;
    public Entity down_closest = null;
    public Entity left_closest = null;
    public Entity right_closest = null;
    double y_velocity = 0;
    double x_velocity = 1;
    public double floorHeight = 300;
    double upLimit = -100;
    double gravity = 0.2;

    String imagePath;
    Layer layer;

    public EntityParent(double xPos, double yPos, double height, double width, String imagePath, Layer layer) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.imagePath = imagePath;
        this.layer = layer;
        this.image = new Image(imagePath);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public Layer getLayer() {
        return layer;
    }

    @Override
    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    @Override
    public void setYPos(double yPos) {
        this.yPos = yPos;
    }



    @Override
    public void tick() {

    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public double getTop() {
        return yPos - height;
    }

    @Override
    public double getBottom() {
        return yPos;
    }

    @Override
    public double getLeft() {
        return xPos;
    }

    @Override
    public double getRight() {
        return xPos + width;
    }


    public void checkCollisionAll(){
        down_closest = GameLevel.gameLevel.checkStandOnFootTile(this);
        left_closest = GameLevel.gameLevel.checkLeftOnFootTile(this);
        right_closest = GameLevel.gameLevel.checkRightOnFootTile(this);
        up_closest = GameLevel.gameLevel.checkUpOnFootTile(this);
    }

    @Override
    public void markAsDelete() {
        deleted = true;
    }

    @Override
    public boolean willDelete() {
        return deleted;
    }


    public void movement(){
        checkCollisionAll();
        moveUpAndDown();
        moveLeftAndRight();
    }


    private void moveLeft(){
        xPos -= x_velocity;
    }

    private void moveRight(){
        xPos += x_velocity;
    }

    public void moveLeftAndRight(){
        if(movingLeft && !(left_closest != null && getLeft() <= left_closest.getRight())){
            moveLeft();
        }

        if(movingRight && !(right_closest != null && getRight() >= right_closest.getLeft() && right_closest != down_closest)){
            moveRight();
        }
    }

    public void moveUpAndDown(){
        // if stand on

        if(down_closest != null && y_velocity >= 0){
            floorHeight = down_closest.getTop();
        }else {
            floorHeight = 300;
        }

        if(up_closest != null){
            upLimit = up_closest.getBottom();
        }else {
            upLimit = -100;
        }

        if(this.yPos + y_velocity >= (floorHeight))  {
            this.y_velocity = 0;
            this.setYPos(floorHeight);
            return;
        }

        if( this.getTop() + y_velocity <= upLimit){
            this.y_velocity = 0;
            this.yPos = upLimit + height + 3;
            return;
        }

        this.y_velocity = this.y_velocity + gravity;
        this.yPos += y_velocity;



    }


}
