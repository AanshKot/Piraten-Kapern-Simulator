package pk;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.util.*;

public class Strategy {

    //Takes first roll, counts all the faces
    //Decides which face to combo, based on DIAMOND=GOLD>MONKEY=PARROT

    //Lock the Dice, based on the face trying to combo
    //IF SKULL PRESENT in Roll, Lock the dice that rolled skull
    //8-LockedDieSkull-LockedDieCombo
    //Reroll until LockedDieSkull=3



    private static final Logger logger = LogManager.getLogger(Strategy.class);

    public static void combo(Faces[] rolled, Player player1){
        logger.trace(player1.name + " is comboing rolls");
        int count_skulls = 0;
        int locked_combo = 0;
//        initialize tracked face with random value
        Faces tracked_face = Faces.MONKEY;
        Map<Faces,Integer> counted = new HashMap<Faces,Integer>();

        //initializes hashmap
//        counted.put(Faces.SKULL,0);
        counted.put(Faces.GOLD,0);
        counted.put(Faces.DIAMOND,0);
        counted.put(Faces.MONKEY,0);
        counted.put(Faces.SABER,0);
        counted.put(Faces.PARROT,0);

        boolean Playerendturn = false;

        //iterates over the rolled faces in the rolled array, iterates the values based on the number of
        //occurences in the rolled array
        for(int i = 0; i < rolled.length; i++){
            if(counted.containsKey(rolled[i])){
                counted.put(rolled[i], counted.get(rolled[i]) + 1);
            }
            else{
                count_skulls += 1;
            }
        }


        if(count_skulls >= 3){
            logger.trace("you rolled 3 or more skulls your turn is over");
            Playerendturn = true;
        }

        else{

            logger.trace(player1.name + " rolled " + count_skulls +" skulls");


            int max = Collections.max(counted.values());

            //stores key(s) associated with the max value (max rolled value in dynamic array max_faces)
            List<Faces> max_faces = new ArrayList<>();
            for (Map.Entry<Faces, Integer> entry : counted.entrySet()) {
                if (entry.getValue()==max) {
                    max_faces.add(entry.getKey());
                }
            }

            //we'll use the first value in the arraylist max_faces
            logger.trace("locking to first value in arraylist");
            tracked_face = max_faces.get(0);
            logger.trace("locking dies that roll:  " + max_faces.get(0));
            Playerendturn = false;

            locked_combo = counted.get(tracked_face);
////gives bonus of locked die
//            player1.bonus(locked_combo);

            player1.update_score(rolled);
        }



        while(Playerendturn == false){
//            number of rolls remaining is equal to the number of locked die (generating the bonus) and the count_skulls which are locked due to the die rolling skulls
            int roll_num = 8 - count_skulls - locked_combo;
            Faces[] re_rolled;
            re_rolled = player1.my_dies.smart_reroll(roll_num);

            for(int i = 0; i < re_rolled.length; i++){
                if(re_rolled[i] == Faces.SKULL){
                    count_skulls += 1;
                } else if (re_rolled[i] == tracked_face) {
                    locked_combo += 1;
                }
            }

            if(count_skulls >= 3){
                logger.trace(player1.name + " rolled 3 or more skulls, turn over!");
                logger.trace(player1.name + " final score: " + player1.score);
                Playerendturn = true;
            }else if (locked_combo == (8-count_skulls)) {
                player1.update_score(re_rolled);
                player1.bonus(locked_combo);
                Playerendturn = true;
                logger.trace(player1.name + " Finished turn, Final Score: " + player1.score);
            } else {
                player1.update_score(re_rolled);
                player1.bonus(locked_combo);
            }




        }


    }

