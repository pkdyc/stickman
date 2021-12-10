to run the code : use "gradle run" or "/gradlew run"

json format : a bracket which includes all the map information
              all the regular attribute list on the first level
              all the entity attribute list on the second level

{
  "ballboySize": "small",
  "ballboyPos": {
    "x": 20.0,
    "y": 300.0
  },
  "cloudVelocity": 3.2,

  "clouds" : [
    {
      "x" : 60.0,
      "y" : 100.0
    },
    {
      "x" : 80.0,
      "y" : 50.0
    }
  ],

the contents above is an example of my json file
it is obviously that all the clouds entities are inside an array
and the regular attribute such as cloud speed or ballBoy size are listed
on the first levels

Factory design pattern :   BackGroundEntityFactory.java
                           BallBoyFactory.java
                           Factory.java
                           GameFactory.java

Builder design patten :    LevelBuilder.java

Strategy design pattern :  Movement.java
                           MoveRight.java
                           MoveLeft.java
                           Stand.java
                           Tracking.java

