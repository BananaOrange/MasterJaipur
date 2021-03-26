package jaipur.command;

import jaipur.annotation.Command;
import jaipur.constant.Const;
import jaipur.constant.HandOrder;
import jaipur.control.GameState;
import jaipur.view.StoredViews;

/**
 *
 * 用途：从公共牌堆中拿取多张牌，并与玩家手牌交换
 *
 * 语法：swap [公共牌堆] [玩家手牌]
 * 举例：swap 1z1h 2p
 *
 * 说明：该命令可以用于自身，也可以用于对手
 */
@Command("swap")
public class CommandSwap extends BaseCommand{
    /**
     * 执行命令(需判断当前游戏方)
     */
    public static void exec(String command) {
        String[] splitCommand = getSplitCommand(command);

        if(splitCommand.length == 3) {

            //获取全局游戏变量
            GameState gameState = GameState.getInstance();

            //检查命令
            if (!checkStartFlag()) {
                StoredViews.getInstance().showCommandMessage(Const.GAME_HAS_NOT_STARTED);
                return;
            }
            if(!checkItems(splitCommand[1]) || !checkItems(splitCommand[2])) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }

            //从公共牌堆中拿取多张牌
            gameState.getCardsPile().removePublicCards(splitCommand[1]);

            //将公共牌堆的牌添加到玩家手牌堆中(需判断当前游戏方)
            if(gameState.getHandOrder() == HandOrder.MYSELF) {
                gameState.getMyself().addHandCards(splitCommand[1]);
            }else {
                gameState.getOpponent().addHandCards(splitCommand[1]);
            }

            //从玩家手牌堆中拿取待交换的牌(需判断当前游戏方)
            if(gameState.getHandOrder() == HandOrder.MYSELF) {
                gameState.getMyself().removeHandCards(splitCommand[2]);
            }else {
                gameState.getOpponent().removeHandCards(splitCommand[2]);
            }

            //将玩家待交换的牌添加到公共牌堆
            gameState.getCardsPile().addPublicCards(splitCommand[2]);

            execSuccess(true, true);
        }else {
            StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARAM_MISSING);
        }
    }
}
