package ballboy.model;

import ballboy.entity.BallBoy;
import ballboy.entity.Monster;

import java.util.ArrayList;
import java.util.List;

public class GameLevel implements Level{
    double levelHeight;
    double levelWidth;
    double floorHeight;
    double targetX;
    double targetY;
    List<Entity> entities;
    BallBoy ballBoy;
    public static GameLevel gameLevel;

    private static boolean left_is_inside(Entity box1,Entity box2){
        double value = box1.getXPos();
        return value > box2.getXPos() && value < box2.getXPos() + box2.getWidth();

    }

    private static boolean right_is_inside(Entity box1,Entity box2){
        double value = box1.getXPos() + box1.getWidth();
        return value > box2.getXPos() && value < box2.getXPos() + box2.getWidth();
    }



    public boolean getTouchInVertical(Entity a,Entity b){
        return left_is_inside(a, b) || right_is_inside(a,b);
    }

    public boolean getTouchInHorizontal(Entity a,Entity b){

        return Math.max(a.getTop(),b.getTop()) < Math.min(a.getBottom(),b.getBottom());
    }


    public void removeEntity(Entity entity){
        entity.markAsDelete();
    }


    public Entity checkUpOnFootTile(Entity a){
        List<Entity> some_entities = new ArrayList<>();
        some_entities.addAll(entities);
        some_entities.remove(a);

        some_entities = some_entities.stream().filter(e -> getTouchInVertical(a, e)).filter(e -> e.getClass() != Monster.class)
                .filter(e -> e.getLayer() != Entity.Layer.BACKGROUND).
                filter(e -> a.getTop() >= e.getTop() - 4).sorted((o1, o2) -> (int) (o2.getBottom() - o1.getBottom())).toList();

        if(some_entities.size() > 0){
            return some_entities.get(0);
        }
        return null;
    }

    public Entity checkRightOnFootTile(Entity a){
        List<Entity> some_entities = new ArrayList<>();
        some_entities.addAll(entities);
        some_entities.remove(a);

        some_entities = some_entities.stream().filter(e -> getTouchInHorizontal(a, e)).
                filter(e -> e.getLayer() != Entity.Layer.BACKGROUND).
                filter(e -> a.getRight() <= e.getLeft()).sorted((o1, o2) -> (int) (o1.getLeft() - o2.getLeft())).toList();

        if(some_entities.size() > 0){
            return some_entities.get(0);
        }
        return null;
    }

    public Entity checkLeftOnFootTile(Entity a){
        List<Entity> some_entities = new ArrayList<>();
        some_entities.addAll(entities);
        some_entities.remove(a);

        some_entities = some_entities.stream().filter(e -> getTouchInHorizontal(a, e)).
                filter(e -> e.getLayer() != Entity.Layer.BACKGROUND).
                filter(e -> a.getLeft() >= e.getRight()).sorted((o1, o2) -> (int) (o2.getRight() - o1.getRight())).toList();

        if(some_entities.size() > 0){
            return some_entities.get(0);
        }
        return null;
    }


    public Entity checkStandOnFootTile(Entity a){
        List<Entity> some_entities = new ArrayList<>();
        some_entities.addAll(entities);
        some_entities.remove(a);

        some_entities = some_entities.stream().filter(e -> getTouchInVertical(a, e)).
                filter(e -> e.getLayer() != Entity.Layer.BACKGROUND).
                filter(e -> a.getBottom() <= e.getTop() + 4).sorted((o1, o2) -> (int) (o1.getBottom() - o2.getBottom())).toList();

        if(some_entities.size() > 0){
            return some_entities.get(0);
        }
        return null;


    }



    public GameLevel(double levelHeight, double levelWidth, double floorHeight, double targetX, double targetY, ArrayList<Entity> entities, BallBoy ballBoy) {
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;
        this.floorHeight = floorHeight;
        this.targetX = targetX;
        this.targetY = targetY;
        this.entities = entities;
        this.ballBoy = ballBoy;
        gameLevel = this;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public double getLevelHeight() {
        return levelHeight;
    }

    @Override
    public double getLevelWidth() {
        return levelWidth;
    }

    @Override
    public void tick() {
        entities = entities.stream().filter(e -> !e.willDelete()).toList();
        checkCollisionAll();
        entities.forEach(Entity::tick);
    }

    @Override
    public double getFloorHeight() {
        return floorHeight;
    }

    @Override
    public double getHeroX() {
        return ballBoy.getXPos();
    }

    @Override
    public double getHeroY() {
        return ballBoy.getYPos();
    }

    @Override
    public boolean boostHeight() {
        ballBoy.jump();
        return false;
    }

    @Override
    public boolean dropHeight() {
        return false;
    }

    @Override
    public boolean moveLeft() {
        ballBoy.keepMovingLeft();
        return true;
    }

    @Override
    public boolean moveRight() {
        ballBoy.keepMovingRight();
        return false;
    }

    @Override
    public boolean stopMoving() {
        ballBoy.stopMoving();
        return false;
    }

    @Override
    public boolean checkMoveLeft(Entity a) {
        return true;
    }

    @Override
    public boolean checkMoveRight(Entity a) {
        return true;
    }


    @Override
    public boolean checkMoveDown(Entity a) {
        return true;
    }

    public void checkCollisionAll(){
    }



    @Override
    public boolean checkMoveUp(Entity a){
        return true;
    }

}
