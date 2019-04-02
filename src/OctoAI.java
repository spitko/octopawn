import java.util.Collections;
import java.util.List;

public class OctoAI {

    /*public static Move findMove(int[][] board, int player) {
        return new Move(0,0,0,0);
    }*/
    public static Move BogdanMove(int[][] board, int player){
        List<Move> moves = Move.findMoves(board, player);
        Move choice = new Move(0,0,0,0);
        if (player == 2){
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).y2 == 0) {
                    choice = moves.get(i);
                    return choice;
                }
            }
            for (int i = 0; i < moves.size(); i++) {
                if ((moves.get(i).y1 == 3) && (moves.get(i).x1 != moves.get(i).x2)){
                    choice = moves.get(i);
                    return choice;
                }
            }
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).x1 != moves.get(i).x2) {
                    choice = moves.get(i);
                    return choice;
                }
            }
        }else if (player == 1){
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).y2 == 3) {
                    choice = moves.get(i);
                    return choice;
                }
            }
            for (int i = 0; i < moves.size(); i++) {
                if ((moves.get(i).y1 == 0) && (moves.get(i).x1 != moves.get(i).x2)) {
                    choice = moves.get(i);
                    return choice;
                }
            }
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).x1 != moves.get(i).x2) {
                    choice = moves.get(i);
                    return choice;
                }
            }
        }
        if (choice.x1 == 0 && choice.x2 == 0 && choice.y1 == 0 && choice.y2 == 0){
            Collections.shuffle(moves);
            choice = moves.get(0);
        }
        return choice;
    }

}
