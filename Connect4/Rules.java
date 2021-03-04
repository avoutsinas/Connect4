import java.util.List;
import java.util.ArrayList;

//Contains all the rules of the game as static methods
public class Rules{

  //returns a string containing the rules of the game in text form
  public static String printRules(){
    String toPrint = "";
    toPrint += "\nWelcome to Connect 4\n";
    toPrint += "There are 2 players red and yellow\n";
    toPrint += "Player 1 is Red, Player 2 is Yellow\n";
    toPrint += "To play the game type in the number of the column you want to drop you counter in\n";
    toPrint += "A player wins by connecting 4 counters in a row - vertically, horizontally or diagonally\n";

    return toPrint;
  }

  //Advances turns by popping the first element of the list and pushing it to the end of the list
  public static ArrayList<Player> endTurn(ArrayList<Player> l){
    l.add(l.get(0));
    l.remove(0);
    return l;
  }

  //checks whether a player has won or if there is a tie
  public static boolean checkWinCondition(Player p, Board board, int ms, int fs, boolean condition){
    if(evaluateMove(p.getToken(), board)){
      condition = true;
      MyConnectFour.gameDisplay("\n"+p.getName()+" won!!!\n");
      return true;
    }
    else if(ms == fs){
      condition = true;
      MyConnectFour.gameDisplay("\nThe Game ended in a tie!\n");
      return condition;
    }
    return condition;
  }

  //checks if any of the matching conditions have been met.
  private static boolean evaluateMove(char player, Board board){
    if (checkHorizontal(player, board)){
      MyConnectFour.gameDisplay("\nHorizontal Match!");
      return true;
    } 
    else if (checkVertical(player, board)){
      MyConnectFour.gameDisplay("\nVerical Match!");
      return true;
    }
    else if (checkDescendingDiagonal(player, board)){
      MyConnectFour.gameDisplay("\nDescending Diagonal Match!");
      return true;
    }
    else if (checkAscendingDiagonal(player, board)){
      MyConnectFour.gameDisplay("\nAscending Diagonal Match!");
      return true;
      }
    return false;
  }

    //checks horizontal matches
    private static boolean checkHorizontal(char player, Board board){
      int count = 0;
      boolean hasWon = false;

      //for every unique row
      for(int rowLimit=0; rowLimit< board.getRowCount(); rowLimit++){
        //checks every row in the board
        for(int i=0; i< board.getRowCount(); i++){
          // checks every column of the row
          for(int j=0; j< board.getColumnsOfRow(i).length; j++){
            // by having the second condition, only matches found in the same row can advance the counter
            if(board.getElement(j,i) == player && i == rowLimit){ 
              count = count + 1;
              if(count >= 4){
                hasWon = true;
                return hasWon;
              }
            }
            else{
              count = 0;
            }
          }
        }
      }
      return hasWon;
    }

  //checks vertical matches
  private static boolean checkVertical(char player, Board board){
    int count = 0;
    boolean hasWon = false;

    for(int i=0; i< board.getColumnCount(); i++){
      for(int j=0; j< board.getRowCount(); j++){
        if(board.getElement(i,j) == player){
          count = count + 1;
          if(count >= 4){
            hasWon = true;
            return hasWon;
          }
        }
        else{
          count = 0;
        }
      }   
    }
    return hasWon;
  }

  //checks ascending diagonal matches
  private static boolean checkAscendingDiagonal(char player, Board board){
    boolean hasWon = false;

    for (int i = 0; i < board.getColumnCount() - 3; i++) {
      for (int j = 3; j < board.getRowCount(); j++) {
        if (board.getElement(i,j) == player && board.getElement(i+1,j-1) == player && 
        board.getElement(i+2,j-2) == player && board.getElement(i+3,j-3) == player) {
          hasWon = true;
          return hasWon;
        }
      }
    }
    return hasWon;
  }

  //checks descending diagonal matches
  private static boolean checkDescendingDiagonal(char player, Board board){
    boolean hasWon = false;

    for (int i = 0; i < board.getColumnCount() - 3; i++) { 
      for (int j = 0; j < board.getRowCount(); j++) {
        if (board.getElement(i,j) == player && board.getElement(i+1,j+1) == player && 
          board.getElement(i+2,j+2) == player && board.getElement(i+3,j+3) == player) {
          hasWon = true;
          return hasWon;
        }
      }
    }
    return hasWon;
  }
}