    public static void random_roll(Faces[] rolled, Player player1){
        logger.trace(player1.name + " is choosing to roll random dies");
        int count_skulls = 0;
        int roll_num = 8;
        boolean Playerendturn;

        Faces tracked_face = Faces.SABER;

//        checks skulls in initial roll (as players initially always start by rolling all 8 die

        for(int i = 0; i < rolled.length; i++){
            if(rolled[i] == Faces.SKULL){
                count_skulls += 1;
            }
        }

        if(count_skulls >= 3){
            logger.trace(player1.name + " rolled 3 or more skulls, your turn is over");
            Playerendturn = true;
        }
        else{
            logger.trace(player1.name + "has rolled " + count_skulls +" skulls in their initial roll");
            roll_num = roll_num - count_skulls;
            player1.update_score(rolled);
            Playerendturn = false;

        }

        while(Playerendturn == false){
//            number of rolls remaining is equal to the number in count_skulls which are locked due to the die rolling skulls
            roll_num = 8 - count_skulls;
            Faces[] re_rolled;
//            player chooses random dies to roll between 1 - and the locked dies
            re_rolled = player1.my_dies.reroll_random_dice(roll_num);

            for(int i = 0; i < re_rolled.length; i++){
                if(re_rolled[i] == Faces.SKULL){
                    count_skulls += 1;
                }
            }

            roll_num = roll_num - count_skulls;

            if(count_skulls >= 3){
                logger.trace(player1.name + " rolled 3 or more skulls! End of turn");
                logger.trace(player1.name + " Final Score: " + player1.score);
                Playerendturn = true;
            }
            else {
                player1.update_score(re_rolled);
            }




        }


    }

//    bonus_score is the score they get/lose from either rolling or not rolling the required amount of sabres
//    in their turn
    public static void seabattle_combo(Faces[] rolled, Player player1,int bonus_score){
        logger.trace(player1.name + " is engaged in a seabattle");

//        optimize the combo strategy, we are going to focus on locking for swords
        int locked_sabre = 0;
        int count_skulls = 0;
        boolean Playerendturn = false;

        int num_to_get = 0;

        if(bonus_score == 300){
            num_to_get = 2;
        } else if (bonus_score == 500) {
            num_to_get = 3;
        } else if (bonus_score == 1000) {
            num_to_get = 4;
        }

        for(int i = 0; i < rolled.length; i++){
            if(rolled[i] == Faces.SABER){
                locked_sabre += 1;
            } else if (rolled[i] == Faces.SKULL) {
                count_skulls += 1;
            }
        }


        if(count_skulls >= 3){
            logger.trace("you rolled 3 or more skulls your turn is over");
//            subtract the bonus score
            player1.score -= bonus_score;
            Playerendturn = true;
        }

        else{

            logger.trace(player1.name + " rolled " + count_skulls +" skulls");
            player1.update_score(rolled);

        }
//        only run this section of code if 3 skulls wasn't rolled in the players first roll
        if(Playerendturn == false) {

//            run this section of the code if you already won the sea battle in your first roll
            if (locked_sabre >= num_to_get) {
                player1.score += bonus_score;
                logger.trace("You won the sea battle! you get a bonus of: " + bonus_score );

//                now roll as a regular combo player would roll

                int locked_combo = 0;
//        initialize tracked face with random value
                Faces tracked_face = Faces.MONKEY;
                Map<Faces, Integer> counted = new HashMap<Faces, Integer>();

                //initializes hashmap
//        counted.put(Faces.SKULL,0);
                counted.put(Faces.GOLD, 0);
                counted.put(Faces.DIAMOND, 0);
                counted.put(Faces.MONKEY, 0);
                counted.put(Faces.SABER, 0);
                counted.put(Faces.PARROT, 0);

                for(int i = 0; i < rolled.length; i++) {
                    if (counted.containsKey(rolled[i])) {
                        counted.put(rolled[i], counted.get(rolled[i]) + 1);
                    }
//                    already counted the number of skulls previously
                }

                int max = Collections.max(counted.values());

                //stores key(s) associated with the max value (max rolled value in dynamic array max_faces)
                List<Faces> max_faces = new ArrayList<>();
                for (Map.Entry<Faces, Integer> entry : counted.entrySet()) {
                    if (entry.getValue()==max) {
                        max_faces.add(entry.getKey());
                    }
                }

//                logger.trace("locking to first value in arraylist");
                tracked_face = max_faces.get(0);
                logger.trace("locking die that rolled: " + max_faces.get(0));


                locked_combo = counted.get(tracked_face);

                while(Playerendturn == false){
//            number of rolls remaining is equal to the number of locked die (generating the bonus) and the count_skulls which are locked due to the die rolling skulls
                    int roll_num = 8 - count_skulls - locked_combo;
                    Faces[] re_rolled;
                    re_rolled = player1.my_dies.smart_reroll(roll_num);

                    for(int i = 0; i < re_rolled.length; i++){
                        if(re_rolled[i] == Faces.SKULL){
                            count_skulls += 1;
                        } else if (re_rolled[i] == tracked_face) {
                            locked_combo += 1;
                        }
                    }

                    if(count_skulls >= 3){
                        logger.trace("You rolled 3 or more skulls! end of turn score: " + player1.score);
                        Playerendturn = true;
                    }else if (locked_combo == (8-count_skulls)) {
//                        player1.update_score(re_rolled);
//                        player1.bonus(locked_combo);
                        Playerendturn = true;
                        logger.trace("Finished turn, Score: " + player1.score);
                    } else {
                        player1.update_score(re_rolled);
                        player1.bonus(locked_combo);
                    }

                }

            }
            else if (locked_sabre < num_to_get) {


                while (Playerendturn == false) {
//              number of dice remaining is equal to the number of locked die (generating the bonus) and the count_skulls which are locked due to the die rolling skulls
                    int roll_num = 8 - count_skulls - locked_sabre;
                    Faces[] re_rolled;
                    re_rolled = player1.my_dies.smart_reroll(roll_num);

                    for (int i = 0; i < re_rolled.length; i++) {
                        if (re_rolled[i] == Faces.SKULL) {
                            count_skulls += 1;
                        } else if (re_rolled[i] == Faces.SABER) {
                            locked_sabre += 1;
                        }
                    }


                    if (count_skulls >= 3) {
                        player1.score -= bonus_score;
                        logger.trace("You rolled 3 or more skulls! end of turn score: " + player1.score);

                        Playerendturn = true;
                    }

//                    note that if the user reaches the number of sabers he will stop rolling here
                    if(locked_sabre >= num_to_get && Playerendturn == false){
                        logger.trace("You won the sea battle! you get a bonus of: " + bonus_score );

                        player1.score += bonus_score;
                        player1.update_score(re_rolled);
                        player1.bonus(locked_sabre);

                        Playerendturn = true;

                        logger.trace("Finished turn,Score: " + player1.score);
                    }

//                    else {
//                        player1.update_score(re_rolled);
//                        player1.bonus(locked_sabre);
//                    }


                }
            }
        }
    }

