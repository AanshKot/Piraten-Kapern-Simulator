# Piraten-Kapern-Simulator
Simulates games of Piraten Kapern with the player's strategy inputted by the user.


# A1 - Piraten Karpen

  * Author: < Aansh Kotian >
  * Email: < kotiana@mcmaster.ca >

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 
  * To run the program with user inputted strategies:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar random combo`
  * To run the program in trace mode:
    * got to log4j2.xml file and set mode to trace





Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * does the feature meets the game criteria? 
   * is the feature’s code the simplest way it can be implemented? 
   * is the feature implemented, documented and well-commented? 
   * has the feature been thoroughly tested for bugs, and passed said test?


### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| Y  | F01 | Roll a dice |  D | 01/01/23 | 16/01/23 |
| N  | F02 | Roll eight dices | D | 17/01/23 | 18/01/23|
| N   | F03 | Select how many games as command-line arg.|D|  18/01/23 |20/01/23|
| Y   | F04 | end of game with three cranes| D | 19/01/23 |20/01/23
| Y   | F05 | Player keeping random dice at their turn |19/01/23| 20/01/23 | 
| Y   | F06 | Score points: 3-of-a-kind | D | 19/01/23 | 20/01/23 |
| N | F07 | Smart Player  | D| 25/01/2023| 26/01/2023 |
| N | F08 | Card Deck | D | 26/01/2023| 27/01/2023|
| N  |F09  | Sea Battle Strategy       | D    | 27/01/23      | 27/01/23       |
| N| F10  |Monkey Business feature     | D | 27/01/23     | 28/01/23      |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
