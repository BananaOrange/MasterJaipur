package jaipur.control;

import jaipur.command.BaseCommand;
import jaipur.constant.Const;
import jaipur.constant.HandOrder;
import jaipur.model.Player;

import java.util.ArrayList;
import java.util.Random;

/**
 * 着法处理类
 */
public class MoveHandle {
    /**
     * 生成着法
     */
    public static ArrayList getMoves(GameState gameState) {

        ArrayList<String> moves = new ArrayList();// 着法列表
        String tempMove = ""; // 着法
        Enum<HandOrder> handOrder = gameState.getHandOrder();// 当前游戏方
        int[] unknownCards = gameState.getCardsPile().getUnknownCards();// 未翻开牌堆
        int[] publicCards = gameState.getCardsPile().getPublicCards();// 公共牌堆
        Player player; // 玩家
        int[] handCards;// 玩家手牌

        // 确定玩家手牌
        if (handOrder == HandOrder.MYSELF) {
            player = gameState.getMyself();
        } else {
            player = gameState.getOpponent();
        }
        handCards = player.getHandCards();

        // 组合未翻出的牌
        ArrayList<String> combineUnknownCards = new ArrayList<>();
        for(int i=0; i<unknownCards[0]; i++) {
            combineUnknownCards.add("z");
        }
        for(int i=0; i<unknownCards[1]; i++) {
            combineUnknownCards.add("h");
        }
        for(int i=0; i<unknownCards[2]; i++) {
            combineUnknownCards.add("b");
        }
        for(int i=0; i<unknownCards[3]; i++) {
            combineUnknownCards.add("s");
        }
        for(int i=0; i<unknownCards[4]; i++) {
            combineUnknownCards.add("x");
        }
        for(int i=0; i<unknownCards[5]; i++) {
            combineUnknownCards.add("p");
        }
        for(int i=0; i<unknownCards[6]; i++) {
            combineUnknownCards.add("l");
        }

        // 生成sell着法
        if (handCards[0] >= 2) {
            tempMove = "sell " + handCards[0] + "z";
            moves.add(tempMove);
        }
        if (handCards[1] >= 2) {
            tempMove = "sell " + handCards[1] + "h";
            moves.add(tempMove);
        }
        if (handCards[2] >= 2) {
            tempMove = "sell " + handCards[2] + "b";
            moves.add(tempMove);
        }
        if (handCards[3] > 0) {
            tempMove = "sell " + handCards[3] + "s";
            moves.add(tempMove);
        }
        if (handCards[4] > 0) {
            tempMove = "sell " + handCards[4] + "x";
            moves.add(tempMove);
        }
        if (handCards[5] > 0) {
            tempMove = "sell " + handCards[5] + "p";
            moves.add(tempMove);
        }

        // 生成camel着法
        if (publicCards[6] > 0) {
            tempMove = "camel " + publicCards[6] + "l " + getBestUnknownCards(combineUnknownCards, publicCards[6]);
            moves.add(tempMove);

            tempMove = "camel " + publicCards[6] + "l " + getWorstUnknownCards(combineUnknownCards, publicCards[6]);
            moves.add(tempMove);
        }

        // 生成take着法
        if (player.getHandCardsNum() < Const.HAND_CARD_LIMIT) {
            if (publicCards[0] > 0) {
                tempMove = "take " + "1z " + getBestUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);

                tempMove = "take " + "1z " + getWorstUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);
            }
            if (publicCards[1] > 0) {
                tempMove = "take " + "1h " + getBestUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);

