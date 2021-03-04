import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//A class representing human players which inherits from Player superclass
public class HumanPlayer extends Player{

  private BufferedReader in;

  //Constructor. It uses the Constructor of the Player superclass
  public HumanPlayer(char playerToken, String playerName){
    super(playerToken, playerName);
    in = new BufferedReader(new InputStreamReader(System.in));
  }

  //This method gets the move of a human player
  //It overrides the getMove method of superclass Player
  public int getMove(){
    boolean isDigit = false;
    int move = 0;

    //the following loop and try-catch block handle inputs which are not integers
    while(!isDigit){
      try{
        //read input from the user
        String input = in.readLine();
        //convert it to an integer
        move = Integer.parseInt(input);
        isDigit = true;
      }
      catch (Exception e){
        MyConnectFour.gameDisplay("\nPlease enter digits only.\n");
      }
    } 
    return move;
  }
} 