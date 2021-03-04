import java.util.Random;

public class CpuPlayer extends Player{

  private Random rand = new Random();

  //Constructor. It uses the Constructor of the Player superclass
  public CpuPlayer(char playerToken, String playerName){
    super(playerToken, playerName);
  }

  //This method gets the move of a computer player
  //It overrides the getMove method of superclass Player
  public int getMove(){
    int highestNum = 7;
    int rand_int = rand.nextInt(highestNum)+1;
    MyConnectFour.gameDisplay("CPU player "+getName()+" chose "+rand_int);
    return rand_int;
  }
  
}