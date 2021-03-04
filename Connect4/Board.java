import java.util.List;
import java.util.ArrayList;

//A class which represents the game board
public class Board{

  private char[][] board;
  private char[][] boardRev;

  // Constructor
  public Board(int columns, int rows){
    this.board = new char[columns][rows];
    //a board with inverted columns and rows
    this.boardRev = new char[rows][columns];
  }

  // Accessors
  public int getColumnCount(){
    return board.length;
  }

  public int getRowCount(){
    return board[0].length;
  }

  public char[] getColumnsOfRow(int index){ 
    return boardRev[index];
  }

  public char getElement(int x, int y){
    return board[x][y];
  }

  public int getDimensions(){
    return ((getColumnCount()-1)*(getRowCount()-1));
  }

  /* Methods */
  
  // places a token to the board
  public void placeToken(Player currentPlayer, Player opponent, int position){
    boolean placed = false;
    //scan the rows of the board from bottom to top
    for (int i = board.length - 2; i >= 0; i--) {
      if (!placed) {
        //if there is an opponent token at current position
        if (board[position - 1][i] == opponent.getToken()) {
          // skip
        }
        //otherwise, place the current player's token on the board
        else if (board[position - 1][i] != currentPlayer.getToken()) {
          board[position - 1][i] = currentPlayer.getToken();
          placed = true;
        }
      }
    }
    //if nothing was placed on the board
    if (!placed){
      MyConnectFour.gameDisplay("\nColumn "+Integer.toString(position)+" is full!\n"+currentPlayer.getName()+", choose another Column.");
      //call the same method recursively, but with a new player move
      placeToken(currentPlayer, opponent, currentPlayer.getMove());
    }
  }

  //returns a string containing the game board with its boundaries and player tokens
  public String toPrint(ArrayList<Player> l){
    String toReturn = "\n--------***GAME-BOARD***-------\n\n";

		for(int i=0; i<board.length-1; i++){
			for(int j=0; j<board[i].length-1; j++){
        //if the player token is found on that point of the board
        if(addPlayerToken(j, i, l) != ""){
          //add it to the string
          toReturn += addPlayerToken(j, i, l);
        }
        else{
          toReturn += "|   ";
        }
      }
      toReturn += "| "+ (board.length-1-i)+"\n";
		}
		toReturn += "  1   2   3   4   5   6   7";
    return toReturn;
	}

  //helper method for toPrint method. 
  //Checks if any of the player tokens matches with the element in a point of the board
  private String addPlayerToken(int col, int row, ArrayList<Player> l){
    String token = "";
    //for every player in the players list
    for(Player p : l){
      if(board[col][row] == p.getToken()){
        token = "| "+p.getToken()+" ";
      }
    }
    return token;
  }
}