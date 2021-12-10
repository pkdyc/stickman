package ballboy.Factory;

import ballboy.entity.BallBoy;
import ballboy.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LevelBuilder {
    private final HashMap<String, EntityType> ballboySizeMap = new HashMap<>();

    public LevelBuilder(){
        ballboySizeMap.put("small",EntityType.BALLBOY_SMALL);
        ballboySizeMap.put("normal",EntityType.BALLBOY_ORDINARY);
        ballboySizeMap.put("large",EntityType.BALLBOY_LARGE);
        ballboySizeMap.put("red",EntityType.MONSTER_RED);
        ballboySizeMap.put("blue",EntityType.MONSTER_BLUE);
        ballboySizeMap.put("purple",EntityType.MONSTER_PURPLE);
        ballboySizeMap.put("green",EntityType.MONSTER_GREEN);
        ballboySizeMap.put("yellow",EntityType.MONSTER_YELLOW);
    }


    private JSONObject parseJsonFile(String filename){
        JSONObject jsonObject = null;
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String content = "";
            while (scanner.hasNextLine()){
                content += scanner.nextLine();
            }
            jsonObject = (JSONObject) new JSONParser().parse(content);
            scanner.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return jsonObject;
    }

    public GameLevel basicLoad(String jsonFile){
        JSONObject jsonObject = parseJsonFile(jsonFile);
        double targetXPos = (double) ((JSONObject) jsonObject.get("targetPos")).get("x");
        double targetYPos = (double) ((JSONObject) jsonObject.get("targetPos")).get("y");
        double levelWidth = (double) jsonObject.get("mapSizeX");
        double levelHeight = (double) jsonObject.get("mapSizeY");
        double speed = (double) jsonObject.get("cloudVelocity");
        double floorHeight = (double)jsonObject.get("floorHeight");
        ArrayList<Entity> entities = new ArrayList<>();
        BallBoy ballBoy = loadBallBoy(jsonObject);
        entities.add(ballBoy);
        return new GameLevel(levelHeight, levelWidth, floorHeight, targetXPos, targetYPos, entities, ballBoy);
    }




    public void loadEntity(String jsonFile,GameLevel gameLevel){
        JSONObject jsonObject = parseJsonFile(jsonFile);
        JSONArray slimes = (JSONArray) jsonObject.get("slimes");
        JSONArray platforms = (JSONArray) jsonObject.get("platforms");
        JSONArray clouds = (JSONArray) jsonObject.get("clouds");
        for (Object slime : slimes) {
            String slimeColour = (String) ((JSONObject) slime).get("colour");
            Double slimeX = (Double) ((JSONObject) slime).get("x");
            Double slimeY = (Double) ((JSONObject) slime).get("y");
            EntityType entityType = ballboySizeMap.get(slimeColour);
            Entity produce = new GameFactory().produce(entityType, slimeX, slimeY);
            gameLevel.getEntities().add(produce);
            System.out.println("loading slimes" + entityType);
        }


        for (Object platform : platforms) {
            double platformX = (double) ((JSONObject) platform).get("x");
            double platformY = (double) ((JSONObject) platform).get("y");
            Entity produce = new GameFactory().produce(EntityType.FOOT_TITLE, platformX, platformY);
            gameLevel.getEntities().add(produce);
        }


        for (Object cloud : clouds) {
            double x = (double) ((JSONObject) cloud).get("x");
            double y = (double) ((JSONObject) cloud).get("y");
            Entity produce = new GameFactory().produce(EntityType.CLOUD1,x, y);
            gameLevel.getEntities().add(produce);

        }
    }


    private BallBoy loadBallBoy(JSONObject jsonObject){
        String ballboySize = (String) jsonObject.get("ballboySize");
        JSONObject ballboyPos = (JSONObject) jsonObject.get("ballboyPos");
        Double xPos_begin = (Double) ballboyPos.get("x");
        Double yPos_begin= (Double) ballboyPos.get("y");
        BallBoy ballBoy = (BallBoy) new GameFactory().produce(ballboySizeMap.get(ballboySize), xPos_begin, yPos_begin);
        return ballBoy;
    }

    public Level buildLevel(String jsonFile){
        GameLevel gameLevel = basicLoad(jsonFile);
        loadEntity(jsonFile,gameLevel);
        return gameLevel;
    }


}
