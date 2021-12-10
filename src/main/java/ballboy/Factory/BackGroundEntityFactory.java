package ballboy.Factory;





import ballboy.entity.Cloud;
import ballboy.entity.FootTile;
import ballboy.entity.Monster;
import ballboy.entity.Tree;
import ballboy.model.Entity;
import ballboy.strategy.MoveLeft;
import ballboy.strategy.MoveRight;
import ballboy.strategy.Stand;
import ballboy.strategy.Tracking;


public class BackGroundEntityFactory implements Factory{
    Integer monsterHeight = 20;
    Integer monsterWidth = 35;

    @Override
    public Entity produce(EntityType type, Double xPos, Double yPos) {
        String imagePath;
        Cloud cloud;
        Monster monster;
        switch (type){
            case CLOUD1:
                imagePath = "cloud_1.png";
                cloud = new Cloud(xPos, yPos, 30, 30, imagePath,Entity.Layer.BACKGROUND,new MoveRight());
                return cloud;
            case CLOUD2:
                imagePath = "cloud_2.png";
                cloud = new Cloud(xPos, yPos, 30, 30, imagePath,Entity.Layer.BACKGROUND, new MoveRight());
                return cloud;
            case MONSTER_BLUE:
                imagePath = "slimeBa.png";
                monster = new Monster(xPos, yPos, monsterHeight, monsterWidth, imagePath,Entity.Layer.FOREGROUND,1.00,new Stand());
                return monster;
            case MONSTER_GREEN:
                imagePath = "slimeGa.png";
                monster = new Monster(xPos, yPos, monsterHeight, monsterWidth, imagePath,Entity.Layer.FOREGROUND,2.00,new Stand());
                return monster;
            case MONSTER_PURPLE:
                imagePath = "slimePa.png";
                monster = new Monster(xPos, yPos, monsterHeight, monsterWidth, imagePath, Entity.Layer.FOREGROUND,3.00,new Stand());
                return monster;
            case MONSTER_RED:
                imagePath = "slimeRa.png";
                monster = new Monster(xPos, yPos, monsterHeight, monsterWidth, imagePath,Entity.Layer.FOREGROUND,4.00,new Tracking());
                return monster;
            case MONSTER_YELLOW:
                imagePath = "slimeYa.png";
                monster = new Monster(xPos, yPos, monsterHeight, monsterWidth, imagePath, Entity.Layer.FOREGROUND,3.00,new MoveLeft());
                return monster;
            case TREE:
                imagePath = "tree.png";
                Tree tree = new Tree(xPos, yPos, 40, 30, imagePath, Entity.Layer.BACKGROUND);
                return tree;
            case FOOT_TITLE:
                imagePath = "foot_tile.png";
                FootTile footTile = new FootTile(xPos, yPos, 32, 32, imagePath, Entity.Layer.FOREGROUND);
                return footTile;
            default:
                System.out.println("loading entity incorrectly");
                System.exit(1);
        }
        return null;
    }
}
