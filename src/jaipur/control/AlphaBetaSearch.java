package jaipur.control;

import java.util.ArrayList;

/**
 * Alpha-Beta 搜索剪枝
 */
public class AlphaBetaSearch {
    /**
     * Alpha-Beta 搜索剪枝
     */
    public static int alphaBetaSearch(GameState gameState, int depth, int alpha, int beta) {

        int value = 0; // 局面分值

        if( depth == 0 || gameState.isEnded()) {
            value = EvaluateValue.complexEval(gameState);
            return value;
        }

        // 着法生成
        ArrayList<String> moves = MoveHandle.getMoves(gameState);
        int best = Integer.MIN_VALUE;

        GameState nextGameState;
        for(String move:moves) {
            nextGameState = MoveHandle.makeMove(gameState, move);

            value = -alphaBetaSearch(nextGameState, depth-1, -beta, -alpha);

            if(value > best) {
                best = value;
            }

            if(best > alpha) {
                alpha = best;
            }

            if(best >= beta) {
                break;
            }
        }

        return best;
    }
}
