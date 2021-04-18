package jaipur.command;

import jaipur.annotation.Command;
import jaipur.constant.Const;
import jaipur.constant.HandOrder;
import jaipur.control.BaseState;
import jaipur.control.GameState;
import jaipur.control.GuessCards;
import jaipur.view.StoredViews;

/**
 *
 * 用途：从公共牌堆中拿取所有骆驼牌，并从未翻开牌堆中翻牌补充到公共牌堆
 *
 * 语法：camel [公共牌堆中骆驼牌] [翻出的牌]
 * 举例：camel 3l 1s1x1p
 *
 * 说明：该命令可以用于自身，也可以用于对手
 */
@Command("camel")
public class CommandCamel extends BaseCommand{
    /**
     * 执行命令(需判断当前游戏方)
     */
    public static void exec(String command) {
        String[] splitCommand = getSplitCommand(command);

        if (splitCommand.length == 3) {

            //获取全局游戏变量
            GameState gameState = GameState.getInstance();
            GuessCards guessCards = BaseState.getInstance().getGuessCards();

            //检查命令
            if (!checkStartFlag()) {
                StoredViews.getInstance().showCommandMessage(Const.GAME_HAS_NOT_STARTED);
                return;
            }
            if (!checkItems(splitCommand[1]) || !checkItems(splitCommand[2])) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }

            //从公共牌堆中拿取所有骆驼牌
            gameState.getCardsPile().removePublicCards(splitCommand[1]);

            //将骆驼牌添加到玩家手牌堆中(需判断当前游戏方)
            if (gameState.getHandOrder() == HandOrder.MYSELF) {
                gameState.getMyself().addHandCards(splitCommand[1]);
            } else {
                if(guessCards.getGuessFlag()) {
                    gameState.getOpponent().addHandCards(splitCommand[1]);
                }else {
                    //猜测对手手牌 & 复制猜测结果
                    BaseState.getInstance().getGuessCards().guessByCamel(splitCommand[1]);
                    BaseState.getInstance().getGuessCards().copyGuessCards();
                }
            }

            //从未翻开牌堆中翻牌
            gameState.getCardsPile().removeUnknownCards(splitCommand[2]);

            //将牌添加到公共牌堆中
            gameState.getCardsPile().addPublicCards(splitCommand[2]);

            execSuccess(true, true);
        } else {
            StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARAM_MISSING);
        }
    }
}
