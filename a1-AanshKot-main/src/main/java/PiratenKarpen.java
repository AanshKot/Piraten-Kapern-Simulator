
import pk.*;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");



//        for(int i = 0; i < args.length; i++){
//
//        }

//        USER INPUTS STRATEGY THROUGH COMMAND LINE
        try {
            String strats1 = args[0];
            String strats2 = args[1];
            boolean trace_toggle = false;


            if (args.length > 2) {
                String trace_mode = args[2];
                if (trace_mode.equals("trace")) {
                    trace_toggle = true;
                }
            }


            Player player1 = new Player("Aansh");
            Player player2 = new Player("Bob");

            Game.sim_game(strats1, strats2, player1, player2, 42);
        }
        catch (Exception ArrayIndexOutofBoundsException){
            System.out.println("User must input strategies for both of the players in the command line!");
        }


    }
    
}