    public static void monkey_business(Faces[] rolled, Player player1){
        logger.trace(player1.name + " is maximizing monkey/parrot combo");
        int locked_die = 0;
        int count_skulls = 0;
        boolean Playerendturn = false;



        for(int i = 0; i < rolled.length; i++){
            if(rolled[i] == Faces.PARROT || rolled[i] == Faces.MONKEY){
                locked_die += 1;

            } else if (rolled[i] == Faces.SKULL) {
                count_skulls += 1;
            }
        }

        if (count_skulls >= 3) {
            logger.trace("You rolled 3 or more skulls");
            logger.trace( player1.name + " turn is over");
            Playerendturn = true;
        }
        else{

            logger.trace(player1.name + " rolled " + count_skulls + " skulls");
            player1.update_score_monkeybusiness(rolled);

//            logger.trace(player1.name + "Current Score: " + player1.score);

        }

        while(Playerendturn == false){
//            number of rolls remaining is equal to the number of locked die (generating the bonus) and the count_skulls which are locked due to the die rolling skulls
            int roll_num = 8 - count_skulls - locked_die;
            Faces[] re_rolled;
            re_rolled = player1.my_dies.smart_reroll(roll_num);

            for(int i = 0; i < re_rolled.length; i++){
                if(re_rolled[i] == Faces.SKULL){
                    count_skulls += 1;
                } else if (re_rolled[i] == Faces.MONKEY || re_rolled[i] == Faces.PARROT) {
                    locked_die += 1;
                }
            }

            if(count_skulls >= 3){
                logger.trace(player1.name + " rolled 3 or more skulls, turn over!");
                logger.trace(player1.name + " final score: " + player1.score);
                Playerendturn = true;
            }else if (locked_die == (8-count_skulls)) {
                player1.update_score_monkeybusiness(re_rolled);
                player1.bonus(locked_die);
                Playerendturn = true;
                logger.trace(player1.name + " Finished turn, Final Score: " + player1.score);
            } else {
                player1.update_score(re_rolled);
                player1.bonus(locked_die);
            }




        }







    }




}

