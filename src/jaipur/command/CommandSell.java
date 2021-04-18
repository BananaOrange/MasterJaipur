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
 * 用途：卖出玩家手牌
 *
 * 语法：sell [玩家手牌]
 * 举例：sell 3z
 *
 * 说明：该命令可以用于自身，也可以用于对手
 */
@Command("sell")
public class CommandSell extends BaseCommand{
    /**
     * 执行命令(需判断当前游戏方)
     */
    public static void exec(String command) {
        String[] splitCommand = getSplitCommand(command);

        if(splitCommand.length == 2) {

            //获取全局游戏变量
            GameState gameState = GameState.getInstance();
            GuessCards guessCards = BaseState.getInstance().getGuessCards();

            //检查命令
            if (!checkStartFlag()) {
                StoredViews.getInstance().showCommandMessage(Const.GAME_HAS_NOT_STARTED);
                return;
            }
            if(!checkItems(splitCommand[1])) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }

            //卖出玩家手牌(需判断当前游戏方)
            if(gameState.getHandOrder() == HandOrder.MYSELF) {
                gameState.getMyself().removeHandCards(splitCommand[1]);
            }else {
                if(guessCards.getGuessFlag()) {
                    gameState.getOpponent().removeHandCards(splitCommand[1]);
                }else {
                    //猜测对手手牌 & 复制猜测结果
                    BaseState.getInstance().getGuessCards().guessBySell(splitCommand[1]);
                    BaseState.getInstance().getGuessCards().copyGuessCards();
                }
            }

            //获取可奖励的分数，并添加给玩家(需判断当前游戏方)
            int goodsScore = gameState.getGoodsPile().getGoodsScore(splitCommand[1]);
            int[] bonusScore = gameState.getBonusPile().getBonusScore(splitCommand[1]);

            if(gameState.getHandOrder() == HandOrder.MYSELF) {
                gameState.getMyself().addScore(goodsScore + bonusScore[0], goodsScore + bonusScore[1]);
            }else {
                gameState.getOpponent().addScore(goodsScore + bonusScore[0], goodsScore + bonusScore[1]);
            }

            execSuccess(true, true);
        }else {
            StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARAM_MISSING);
        }
    }
}
