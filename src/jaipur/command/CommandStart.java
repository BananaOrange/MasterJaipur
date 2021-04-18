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
 * 用途：开始一局新游戏，并初始化必要的状态
 *
 * 语法：start [公共牌堆] [我的手牌] [对手骆驼牌数量] [先后手]
 * 举例：start 1h1z3l 1x2b1s1p ? 0
 *
 * 说明：当不确定对手的骆驼牌数量时，可以使用 ? 指代
 * 说明：0表示当前游戏方是自己; 1表示当前游戏方是对手
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
            GuessCards guessCards = BaseState.getInstance().getGuessCards();

            //检查命令
            if(!checkItems(splitCommand[1]) || !splitCommand[1].contains("3l")) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }
            if(!checkItems(splitCommand[2])) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }
            if(!checkItems(splitCommand[3]) && !splitCommand[3].equals("?")) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ITEMS_ERROR);
                return;
            }
            if(!splitCommand[4].equals("0") && !splitCommand[4].equals("1")) {
                StoredViews.getInstance().showCommandMessage(Const.COMMAND_ORDER_ERROR);
                return;
            }

            //更新游戏开始标记
            BaseState.getInstance().setStartFlag(true);

            //初始化牌堆
            gameState.getCardsPile().initUnknownCards();
            gameState.getCardsPile().removeUnknownCards(splitCommand[1]);
            gameState.getCardsPile().addPublicCards(splitCommand[1]);

            //初始化我的手牌
            gameState.getMyself().addHandCards(splitCommand[2]);

            //初始化对手骆驼牌(需处理骆驼牌数量不确定的情况)
            //交由手牌猜测类处理
            /*
            if(splitCommand[3].equals("?")) {
                gameState.getOpponent().setUnknownHandCards(6);
            }else {
                gameState.getOpponent().addHandCards(splitCommand[3]);
            }
             */

            //初始化先后手标志
            if(splitCommand[4].equals("0")) {
                gameState.setHandOrder(HandOrder.MYSELF);
            }else {
                gameState.setHandOrder(HandOrder.OPPONENT);
            }

            //猜测对手手牌 & 复制猜测结果
            guessCards.guessByStart(splitCommand[3]);
            guessCards.copyGuessCards();

            execSuccess(false, true);
        }else {
            StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARAM_MISSING);
        }
    }
}
