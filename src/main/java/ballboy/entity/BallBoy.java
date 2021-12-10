package ballboy.entity;

import ballboy.model.Entity;
import ballboy.model.GameLevel;
import javafx.scene.image.Image;

import java.util.HashMap;

public class BallBoy extends EntityParent {

    public int frameSpeed = 0;
    HashMap<Integer, Image> movingImage = new HashMap<>();
    int movingFrame = 0;
    private double x_original;
    private double y_original;




    private void encounterEnemy(){
        if(right_closest instanceof Monster && movingRight){
            if(getRight() >= right_closest.getLeft()){
                xPos = x_original;
                yPos = y_original;
            }
            return;
        }



        if(left_closest instanceof Monster && movingLeft){
            if(getLeft() <= left_closest.getRight()){
                xPos = x_original;
                yPos = y_original;
            }
        }


        if(down_closest instanceof Monster && y_velocity >= 0){
            if(floorHeight == getBottom()){
                GameLevel.gameLevel.removeEntity(down_closest);
            }
        }


    }





    public BallBoy(double xPos, double yPos, double height, double width, String imagePath, Layer layer) {
        super(xPos, yPos, height, width, imagePath, layer);
        movingImage.put(0,new Image("ch_walk1.png"));
        movingImage.put(1,new Image("ch_walk2.png"));
        movingImage.put(2,new Image("ch_walk3.png"));
        movingImage.put(3,new Image("ch_walk4.png"));
        movingImage.put(4,new Image("ch_walk5.png"));
        movingImage.put(5,new Image("ch_walk6.png"));
        movingImage.put(6,new Image("ch_walk7.png"));
        movingImage.put(7,new Image("ch_walk8.png"));
        this.x_original = xPos;
        this.y_original = yPos;


    }
    public void stopMoving(){
        if(movingLeft){
            stopMovingLeft();
        }else {
            stopMovingRight();
        }
    }

    public void keepMovingLeft(){
        movingLeft = true;
    }

    public void keepMovingRight(){
        movingRight = true;
    }

    private void stopMovingLeft(){
        movingLeft = false;
        image = new Image("ch_stand4.png");
    }

    private void stopMovingRight(){
        movingRight = false;
        image = new Image("ch_stand1.png");
    }


    public void jump(){
        y_velocity = -5;
    }


    @Override
    public void tick() {
        x_velocity = 1;
        movement();
        encounterEnemy();
        frameSpeed += 1;
        if((movingLeft || movingRight) && frameSpeed % 3 == 0){
            int factor = movingRight?0:4;
            image = movingImage.get(factor + movingFrame % 4);
            movingFrame++;
        }
    }






    public BallBoy(){
        super(300,200,40,30,"ch_stand1.png",Layer.FOREGROUND);
    }

}
