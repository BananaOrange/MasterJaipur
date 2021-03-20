package jaipur.command;

import jaipur.annotation.Command;
import jaipur.constant.Const;
import jaipur.constant.HandOrder;
import jaipur.control.GameState;
import jaipur.view.StoredViews;

/**
 *
 * 用途：从公共牌堆中拿取一张牌，并从未翻开牌堆中拿取一张牌补充到公共牌堆
 *
 * 语法：take [公共牌堆] [翻出的牌]
 * 举例：take 1h 1x
 *
 * 说明：该命令可以用于自身，也可以用于对手
 */
@Command("take")
public class CommandTake extends BaseCommand{
    /**
     * 执行命令(需判断当前游戏方)
     */
    public static void exec(String command) {
        String[] splitCommand = getSplitCommand(command);

        if(splitCommand.length == 3) {

            //获取全局游戏变量
            GameState gameState = GameState.getInstance();

            //检查命令
            if(!checkItems(splitCommand[1]) || !checkItems(splitCommand[2])) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }

            //从公共牌堆中拿取一张牌
            gameState.getCardsPile().removePublicCards(splitCommand[1]);

            //将该张牌添加到手牌堆中(需判断当前游戏方)
            if(gameState.getHandOrder() == HandOrder.MYSELF) {
                gameState.getMyself().addHandCards(splitCommand[1]);
            }else {
                gameState.getOpponent().addHandCards(splitCommand[1]);
            }

            //从未翻开牌堆中拿取一张牌
            gameState.getCardsPile().removeUnknownCards(splitCommand[2]);

            //将该张牌添加到公共牌堆中
            gameState.getCardsPile().addPublicCards(splitCommand[2]);

            execSuccess(true, true);
        }else {
            StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARAM_MISSING);
        }
    }
}
