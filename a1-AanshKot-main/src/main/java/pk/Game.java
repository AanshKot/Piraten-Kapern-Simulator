package pk;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Game {



    private static final Logger logger = LogManager.getLogger(Game.class);

    public int num_games(){
        //Feature 3
        Scanner games = new Scanner(System.in);

        System.out.println("Please input the number of games that you want to play: ");
        int number_games = games.nextInt();
        System.out.println("Number of games:\t" + number_games);

        return number_games;
    }

    public static void sim_game(String player_strat1, String player_strat2,Player player1, Player player2, int num_games){
        int count1 = 0;
        int count2 = 0;
        int countdraw = 0;


//        System.out.println(count1);
//        System.out.println(count2);
//        System.out.println(countdraw);

        if(player_strat1.equals("random") && player_strat2.equals("random")) {

            for (int i = 0; i < num_games; i++) {
                player1.score = 0;
                player2.score = 0;

                logger.trace(player1.name + " is drawing a card...");
                String card1 = player1.draw_card();

                logger.trace(player2.name + " is drawing a card");
                String card2 = player2.draw_card();

//                split the card1 into Seabattle and the score gained from rolling said skulls

//                initial rolls
                Faces[] rolled = player1.my_dies.roll_eight();
                Faces[] rolled2 = player2.my_dies.roll_eight();

                logger.trace(player1.name + " initial roll " + Arrays.toString(rolled));
                logger.trace(player2.name + " initial roll " + Arrays.toString(rolled2));

//                going to put if the card drawn at the start of each game is Seabattle the combo player will optimize for sabers
//                we will call the Strategy of player1 strategy.seabattle(rolled,player1);

                if ((card1.equals("SEABATTLE-300")  || card1.equals("SEABATTLE-500") || card1.equals("SEABATTLE-1000")) && (card2.equals("SEABATTLE-300")  || card2.equals("SEABATTLE-500") || card2.equals("SEABATTLE-1000"))) {
                    String[] arrOfStr = card1.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);

                    String[] arrofStr2 = card2.split("-");
                    int score_bonus2 = Integer.parseInt(arrofStr2[1]);

//                    if the random roller draws a sea battle card he/she will switch to using a sea_battle combo strategy
                    Strategy.seabattle_combo(rolled, player1, score_bonus);
                    Strategy.seabattle_combo(rolled2, player2, score_bonus2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card1.equals("SEABATTLE-300")  || card1.equals("SEABATTLE-500") || card1.equals("SEABATTLE-1000")) {
                    String[] arrOfStr = card1.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);

                    Strategy.seabattle_combo(rolled, player1, score_bonus);
                    Strategy.random_roll(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card2.equals("SEABATTLE-300")  || card2.equals("SEABATTLE-500") || card2.equals("SEABATTLE-1000")) {
                    String[] arrOfStr = card2.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);


                    Strategy.random_roll(rolled, player1);
                    Strategy.seabattle_combo(rolled2, player2, score_bonus);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if ((card1.equals("MONKEYBUSINESS")&& card2.equals("MONKEYBUSINESS"))) {

//                    if the random roller draws a sea battle card he/she will switch to using a sea_battle combo strategy
                    Strategy.monkey_business(rolled, player1);
                    Strategy.monkey_business(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card1.equals("MONKEYBUSINESS")) {

                    Strategy.monkey_business(rolled, player1);
                    Strategy.random_roll(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card2.equals("MONKEYBUSINESS")) {

                    Strategy.random_roll(rolled, player1);
                    Strategy.monkey_business(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }


                else {
                    Strategy.random_roll(rolled, player1);
                    Strategy.random_roll(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }

            }

            float percent1 = ((float) count1 / (float) (count1 + count2 + countdraw)) * 100;
            float percent2 = ((float) count2 / (float) (count1 + count2 + countdraw)) * 100;
            float percent_draw = ((float) countdraw / (float) (count1 + count2 + countdraw)) * 100;


            System.out.println("Player 1 win percentage: " + percent1);
            System.out.println("Player 2 win percentage: " + percent2);
            System.out.println("Percentage of games drawn:" + percent_draw);
        }

        else if(player_strat1.equals("combo") && player_strat2.equals("random")) {

            for (int i = 0; i < num_games; i++) {
                player1.score = 0;
                player2.score = 0;

//                logger.trace(player1.name + " is drawing a card...");
                String card1 = player1.draw_card();

//                logger.trace(player2.name + " is drawing a card");
                String card2 = player2.draw_card();

//                split the card1 into Seabattle and the score gained from rolling said skulls

//                initial rolls
                Faces[] rolled = player1.my_dies.roll_eight();
                Faces[] rolled2 = player2.my_dies.roll_eight();

                logger.trace(player1.name + " initial roll " + Arrays.toString(rolled));
                logger.trace(player2.name + " initial roll " + Arrays.toString(rolled2));

//                going to put if the card drawn at the start of each game is Seabattle the combo player will optimize for sabers
//                we will call the Strategy of player1 strategy.seabattle(rolled,player1);

                if ((card1.equals("SEABATTLE-300")  || card1.equals("SEABATTLE-500") || card1.equals("SEABATTLE-1000")) && (card2.equals("SEABATTLE-300")  || card2.equals("SEABATTLE-500") || card2.equals("SEABATTLE-1000"))) {
                    String[] arrOfStr = card1.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);

                    String[] arrofStr2 = card2.split("-");
                    int score_bonus2 = Integer.parseInt(arrofStr2[1]);

//                    if the random roller draws a sea battle card he/she will switch to using a sea_battle combo strategy
                    Strategy.seabattle_combo(rolled, player1, score_bonus);
                    Strategy.seabattle_combo(rolled2, player2, score_bonus2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card1.equals("SEABATTLE-300")  || card1.equals("SEABATTLE-500") || card1.equals("SEABATTLE-1000")) {
                    String[] arrOfStr = card1.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);

                    Strategy.seabattle_combo(rolled, player1, score_bonus);
                    Strategy.random_roll(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card2.equals("SEABATTLE-300")  || card2.equals("SEABATTLE-500") || card2.equals("SEABATTLE-1000")) {
                    String[] arrOfStr = card2.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);


                    Strategy.combo(rolled, player1);
                    Strategy.seabattle_combo(rolled2, player2, score_bonus);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }

                else if ((card1.equals("MONKEYBUSINESS")&& card2.equals("MONKEYBUSINESS"))) {

//                    if the random roller draws a sea battle card he/she will switch to using a sea_battle combo strategy
                    Strategy.monkey_business(rolled, player1);
                    Strategy.monkey_business(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if ((card1.equals("MONKEYBUSINESS"))) {

                    Strategy.monkey_business(rolled, player1);
                    Strategy.random_roll(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if ((card2.equals("MONKEYBUSINESS"))){

                    Strategy.combo(rolled, player1);
                    Strategy.monkey_business(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }


                else {
                    Strategy.combo(rolled, player1);
                    Strategy.random_roll(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");

                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");

                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }


            }

            float percent1 = ((float) count1 / (float) (count1 + count2 + countdraw)) * 100;
            float percent2 = ((float) count2 / (float) (count1 + count2 + countdraw)) * 100;
            float percent_draw = ((float) countdraw / (float) (count1 + count2 + countdraw)) * 100;


            System.out.println("Player 1 win percentage: " + percent1);
            System.out.println("Player 2 win percentage: " + percent2);
            System.out.println("Percentage of games drawn:" + percent_draw);
        }

        else if(player_strat1.equals("random") && player_strat2.equals("combo")) {

            for (int i = 0; i < num_games; i++) {
                player1.score = 0;
                player2.score = 0;


                String card1 = player1.draw_card();


                String card2 = player2.draw_card();

//                split the card1 into Seabattle and the score gained from rolling said skulls

//                initial rolls
                Faces[] rolled = player1.my_dies.roll_eight();
                Faces[] rolled2 = player2.my_dies.roll_eight();

                logger.trace(player1.name + " initial roll " + Arrays.toString(rolled));
                logger.trace(player2.name + " initial roll " + Arrays.toString(rolled2));

//                going to put if the card drawn at the start of each game is Seabattle the combo player will optimize for sabers
//                we will call the Strategy of player1 strategy.seabattle(rolled,player1);

                if ((card1.equals("SEABATTLE-300")  || card1.equals("SEABATTLE-500") || card1.equals("SEABATTLE-1000")) && (card2.equals("SEABATTLE-300")  || card2.equals("SEABATTLE-500") || card2.equals("SEABATTLE-1000"))) {
                    String[] arrOfStr = card1.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);

                    String[] arrofStr2 = card2.split("-");
                    int score_bonus2 = Integer.parseInt(arrofStr2[1]);

//                    if the random roller draws a sea battle card he/she will switch to using a sea_battle combo strategy
                    Strategy.seabattle_combo(rolled, player1, score_bonus);
                    Strategy.seabattle_combo(rolled2, player2, score_bonus2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card1.equals("SEABATTLE-300")  || card1.equals("SEABATTLE-500") || card1.equals("SEABATTLE-1000")) {
                    String[] arrOfStr = card1.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);

                    Strategy.seabattle_combo(rolled, player1, score_bonus);
                    Strategy.combo(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card2.equals("SEABATTLE-300")  || card2.equals("SEABATTLE-500") || card2.equals("SEABATTLE-1000")) {
                    String[] arrOfStr = card2.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);


                    Strategy.random_roll(rolled, player1);
                    Strategy.seabattle_combo(rolled2, player2, score_bonus);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }
                else if ((card1.equals("MONKEYBUSINESS") && card2.equals("MONKEYBUSINESS"))) {

//                    if the random roller draws a sea battle card he/she will switch to using a sea_battle combo strategy
                    Strategy.monkey_business(rolled, player1);
                    Strategy.monkey_business(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card1.equals("MONKEYBUSINESS")) {

                    Strategy.monkey_business(rolled, player1);
                    Strategy.combo(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card2.equals("MONKEYBUSINESS")) {

                    Strategy.random_roll(rolled, player1);
                    Strategy.monkey_business(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }

                else {
                    Strategy.random_roll(rolled, player1);
                    Strategy.combo(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;

                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        logger.trace("Game has been drawn");
                        countdraw += 1;
                    }
                }



            }
            float percent1 = ((float) count1 / (float) (count1 + count2 + countdraw)) * 100;
            float percent2 = ((float) count2 / (float) (count1 + count2 + countdraw)) * 100;
            float percent_draw = ((float) countdraw / (float) (count1 + count2 + countdraw)) * 100;


            System.out.println("Player 1 win percentage: " + percent1);
            System.out.println("Player 2 win percentage: " + percent2);
            System.out.println("Percentage of games drawn:" + percent_draw);
        }

        else if(player_strat1.equals("combo") && player_strat2.equals("combo")) {

            for (int i = 0; i < num_games; i++) {
                player1.score = 0;
                player2.score = 0;


                String card1 = player1.draw_card();


                String card2 = player2.draw_card();

//                split the card1 into Seabattle and the score gained from rolling said skulls

//                initial rolls
                Faces[] rolled = player1.my_dies.roll_eight();
                Faces[] rolled2 = player2.my_dies.roll_eight();

                logger.trace(player1.name + " initial roll " + Arrays.toString(rolled));
                logger.trace(player2.name + " initial roll " + Arrays.toString(rolled2));

//                going to put if the card drawn at the start of each game is Seabattle the combo player will optimize for sabers
//                we will call the Strategy of player1 strategy.seabattle(rolled,player1);

                if ((card1.equals("SEABATTLE-300")  || card1.equals("SEABATTLE-500") || card1.equals("SEABATTLE-1000")) && (card2.equals("SEABATTLE-300")  || card2.equals("SEABATTLE-500") || card2.equals("SEABATTLE-1000"))) {
                    String[] arrOfStr = card1.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);

                    String[] arrofStr2 = card2.split("-");
                    int score_bonus2 = Integer.parseInt(arrofStr2[1]);

//                    if the random roller draws a sea battle card he/she will switch to using a sea_battle combo strategy
                    Strategy.seabattle_combo(rolled, player1, score_bonus);
                    Strategy.seabattle_combo(rolled2, player2, score_bonus2);

                    if (player1.score > player2.score) {
                        logger.trace("Player 1 has won the game");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace("Player 2 has won the game");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card1.equals("SEABATTLE-300")  || card1.equals("SEABATTLE-500") || card1.equals("SEABATTLE-1000")) {
                    String[] arrOfStr = card1.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);

                    Strategy.seabattle_combo(rolled, player1, score_bonus);
                    Strategy.combo(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace("Player 1 has won the game");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace("Player 2 has won the game");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card2.equals("SEABATTLE-300")  || card2.equals("SEABATTLE-500") || card2.equals("SEABATTLE-1000")) {
                    String[] arrOfStr = card2.split("-");
                    int score_bonus = Integer.parseInt(arrOfStr[1]);


                    Strategy.combo(rolled, player1);
                    Strategy.seabattle_combo(rolled2, player2, score_bonus);

                    if (player1.score > player2.score) {
                        logger.trace("Player 1 has won the game");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace("Player 2 has won the game");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }

                else if ((card1.equals("MONKEYBUSINESS") && card2.equals("MONKEYBUSINESS"))) {

//                    if the random roller draws a sea battle card he/she will switch to using a sea_battle combo strategy
                    Strategy.monkey_business(rolled, player1);
                    Strategy.monkey_business(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card1.equals("MONKEYBUSINESS")) {

                    Strategy.monkey_business(rolled, player1);
                    Strategy.combo(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                } else if (card2.equals("MONKEYBUSINESS")) {

                    Strategy.combo(rolled, player1);
                    Strategy.monkey_business(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;
                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;

                    } else {
                        countdraw += 1;
                    }
                }


                else {
                    Strategy.combo(rolled, player1);
                    Strategy.combo(rolled2, player2);

                    if (player1.score > player2.score) {
                        logger.trace(player1.name + " has won the game!");
                        count1 += 1;

                    } else if (player2.score > player1.score) {
                        logger.trace(player2.name + " has won the game!");
                        count2 += 1;


                    } else {
                        logger.trace("Game has been drawn");
                        countdraw += 1;
                    }
                }



            }
            float percent1 = ((float) count1 / (float) (count1 + count2 + countdraw)) * 100;
            float percent2 = ((float) count2 / (float) (count1 + count2 + countdraw)) * 100;
            float percent_draw = ((float) countdraw / (float) (count1 + count2 + countdraw)) * 100;


            System.out.println("Player 1 win percentage: " + percent1);
            System.out.println("Player 2 win percentage: " + percent2);
            System.out.println("Percentage of games drawn:" + percent_draw);
        }

        else{
            System.out.println("Please input either random or combo for the player's chosen strategy");
        }





    }


}
