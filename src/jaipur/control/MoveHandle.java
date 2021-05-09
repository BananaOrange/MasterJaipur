package jaipur.control;

import jaipur.constant.HandOrder;

import java.util.ArrayList;

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
        int[] handCards; // 玩家手牌

        // 生成可能翻出的牌组合
        if (handOrder == HandOrder.MYSELF) {
            handCards = gameState.getMyself().getHandCards();
        } else {
            handCards = gameState.getOpponent().getHandCards();
        }
        // TODO: 2021-05-09 补充代码

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

        }

        // 生成take着法



        // 生成swap着法

        return moves;
    }

    public static GameState makeMove(String move) {
        return gameState;
    }
}
