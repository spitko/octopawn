import java.util.ArrayList;
import java.util.List;

public class Turn {

        //0 pole kellegi võit
        //1 on AI võit
        //2 on Mängija võit
        public int checkVictory(int[][] board){
            for (int i = 0; i < board.length; i++) {
                if (board[0][i] == 2){
                    return 2;
                }
            }
            for (int i = 0; i < board.length; i++) {
                if (board[board.length-1][i] == 1){
                    return 1;
                }
            }
            return 0;
        }


        public static int[][] findTurns(int[][] board){
            int fw = 0;
            List<int[]> turns = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 1){
                        fw = 1;
                    }else if(board[i][j] == 2){
                        fw = 2;
                    }else {
                        continue;
                    }

                    int pl = board[i][j];

                    if (pl != 1){
                        continue;
                    }

                    int other = 3 - pl;

                    if ((i + fw) < board.length && (i + fw) >= 0){
                        if (board[i + fw][j] == 0){
                            turns.add({{j, i}, {j, i + fw}});
                        }
                        if ((j - 1) >= 0 && board[i + fw][j - 1] == other){
                            turns.add({{j, i}, {j - 1, i + fw}});
                        }
                        if ((j + 1) < board.length && board[i + fw][j + 1] == other){
                            turns.add({{j, i}, {j + 1, i + fw}});
                        }
                    }
                }

            }
            int[][] possibleturns = new int[turns.size() - 1][2];
            for (int i = 0; i < turns.size(); i++) {
                possibleturns[i] = turns.get(i);
            }
            return possibleturns;
        }
}
