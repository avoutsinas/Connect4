//Abstract Superclass. 
//HumanPlayer and CpuPlayer classes inherit from this class
public abstract class Player{

  private char playerToken;
  private String playerName;

  //Constructor
  public Player(char playerToken, String playerName){
    this.playerToken = playerToken;
    this.playerName = playerName;
  }

  //Accessors
  public char getToken(){
    return playerToken;
  }

  public String getName(){
    return playerName.toString();
  }

  //Abstract method for player moves
  public abstract int getMove();
  
}