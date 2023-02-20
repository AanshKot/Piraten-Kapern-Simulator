<!-- BUSINESS LOGIC OBJECTIVE
You have to develop a program that will simulate games of Piraten Karpen (a board game with dices
and cards). Your user will select how many games to be played and the different strategies the virtual
players will use to play the game. Ultimately, it’ll print the number of wins for each player on the
standard output. -->

<!-- MVP - minimal and viable product -->

Jan 10th 2023: created logbook.md + went over the README file, went over rules for the game

Jan 17th 2023: wrote MVP report + started on step 2, finished DoD as well as updated backlog

Jan 18th 2023: delivered feature 2, implemented feature 3 as well as feature 4 and 5 created Player class

Jan 19th 2023: finished implementing all features + simulated game

Jan 20th 2023: pushed MVP to Git

Jan 22nd 2023: added logging dependency to pom.xml file, error with logic of sim_game method of MVP release, therefore updated it with correct logic and pushed to git re-released MVP

Jan 23rd 2023: Finished adding logger to sim_game method, started on 4-of-a-kind implementation and smarter player implementation

Jan 25th 2023: Finished smart player implementation, updated the update_score method, as well as added a bonus method for 3 of a kind and above. Made a sim_game method that takes in player strategy and then players play to that strategy. Still have to change System out statements to logger.trace statements

Jan 26th 2023: Created DeckofCards class in which we will store the various cards Piraten Kapern introduces

Jan 27th 2023: started and finished SeaBattle strategy, updated Game class, started monkey business feature

Jan 28th 2023: finished and tested Monkey Business feature, started working on accepting arguments from the  command line implementation