package jaipur.control;

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
            tempMove = "camel " + publicCards[6] + "l " + "";
            // TODO: 2021/5/15
        }

        // 生成take着法
        if (publicCards[0] > 0) {
            tempMove = "take " + publicCards[0] + "z ";
            moves.add(tempMove);
        }



        // 生成swap着法

        return moves;
    }

    /**
     * 模拟随机发牌
     */
    private static String getRandomUnknownCards(ArrayList<String> combineUnknownCards, int cardNum) {
        Random random = new Random(combineUnknownCards.size());
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<cardNum; i++) {
            sb.append("1").append(combineUnknownCards.get(random.nextInt()));
        }

        return sb.toString();
    }

    /**
     * 发出最好的牌(只生成2种情况)
     */
    private static String[] getBestUnknownCards(ArrayList<String> combineUnknownCards, int[] unknownCards, int cardNum) {
        String[] bestCards = new String[2];

        // 第1种情况：按顺序获取
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<cardNum; i++) {
            sb.append("1").append(combineUnknownCards.get(i));
        }
        bestCards[0] = sb.toString();

        // 第2种情况：随机获取
        sb.setLength(0);
        Random random = new Random(cardNum * 3);
        for(int i=0; i<cardNum; i++) {
            sb.append("1").append(combineUnknownCards.get(random.nextInt()));
        }
        bestCards[1] = sb.toString();

        return bestCards;
    }

    /**
     * 发出最差的牌(只生成2种情况)
     */
    private static String[] getWorstUnknownCards(ArrayList<String> combineUnknownCards, int[] unknownCards, int cardNum) {
        String[] worstCards = new String[2];

        // TODO: 2021-05-17  

        // 第1种情况：按顺序获取
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<cardNum; i++) {
            sb.append("1").append(combineUnknownCards.get(i));
        }
        worstCards[0] = sb.toString();

        // 第2种情况：随机获取
        sb.setLength(0);
        Random random = new Random(cardNum * 3);
        for(int i=0; i<cardNum; i++) {
            sb.append("1").append(combineUnknownCards.get(random.nextInt()));
        }
        worstCards[1] = sb.toString();

        return worstCards;
    }

    public static GameState makeMoveByTake(GameState gameState, String move) {
        
        return gameState;
    }
}
