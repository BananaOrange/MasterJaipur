package jaipur.command;

import jaipur.annotation.Command;
import jaipur.constant.Const;
import jaipur.constant.HandOrder;
import jaipur.control.*;
import jaipur.view.StoredViews;

import java.util.ArrayList;

/**
 *
 * 用途：AI计算
 *
 * 语法：ai [搜索深度]
 * 举例：ai 5
 *
 */
@Command("ai")
public class CommandAi extends BaseCommand{
    /**
     * 执行命令
     */
    public static void exec(String command) {
        String[] splitCommand = getSplitCommand(command);

        if(splitCommand.length == 2) {

            //获取全局游戏变量
            GameState gameState = GameState.getInstance();

            //检查命令
            if (!checkStartFlag()) {
                StoredViews.getInstance().showCommandMessage(Const.GAME_HAS_NOT_STARTED);
                return;
            }
            if (!splitCommand[1].matches("^[0-9]*$")) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARSING_ERROR);
                return;
            }

            long startTime = System.currentTimeMillis();

            String bestMove = ""; // 最佳着法
            int bestValue = 0;
            ArrayList<String> moves = MoveHandle.getMoves(gameState);
            for (String move : moves) {
                GameState nextGameState = MoveHandle.makeMove(gameState, move);
                int value = AlphaBetaSearch.alphaBetaSearch(nextGameState, Integer.parseInt(splitCommand[1]), Integer.MIN_VALUE, Integer.MAX_VALUE);

                if (bestMove.equals("") || value >= bestValue) {
                    bestMove = move;
                }
            }

            long endTime = System.currentTimeMillis();
            long searchTime = endTime - startTime;

            StoredViews.getInstance().showCommandMessage("AI最佳着法(耗时" + searchTime + "ms): " + bestMove);

            execSuccess(true, true);
        }else {
            StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARAM_MISSING);
        }
    }
}
