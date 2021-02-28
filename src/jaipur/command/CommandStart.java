package jaipur.command;

import jaipur.annotation.Command;
import jaipur.constant.Const;
import jaipur.control.GameState;
import jaipur.view.StoredViews;

/**
 * 命令：开始游戏
 * 用途：开始一局新游戏，并初始化必要的状态
 *
 * 语法：start [公共牌堆] [我的手牌] [对手骆驼牌数量] [先后手]
 * 举例：start 1h1z3l 1x2b1s1p ? 0
 *
 * 说明：当不确定对手的骆驼牌数量时，可以使用 ? 指代
 * 说明：0表示自己是先手; 1表示自己是后手
 */
@Command("start")
public class CommandStart extends BaseCommand {
    /**
     * 执行命令
     */
    public static void exec(String command) {

        String[] splitCommand = getSplitCommand(command);

        if(splitCommand.length == 5) {

            //初始化全局游戏变量
            GameState gameState = GameState.getInstance();

            //初始化牌堆
            if(!checkItems(splitCommand[1]) || !splitCommand[1].contains("3l")) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }
            gameState.getCardsPile().initUnknownCards();
            gameState.getCardsPile().deelCards(splitCommand[1]);
            gameState.getCardsPile().fitPublicCards(splitCommand[1]);

            //初始化我的手牌
            if(!checkItems(splitCommand[2])) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }
            gameState.getMyself().fitHandCards(splitCommand[2]);

            //初始化对手骆驼牌(需处理骆驼牌数量不确定的情况)
            if(splitCommand[3].equals("?")) {
                gameState.getOpponent().fitHandCardsWithMinusOne(6);
            }else {
                if(!checkItems(splitCommand[3])) {
                    StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                    return;
                }
                gameState.getOpponent().fitHandCards(splitCommand[3]);
            }

            //初始化先后手标志
            if(!splitCommand[4].equals("0") && !splitCommand[4].equals("1")) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ORDER_ERROR);
            }
        }else {
            StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARAM_MISSING);
        }
    }
}