                tempMove = "take " + "1h " + getWorstUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);
            }
            if (publicCards[2] > 0) {
                tempMove = "take " + "1b " + getBestUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);

                tempMove = "take " + "1b " + getWorstUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);
            }
            if (publicCards[3] > 0) {
                tempMove = "take " + "1x " + getBestUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);

                tempMove = "take " + "1x " + getWorstUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);
            }
            if (publicCards[4] > 0) {
                tempMove = "take " + "1s " + getBestUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);

                tempMove = "take " + "1s " + getWorstUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);
            }
            if (publicCards[5] > 0) {
                tempMove = "take " + "1p " + getBestUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);

                tempMove = "take " + "1p " + getWorstUnknownCards(combineUnknownCards, 1);
                moves.add(tempMove);
            }
        }

        // 生成swap着法
        /*
        tempMove = createSwapMove(publicCards, handCards);
        if (tempMove.length() > 5) {
            System.out.println("****:" + tempMove);
            moves.add(tempMove);
        }
         */

        return moves;
    }

    /**
     * 生成swap着法
     */
    private static String createSwapMove(int[] publicCards, int[] handCards) {
        int expensiveItemNum = publicCards[0] + publicCards[1] + publicCards[2]; // 钻石、黄金、白银数量总和
        int cheapItemNum = handCards[3] + handCards[4] + handCards[5] + handCards[6]; // 丝绸、香料、皮革、骆驼数量总和

        StringBuffer sb = new StringBuffer();
        sb.append("swap ");

        if (expensiveItemNum >= 2 && cheapItemNum >= expensiveItemNum) {

            int tempHandCardsNum = 0; // 用于计算玩家需换出多少张手牌

            if (publicCards[0] > 0) {
                sb.append(publicCards[0]).append("z");
            }
            if (publicCards[1] > 0) {
                sb.append(publicCards[1]).append("h");
            }
            if (publicCards[2] > 0) {
                sb.append(publicCards[2]).append("b");
            }

            sb.append(" ");

            tempHandCardsNum += handCards[6];
            if(tempHandCardsNum > expensiveItemNum) {
                sb.append(handCards[6] - tempHandCardsNum + expensiveItemNum).append("l");
                return sb.toString();
            }else {
                sb.append(handCards[6]).append("l");
            }

            tempHandCardsNum += handCards[5];
            if(tempHandCardsNum > expensiveItemNum) {
                sb.append(handCards[5] - tempHandCardsNum + expensiveItemNum).append("p");
                return sb.toString();
            }else {
                sb.append(handCards[5]).append("p");
            }

            tempHandCardsNum += handCards[4];
            if(tempHandCardsNum > expensiveItemNum) {
                sb.append(handCards[4] - tempHandCardsNum + expensiveItemNum).append("x");
                return sb.toString();
            }else {
                sb.append(handCards[4]).append("x");
            }

            tempHandCardsNum += handCards[3];
            if(tempHandCardsNum > expensiveItemNum) {
                sb.append(handCards[3] - tempHandCardsNum + expensiveItemNum).append("s");
                return sb.toString();
            }else {
                sb.append(handCards[3]).append("s");
            }
        }

        return sb.toString();
    }

    /**
     * 模拟随机发牌
     */
    private static String getRandomUnknownCards(ArrayList<String> combineUnknownCards, int cardNum) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<cardNum; i++) {
            sb.append("1").append(combineUnknownCards.get(random.nextInt(combineUnknownCards.size())));
        }

        return sb.toString();
    }

    /**
     * 发出最好的牌
     */
    private static String getBestUnknownCards(ArrayList<String> combineUnknownCards, int cardNum) {
        StringBuffer sb = new StringBuffer();

        if (combineUnknownCards.size() < cardNum) {
            cardNum = combineUnknownCards.size();
        }

        for(int i=0; i<cardNum; i++) {
            sb.append("1").append(combineUnknownCards.get(i));
        }

        return sb.toString();
    }

    /**
     * 发出最差的牌
     */
    private static String getWorstUnknownCards(ArrayList<String> combineUnknownCards, int cardNum) {
        StringBuffer sb = new StringBuffer();

        if (combineUnknownCards.size() < cardNum) {
            cardNum = combineUnknownCards.size();
        }

        for(int i=0; i<cardNum; i++) {
            int index = combineUnknownCards.size() -i -1;
            sb.append("1").append(combineUnknownCards.get(index));
        }

        return sb.toString();
    }

    public static GameState makeMove(GameState gameState, String move) {

        if (move.startsWith("take")) {
            return makeMoveByTake(gameState, move);

        } else if (move.startsWith("swap")) {
            return makeMoveBySwap(gameState, move);

        } else if (move.startsWith("sell")) {
            return makeMoveBySell(gameState, move);

        } else if (move.startsWith("camel")) {
            return makeMoveByCamel(gameState, move);
        }

        return gameState;
    }

    /**
     * 执行take命令
     */
    public static GameState makeMoveByTake(GameState gameState, String move) {

        String[] splitCommand = BaseCommand.getSplitCommand(move);

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

        return gameState;
    }

    /**
     * 执行swap命令
     */
    public static GameState makeMoveBySwap(GameState gameState, String move) {

        String[] splitCommand = BaseCommand.getSplitCommand(move);

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

        return gameState;
    }

    /**
     * 执行sell命令
     */
    public static GameState makeMoveBySell(GameState gameState, String move) {

        String[] splitCommand = BaseCommand.getSplitCommand(move);

        //卖出玩家手牌(需判断当前游戏方)
        if(gameState.getHandOrder() == HandOrder.MYSELF) {
            gameState.getMyself().removeHandCards(splitCommand[1]);
        }else {
            gameState.getOpponent().removeHandCards(splitCommand[1]);
        }

        //获取可奖励的分数，并添加给玩家(需判断当前游戏方)
        int goodsScore = gameState.getGoodsPile().getGoodsScore(splitCommand[1]);
        int[] bonusScore = gameState.getBonusPile().getBonusScore(splitCommand[1]);

        if(gameState.getHandOrder() == HandOrder.MYSELF) {
            gameState.getMyself().addScore(goodsScore + bonusScore[0], goodsScore + bonusScore[1]);
        }else {
            gameState.getOpponent().addScore(goodsScore + bonusScore[0], goodsScore + bonusScore[1]);
        }

        return gameState;
    }

    /**
     * 执行camel命令
     */
    public static GameState makeMoveByCamel(GameState gameState, String move) {

        String[] splitCommand = BaseCommand.getSplitCommand(move);

        //从公共牌堆中拿取所有骆驼牌
        gameState.getCardsPile().removePublicCards(splitCommand[1]);

        //将骆驼牌添加到玩家手牌堆中(需判断当前游戏方)
        if (gameState.getHandOrder() == HandOrder.MYSELF) {
            gameState.getMyself().addHandCards(splitCommand[1]);
        } else {
            gameState.getOpponent().addHandCards(splitCommand[1]);
        }

        //从未翻开牌堆中翻牌
        gameState.getCardsPile().removeUnknownCards(splitCommand[2]);

        //将牌添加到公共牌堆中
        gameState.getCardsPile().addPublicCards(splitCommand[2]);

        return gameState;
    }
}
