package pk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private static final Logger logger = LogManager.getLogger(Player.class);

    public Faces roll() {
        int howManyFaces = Faces.values().length;
//        System.out.println("  (DEBUG) there are " + howManyFaces + " faces");
//        System.out.println("  (DEBUG) " + Arrays.toString(Faces.values()));
        Random bag = new Random();
        return Faces.values()[bag.nextInt(howManyFaces)];
    }

    //Feature 1
    public Faces[] roll_eight(){

        Dice[] eight_die = new Dice[8];

        for (int i = 0; i < eight_die.length;i++){
            eight_die[i] = new Dice();
        }

        //roll 8 die and store them in rolled array

        Faces[] rolled = new Faces[8];
        int[] rolled_int = new int[8];

        for(int i = 0; i < eight_die.length; i++){
            rolled[i] = eight_die[i].roll();
        }

//        logger.trace(Arrays.toString(rolled));

        return rolled;
    }

    public Faces[] reroll_random_dice(int num_die){
        int min = 1;
        int max = num_die;

        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        logger.trace("You have chose to roll "+ randomNum + "\tdie(s)");

        Dice[] random_die = new Dice[randomNum];

        for (int i = 0; i < random_die.length;i++){
            random_die[i] = new Dice();
        }

        //roll random number of die and store them in rolled array

        Faces[] rolled = new Faces[randomNum];

        for(int i = 0; i < random_die.length; i++){
            rolled[i] = random_die[i].roll();
        }

        //checks which die rolled what
       logger.trace(Arrays.toString(rolled));

        return rolled;
    }

    public Faces[] smart_reroll(int unlocked_die){

        Faces[] rolled = new Faces[unlocked_die];
        Dice smart_die = new Dice();

        for(int i = 0; i < unlocked_die; i++){
            rolled[i] = smart_die.roll();
        }

        logger.trace(Arrays.toString(rolled));

        return rolled;
    }
//    public Faces[] combo_dice(){
//
//
//
//    }
    
}
