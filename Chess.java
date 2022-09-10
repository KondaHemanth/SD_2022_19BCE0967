import java.util.*;

// TODO: Checking characters/pawns
// TODO: Killings

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

        //Move Sets

        String[] moveP = new String[]{"F", "B", "L", "R"};
        String[] moveH2 = new String[]{"FL", "FR", "BL", "BR"};

        HashSet<String> P = new HashSet<>(Arrays.asList(moveP));
        HashSet<String> H2 = new HashSet<>(Arrays.asList(moveH2));


        String character = inp.substring(0, inp.indexOf(':')).trim();

        String move = inp.substring(inp.indexOf(':')+1);
        int movCode = 0;

        if((character.charAt(0) == 'P' || character.compareTo("H1") == 0) && H2.contains(move)){
            System.out.println("Invalid Move Set");
            return;
        }

        switch(move){
            case "F":
                movCode = 2;
                break;
            case "B":
                movCode = 6;
                break;
            case "R":
                movCode = 4;
                break;
            case "L":
                movCode = 8;
                break;
            case "FL":
                movCode = 1;
                break;
            case "FR":
                movCode = 3;
                break;
            case "BR":
                movCode = 5;
                break;
            case "BL":
                movCode = 7;
                break;
            default:
                movCode = -1;
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

        int finRow=0, finCol = 0;

        int movDist = 0;

        if(character.compareTo("H1") == 0 || character.compareTo("H2") == 0){
            movDist = 2;
        }
        if(character.charAt(0) == 'P'){
            movDist = 1;
        }
        
        if(player == 'A'){

            pos = A.get(character);

            switch(code){
                case 1:
                    finRow = ((pos/5)-movDist);
                    finCol = ((pos%5)-movDist);
                    break;

                case 2:
                    finRow = (pos/5)-movDist;
                    finCol = pos%5;
                    break;

                case 3 :
                    finRow = ((pos/5)-2);
                    finCol = ((pos%5)+2);
                    break;

                case 4:
                    finRow = (pos/5);
                    finCol = (pos%5) + movDist;
                    break;

                
                case 5:
                    finRow = ((pos/5)+2);
                    finCol = ((pos%5)+2);
                    break;
                    
                case 6:
                    finRow = (pos/5)+movDist;
                    finCol = pos%5;
                break;
                
                case 7:
                finRow = ((pos/5)+2);
                finCol = ((pos%5)-2);
                break;

                case 8:
                    finRow = (pos/5);
                    finCol = (pos%5)-movDist;
                    break;

            }

            if(finRow > 4 || finRow < 0 || finCol > 4 || finCol < 0){

                System.out.println("Move out of bounds");

                return;
            }

            String currChar = board[pos/5][pos%5];
            String finChar = board[finRow][finCol];
            

            if(currChar.charAt(0) != finChar.charAt(0)){
                if(finChar.charAt(0) == 'B'){
                    B.remove(finChar.substring(finChar.indexOf("-")+1, finChar.indexOf(":")));
                }
                
                board[pos/5][pos%5] = "-";

                A.put(character, finRow*5 + finCol);
                board[finRow][finCol] = "A-" + character ;
            }
            else {
                System.out.println("Incorrect move: Position occupied");
                return;
            }
            
            
        }
        else {
            finRow=0;
            finCol = 0;

        movDist = 0;

        if(character.compareTo("H1") == 0 || character.compareTo("H2") == 0){
            movDist = 2;
        }
        if(character.charAt(0) == 'P'){
            movDist = 1;
        }
        
        if(player == 'B'){

            pos = B.get(character);

            switch(code){
                case 1:
                    finRow = ((pos/5)+movDist);
                    finCol = ((pos%5)+movDist);
                    break;

                case 2:
                    finRow = (pos/5)+movDist;
                    finCol = pos%5;
                    break;

                case 3 :
                    finRow = ((pos/5)+2);
                    finCol = ((pos%5)-2);
                    break;

                case 4:
                    finRow = (pos/5);
                    finCol = (pos%5) - movDist;
                    break;

                
                case 5:
                    finRow = ((pos/5)-2);
                    finCol = ((pos%5)-2);
                    break;
                    
                case 6:
                    finRow = (pos/5)-movDist;
                    finCol = pos%5;
                break;
                
                case 7:
                finRow = ((pos/5)-2);
                finCol = ((pos%5)+2);
                break;

                case 8:
                    finRow = (pos/5);
                    finCol = (pos%5)+movDist;
                    break;

            }

            if(finRow > 4 || finRow < 0 || finCol > 4 || finCol < 0){

                System.out.println("Move out of bounds");

                return;
            }


            String currChar = board[pos/5][pos%5];
            String finChar = board[finRow][finCol];
            

            if(currChar.charAt(0) != finChar.charAt(0)){

                if(finChar.charAt(0) == 'A'){
                    A.remove(finChar.substring(finChar.indexOf("-")+1, finChar.indexOf(":")));
                }

                board[pos/5][pos%5] = "-";
                
                B.put(character, finPos);
                board[finPos/5][finPos%5] = "B-" + character ;
            }
            else {
                System.out.println("Incorrect move: Position occupied");
                return;
            }
        }
        }
    }
    
    public static void getInput(char player, String cmd) {
        Scanner scn = new Scanner(System.in);
        String inp;
        
        System.out.print("Player "+ (player - 'A'+1) +"'s Input: ");
        inp = scn.nextLine();
        
        String[] chars = inp.split(",");

        if(player == 'A'){
            for(int i=0; i<chars.length; i++){
                A.put(chars[i].trim(), i+20);
            }
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