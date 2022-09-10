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

        char move = inp.charAt(inp.indexOf(':')+1);
        int movCode;

        switch(move){
            case 'F':
                movCode = 1;
                break;
            case 'B':
                movCode = -1;
                break;
            case 'R':
                movCode = 2;
                break;
            default:
                movCode = -2;
        }

        //int finPos = Integer.valueOf(inp.substring(inp.indexOf(':')+1).trim());
        int pos;
        //updateBoard(player, character, pos);

        if(player == 'A'){
            
            updateBoard('A', character, movCode);
        }
        else {
            
            updateBoard('B', character, movCode);
        }
    }
    

    
    public static void updateBoard() {
        int pos;
        
        for (Map.Entry<String,Integer> ele : A.entrySet()) {
            pos = ele.getValue();
            
            board[(pos/5)][(pos%5)] = "A-" + ele.getKey();
        }
        
        for (Map.Entry<String,Integer> ele : B.entrySet()) {
            pos = ele.getValue();
            
            board[pos/5][pos%5] = "B-" + ele.getKey();
        }
    }
    
    public static void updateBoard(char player, String character, int code) {
        int pos, finPos=0;
        
        if(player == 'A'){

            pos = A.get(character);

            if(code == 1 || code == -1){
                if(code == 1){
                    finPos = pos - 5;
                }
                else {
                    finPos = pos + 5;
                }
                
            }
            else {
                if(code == -2){
                    finPos = pos - 1;
                }
                else {
                    finPos = pos + 1;
                }
            }

            if(finPos/5 > 4 || finPos/5 < 0 || finPos%5 > 4 || finPos % 5 < 0){

                System.out.println("Move out of bounds");

                return;
            }

            board[pos/5][pos%5] = "-";
            
            A.put(character, finPos);
            board[finPos/5][finPos%5] = "A-" + character ;
            
        }
        else {
            pos = B.get(character);

            if(code == 1 || code == -1){
                if(code == 1){
                    finPos = pos + 5;
                }
                else {
                    finPos = pos - 5;
                }
                
            }
            else {
                if(code == -2){
                    finPos = pos - 1;
                }
                else {
                    finPos = pos + 1;
                }
            }

            if(finPos/5 > 4 || finPos/5 < 0 || finPos%5 > 4 || finPos % 5 < 0){

                System.out.println("Move out of bounds");

                return;
            }

            board[pos/5][pos%5] = "-";
            
            B.put(character, finPos);
            board[finPos/5][finPos%5] = "B-" + character ;
        }
    }
    
    public static void getInput(char player, String cmd) {
        Scanner scn = new Scanner(System.in);
        String inp;
        
        System.out.print("Player "+ (player - 'A'+1) +"'s Input: ");
        inp = scn.nextLine();
        
        String[] chars = inp.split(",");

        if(player == 'A'){
            for(int i=0; i<chars.length; i++)
                A.put(chars[i].trim(), i+20);                
                //updateBoard('A', chars[i].trim(), i+20);
        }
        else if(player == 'B'){
            for(int i=0; i<chars.length; i++)
                B.put(chars[i].trim(), i);
                //updateBoard('B', chars[i].trim(), i);
        }

        updateBoard();
    }
}