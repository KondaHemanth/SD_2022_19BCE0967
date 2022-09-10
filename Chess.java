import java.util.*;

// TODO: Checking characters/pawns

class Chess {

    static String[][] board;

    static HashMap<String,Integer> A = new HashMap<>();
    static HashMap<String,Integer> B = new HashMap<>();

    public static void main(String [] args){
        board = new String[5][5];

        
        
        boolean over = false;
        char winner = ' ';
        
        // Initialize Board
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                board[i][j] = "-";
            }
        }

        // Initlal Input

        getInput('A', "init");
        printBoard();
        
        getInput('B', "init");
        printBoard();



        while(!over){
            // A's turn
            getInput('A');
            printBoard();
            // B's turn
            
            getInput('B');
            printBoard();
        }

        printBoard();
    }

    public static void printBoard() {
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(board[i][j]+ "\t");
            }
            System.out.println("");
        }
    }
    
    public static void getInput(char player) {
        Scanner scn = new Scanner(System.in);
        String inp;

        System.out.print("Player "+(player-'A'+1)+"'s Move: ");
        inp = scn.nextLine();

        String character = inp.substring(0, inp.indexOf(':')).trim();
        int pos = Integer.valueOf(inp.substring(inp.indexOf(':')+1).trim());
        updateBoard(player, character, pos);
    }
    
    
    public static void updateBoard(char player, String character, int position) {
        int row = position/5, col=position%5;

        if(player == 'A'){
            A.put(character, position);
        }
        else {
            B.put(character, position);
        }
        
        // To bo changed to implement eating
        board[row][col] = player + "-" +character;
    }
    
    public static void getInput(char player, String cmd) {
        Scanner scn = new Scanner(System.in);
        String inp;
        
        System.out.print("Player "+ (player - 'A'+1) +"'s Input: ");
        inp = scn.nextLine();
        
        String[] chars = inp.split(",");

        if(player == 'A'){
            for(int i=0; i<chars.length; i++)
               updateBoard('A', chars[i].trim(), i+20);
        }
        else if(player == 'B'){
            for(int i=0; i<chars.length; i++)
                updateBoard('B', chars[i].trim(), i);
        }
    }
}