import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class MyConnectFour {

  private static View view;
  private Board board;
  private Player player1;
  private Player player2;

  //Constructor (initializes the game)
  public MyConnectFour(){
    //the view of the game, which in this case is the command line
    view = new TIView();
    this.board = new Board(7,8);
    this.player1 = new HumanPlayer('r',"Red");
    this.player2 = new CpuPlayer('y', "Yellow");
  }

  //This method contains the Connect4 game logic
  public void playGame(){
    
    int maxSlots = board.getDimensions();
    int filledSlots = 0;

    //an Arraylist which stores the players of the game
    ArrayList<Player> players = new ArrayList<>();

    players.add(player1);
    players.add(player2);

    int currentPlayer = 0;
    int nextPlayer = 1;

    boolean winCondition = false;

    //print the rules and the board
    view.display(Rules.printRules());
    view.display(board.toPrint(players));

    //repeat the main game loop until the winCondition is fulfilled
    while(!winCondition){
      view.display("\nIt's "+players.get(currentPlayer).getName()+"'s turn!\n");
      boolean validPlayerMove = false;
      
      //this loop and try block prevent human players from entering digits outside of the board dimensions
      while(!validPlayerMove){
        try{
          //place Token of currentPlayer to the board
          board.placeToken(players.get(currentPlayer), players.get(nextPlayer), players.get(currentPlayer).getMove());
          //increase the total number of tokens placed
          filledSlots+=1;
          validPlayerMove = true;
        }
        catch(Exception e){
          view.display("\nPlease enter a number within the boundaries of the game\n");
          }
        }

      view.display(board.toPrint(players));

      //check if the winCondition has been fulfilled
      winCondition = Rules.checkWinCondition(players.get(currentPlayer), board, maxSlots, filledSlots, winCondition);
      //advances to next player's turn
      Rules.endTurn(players);
    }
  }

  //this static method can be used by other classes to print information
  public static void gameDisplay(String s){
    view.display(s);
  }
}

