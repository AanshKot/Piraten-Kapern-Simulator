package pk;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class DeckofCards {

    private static final Logger logger = LogManager.getLogger(DeckofCards.class);
    private final static String[] deck_cards = {
            "SEABATTLE-300","SEABATTLE-300",
            "SEABATTLE-500","SEABATTLE-500",
            "SEABATTLE-1000","SEABATTLE-1000",
            "MONKEYBUSINESS","MONKEYBUSINESS","MONKEYBUSINESS","MONKEYBUSINESS","NOP",
            "NOP","NOP","NOP","NOP","NOP",
            "NOP","NOP","NOP","NOP","NOP",
            "NOP","NOP","NOP","NOP","NOP",
            "NOP","NOP","NOP","NOP","NOP",
            "NOP","NOP","NOP","NOP"};



    public static String drawCard(){
        int min = 1;
        int max = 35;

        int randomNum = ThreadLocalRandom.current().nextInt(min, max);

        logger.trace("You drew: " + deck_cards[randomNum]);

        return deck_cards[randomNum];
    }



}