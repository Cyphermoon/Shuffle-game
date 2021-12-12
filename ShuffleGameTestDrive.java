import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class shuffleGame{
    private int ROW;
    private int COL;
    private int[] items = {1, 0, 2, 3, 4, 5, 6, 7, 8};
    private int[] sorted_items = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private int keypress;
    private int[][] board;
    ArrayList<String> moves;

    public shuffleGame(){
        this(3);
    }


    public shuffleGame(int col){
        this.moves = new ArrayList<String>();
        this.moves.add("l"); this.moves.add("r");  
        this.moves.add("u"); this.moves.add("d");

        this.COL = col;
        this.shuffleGameItems();
        this.setBoard();
    }


    public void shuffleGameItems(){
        for(int i = 0; i < this.items.length; i++){
            int randIndex = (int) (Math.random() * this.items.length);
            int tempValue = this.items[i];

            this.items[i] = this.items[randIndex];
            this.items[randIndex] = tempValue;
        }
    }
    

    public void incrementKeyPress(){
        this.keypress++;
    }


    public int getKeyPress(){
        return this.keypress;
    }


    public void setBoard(){
        this.ROW = (int) this.items.length / this.COL;
        this.board = new int[this.ROW][this.COL];
        int itemIndex = 0;

        for(int r = 0; r < this.ROW; r++){
            for(int c = 0; c < this.COL; c++){
                this.board[r][c] = this.items[itemIndex];
                 itemIndex++;
            }
        }
    }


    public String getUserChoice(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Your Move [L, R, U, D]: ");
        String userChoice = scanner.next();

        if(this.moves.contains(userChoice.toLowerCase())) return userChoice;
        
        return "";
    }


    public int[] getElementCellPosition(){
        String userChoice = this.getUserChoice();
        int[] blankCell = this.getbLankSpace();
        int[] elementCell = new int[2];

        if(userChoice.equalsIgnoreCase("l")){
            elementCell[0] = blankCell[0];
            elementCell[1] = blankCell[1] - 1;
        }

        if(userChoice.equalsIgnoreCase("r")){
            elementCell[0] = blankCell[0];
            elementCell[1] = blankCell[1] + 1;
        }

        if(userChoice.equalsIgnoreCase("u")){
            elementCell[0] = blankCell[0] - 1 ;
            elementCell[1] = blankCell[1];
        }

        if(userChoice.equalsIgnoreCase("d")){
            elementCell[0] = blankCell[0] + 1 ;
            elementCell[1] = blankCell[1];
        }

        if(userChoice.equalsIgnoreCase("")){
            elementCell[0] = -2;
            elementCell[1] = -1;
        }

        return elementCell;
    }


    public int[] getbLankSpace(){
        int[] cell = new int[2];

        for(int r = 0; r < this.ROW; r++){
            for(int c = 0; c < this.COL; c++){
                if(this.board[r][c] == 0){
                    cell[0] = r;
                    cell[1] = c;
                }
            }
        }
        return cell;
    }


    public int[] getElementSpace(int row, int col){
        int[] cell = new int[2];

        for(int r = 0; r < this.ROW; r++){
            for(int c = 0; c < this.COL; c++){
                if(this.board[r][c] == this.board[row][col]){
                    cell[0] = r;
                    cell[1] = c;
                }
            }
        }
        return cell;
    }

    public boolean isBoardInOrder(){
        int itemIndex = 0;

        for(int r = 0; r < this.ROW; r++){
            for(int c = 0; c < this.COL; c++){
                if(this.board[r][c] != this.sorted_items[itemIndex]){
                    return false;
                }
                 itemIndex++;
            }
        }

        return true;
    }    


    public void switchCell(int row, int col){
        int[] blankCell = getbLankSpace();
        int tempValue = this.board[blankCell[0]][blankCell[1]];
        int[] newCell = getElementSpace(row, col);

        this.board[blankCell[0]][blankCell[1]] = this.board[newCell[0]][newCell[1]];
        this.board[newCell[0]][newCell[1]] = tempValue;
    }   


    public boolean isValidSpace(int row, int col){
        ArrayList<Integer>  rows = new ArrayList<Integer>();
        ArrayList<Integer>  cols = new ArrayList<Integer>();

        for(int i = 0; i < this.ROW; i++){
            rows.add(i);
        }

        for(int i = 0; i < this.COL; i++){
            cols.add(i);
        }

        if(rows.contains(row) && cols.contains(col)) return  true;

        return false;
    }


    public void displayData(){
         System.out.print("+=====+=====+\n");
        for(int i = 0; i < this.ROW; i++){
            String arr = Arrays.toString(this.board[i]).replace(",", " +");
            StringBuilder sb = new StringBuilder(arr);
            sb.deleteCharAt(arr.length() - 1);
            sb.deleteCharAt(0);

            System.out.println("+ " + sb.toString() + " + ");
        }
         System.out.print("+=====+=====+\n\n");
    }
}


public class ShuffleGameTestDrive {
    public static void main(String[] args){
        System.out.println("\n======Welcome to a Shuffle Game (created by Cypher Moon)========\n")  ;
        shuffleGame game = new shuffleGame(3);
        MakeSound eHandling = new MakeSound();
        
        game.displayData();

        while(true){
            int[] cell = game.getElementCellPosition();
            game.incrementKeyPress();
            if(game.isValidSpace(cell[0], cell[1])){
                game.switchCell(cell[0], cell[1]);
                System.out.println();
                game.displayData();
            } 
            else {
                eHandling.play(100, 60);
                continue;
            }; 

            if(game.isBoardInOrder()){
                System.out.println("\nCompleted\n");
                System.out.println("KeyPress:" + game.getKeyPress());
                break;
            }
            
        }

    }
}
