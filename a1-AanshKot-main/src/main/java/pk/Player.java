package pk;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class Player {

    public int score;
    public Dice my_dies;

    private static final Logger logger = LogManager.getLogger(Player.class);

//    user will input combo strategy for each player in the command line
    String name;

    //constructor
    public Player(String name){
        this.score = 0;
        this.my_dies = new Dice();
        this.name = name;
    }



    //feature 6; 3-of-a-kind scoring mechanism
//    non-static method because an instance of the player class has a score specific to the player instance
    //update score only updates score for diamond and gold
    public void update_score(Faces[] rolled){

        Map<Faces,Integer> score_map = new HashMap<Faces,Integer>();

        score_map.put(Faces.GOLD,0);
        score_map.put(Faces.DIAMOND,0);
        score_map.put(Faces.MONKEY,0);
        score_map.put(Faces.SABER,0);
        score_map.put(Faces.PARROT,0);

        for(int i = 0; i < rolled.length; i++){
            if(score_map.containsKey(rolled[i])){
                score_map.put(rolled[i], score_map.get(rolled[i]) + 1);
            }
        }

        for(int i = 0; i<score_map.get(Faces.GOLD); i++){
            this.score += 100;
        }

        for(int i = 0; i<score_map.get(Faces.DIAMOND); i++){
            this.score += 100;
        }

        logger.trace("Base roll Score: " + this.score);

        logger.trace("Updating Score...");

        if (score_map.get(Faces.GOLD) >= 3){
            this.bonus(score_map.get(Faces.GOLD));
        }

        if (score_map.get(Faces.DIAMOND) >= 3){
            this.bonus(score_map.get(Faces.DIAMOND));
        }

        if (score_map.get(Faces.MONKEY) >= 3){
            this.bonus(score_map.get(Faces.MONKEY));
        }

        if (score_map.get(Faces.PARROT) >= 3){
            this.bonus(score_map.get(Faces.PARROT));
        }

        if (score_map.get(Faces.SABER) >= 3){
            this.bonus(score_map.get(Faces.SABER));
        }


        logger.trace(this.name + " Current Score: " + this.score);
    }

    public void update_score_monkeybusiness(Faces[] rolled){
        int count_monkey_parrot = 0;

        Map<Faces,Integer> score_map = new HashMap<Faces,Integer>();

//        NOTE THAT I Did not add the Faces.Parrot as a key as we are considering it to equal Faces.Monkey when the user draws a monkey business card
        score_map.put(Faces.GOLD,0);
        score_map.put(Faces.DIAMOND,0);
        score_map.put(Faces.MONKEY,0);
        score_map.put(Faces.SABER,0);
        score_map.put(Faces.PARROT,0);

        for(int i = 0; i < rolled.length; i++){
            if(score_map.containsKey(rolled[i])){
                score_map.put(rolled[i], score_map.get(rolled[i]) + 1);
            }
        }

        for(int i = 0; i<score_map.get(Faces.GOLD); i++){
            this.score += 100;
        }

        for(int i = 0; i<score_map.get(Faces.DIAMOND); i++){
            this.score += 100;
        }
        logger.trace("Base roll Score: " + this.score);

        logger.trace("Updating Score...");

        if (score_map.get(Faces.GOLD) >= 3){
            this.bonus(score_map.get(Faces.GOLD));
        }

        if (score_map.get(Faces.DIAMOND) >= 3){
            this.bonus(score_map.get(Faces.DIAMOND));
        }

        if ((score_map.get(Faces.MONKEY) + score_map.get(Faces.PARROT)) >= 3){
            this.bonus(score_map.get(Faces.MONKEY) + score_map.get(Faces.PARROT));
        }

        if (score_map.get(Faces.SABER) >= 3){
            this.bonus(score_map.get(Faces.SABER));
        }


        logger.trace(this.name + " Current Score: " + this.score);
    }

    public void bonus(int num_combo){
        if(num_combo == 3){
            this.score += 100;
        } else if (num_combo == 4) {
            this.score += 200;
        }else if (num_combo == 5) {
            this.score += 500;
        }
        else if (num_combo == 6) {
            this.score += 1000;
        }
        else if (num_combo == 7) {
            this.score += 2000;
        }
        else if (num_combo == 8) {
            this.score += 4000;
        }

        logger.trace("Bonus added: " + this.score);

//        logger.trace(this.name + " Current Score: " + this.score);
    }


    public int check_three_skulls(Faces[] rolled){

        int count = 0;

        for(int i = 0; i < rolled.length; i++){
//            enum converted to string to check if the value is "SKULL"
            if(rolled[i].toString() == "SKULL"){
                count += 1;
            }
        }

        return count;
    }

    public String draw_card(){
        logger.trace(this.name + " is drawing a card");
        String card = DeckofCards.drawCard();

        return card;
    }



}
