package jaipur.control;

/**
 * Alpha-Beta 搜索剪枝
 */
public class AlphaBetaSearch {
    /**
     * Alpha-Beta 搜索剪枝
     */
    public static int alphaBetaSearch(GameState gameState, int depth, int alpha, int beta) {

        int value;

        if( depth == 0 || gameState.isEnded()) {
            value = EvaluateValue.complexEval(gameState);
            return value;
        }

        //着法生成
        gameState.getOrderedMoves();
        int best = Integer.MIN_VALUE;
        int move;

        GameState nextGameState;
        while (board.hasMoreMoves()) {
            move = board.getNextMove();
            nextGameState = MoveHandle.makeMove(move);

            value = -alphaBeta(nextBoard, depth-1, -beta, -alpha);

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
