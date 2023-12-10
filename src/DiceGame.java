//Autofill was very useful on this one

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DiceGame {
    public static void main(String[] args) {
        //initialized the scanner
        Scanner sc = new Scanner(System.in);

        //setup to catch exceptions if the user did not input an actual number for the sides of a die, one for each prompt
        System.out.print("Enter the number of sides of the die being used: ");
        int numSides;
        try {
            numSides = sc.nextInt();

            //out of desired bounds check
            if (numSides < 1) {
                numSides = 6;
                System.out.println("Default = " + numSides);
            }
        } catch (InputMismatchException ignored) {
            //falls back onto the intended default if the input is not an integer
            numSides = 6;
            System.out.println("Default = " + numSides);
            sc.nextLine();
        }

        System.out.print("Enter the number of players: ");
        int numPlayers;
        try {

            //bounds check
            numPlayers = sc.nextInt();
            if (numPlayers < 1) {
                numPlayers = 2;
                System.out.println("Default = " + numPlayers);
            }
        } catch (InputMismatchException ignored) {
            //falls back onto a default if teh input is a non integer
            numPlayers = 2;
            System.out.println("Default = " + numPlayers);
            sc.nextLine();
        }

        //makes a List of players to store data
        List<Player> players = new ArrayList<>();

        //loops through the player array and fills it with the specific details such as name and die(universal) across all players
        int i = 1;
        //'for' loop broke this for some reason, so I had to use 'while'
        while (i <= numPlayers) {
            System.out.print("Enter the name for player " + i + ": ");
            String playerName = sc.next();

            Die playerDie = new Die(numSides);
            Player player = new Player(playerName, playerDie);
            players.add(player);
            i++;
        }

        //catches IO exception, because I know how to do that
        try {

            //creates the file to output game messages rather than the terminal
            FileWriter file = new FileWriter("DiceGameOutput.txt");

            //loops through the players and fills in names and value that are printed to the file
            for (Player player : players) {
                player.getDie().roll();
                file.write
                        ("Player " + player.getName() +
                                " rolled a " + player.getDie().getValue() +
                                " on a " + numSides + " sided die\n");
            }


            if (decideWinner(players) == -420) {
                //in the event of a tie
                file.write("The game is a tie");
            } else {
                //outputs the winner by name
                String winnerName = players.get(decideWinner(players)).getName();
                file.write(winnerName + " won the game");
            }

            file.close();

        } catch (IOException e) {
            //outputs a nice message that is probably not that useful
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    //sorts through players and finds ties or highest value among them
    private static int decideWinner(List<Player> players) {
        //other variables to store loop sorting
        int valueMax = 0;
        int count = 0;
        int winner = -420;

        for (int j = 0; j < players.size(); j++) {
            int value = players.get(j).getDie().getValue();

            if (value > valueMax) {
                valueMax = value;
                count = 1;
                winner = j;
            } else if (value == valueMax) {
                count++;
            }
        }
        return ((count > 1) ? -420 : winner);
    }
}