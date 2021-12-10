package ballboy.model;

import ballboy.Factory.LevelBuilder;
import ballboy.entity.Cloud;

public class GameEngineImpl implements GameEngine{
    GameLevel currentLevel;
    String jsonFilePath;

    public GameEngineImpl(String jsonFile) {
        currentLevel = (GameLevel) new LevelBuilder().buildLevel(jsonFile);

    }





    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void startLevel() {

    }

    @Override
    public boolean boostHeight() {
        return currentLevel.boostHeight();
    }

    @Override
    public boolean dropHeight() {
        return false;
    }

    @Override
    public boolean moveLeft() {
        return currentLevel.moveLeft();
    }

    @Override
    public boolean moveRight() {
        return currentLevel.moveRight();
    }

    @Override
    public boolean stopMoving() {
        return currentLevel.stopMoving();
    }

    @Override
    public void tick() {
        currentLevel.tick();
    }



}
