import java.util.*;

public class Pawn extends gameChars{
    int movDist;
    String name;
    HashSet<Character> dir = new HashSet<>();

    int row, col;

    Pawn(String name){
        this.name = name;

        movDist = 1;
        dir.add('F');
        dir.add('B');
        dir.add('L');
        dir.add('R');
    }
}
