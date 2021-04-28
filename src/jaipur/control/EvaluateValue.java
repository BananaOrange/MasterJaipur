package jaipur.control;

import jaipur.constant.Const;
import jaipur.constant.HandOrder;
import jaipur.model.GoodsPile;
import jaipur.model.Player;

/**
 * 局面评估函数
 */
public class EvaluateValue {
    /**
     * 复杂估值策略,考虑局面优劣等
     *
     * 注：返回值为自己的局面分和对手局面分差值。差值为正数，表示自己局面占优，反之对手局面占优。
     */
    public static int complexEval(GameState gameState) {
        //初始化
        Player myself = gameState.getMyself();
        Player opponent = gameState.getOpponent();
        GoodsPile goodsPile = gameState.getGoodsPile();
        int myselfScore = 0;
        int opponentScore = 0;

        //基础得分
        myselfScore += myself.getMinScore();
        opponentScore += opponent.getMaxScore();

        //骆驼牌得分
        if(myself.getHandCards()[6] > opponent.getHandCards()[6]) {
            myselfScore += Const.CAMEL_CARD_BONUS;
        }else if(myself.getHandCards()[6] < opponent.getHandCards()[6]) {
            opponentScore += Const.CAMEL_CARD_BONUS;
        }

        //将得分乘以固定系数,以便进行后续的局面优劣评判
        myselfScore *= Const.EVAL_SCALE;
        opponentScore *= Const.EVAL_SCALE;

        //当游戏已结束时的得分
        if(gameState.isEnded()) {
            if(myselfScore > opponentScore) {
                myselfScore += Const.MAX_INT;
            }else if(myselfScore < opponentScore) {
                opponentScore += Const.MAX_INT;
            }

            return myselfScore - opponentScore;
        }

        //局面动态评分
        int myselfHandCard = 0;
        int opponentHandCard = 0;

        for(int i=0; i<6; i++) {
            myselfHandCard = myself.getHandCards()[i];
            opponentHandCard = opponent.getHandCards()[i];

            //手牌中可售卖的货物得分
            myselfScore += myselfHandCard;
            opponentScore += opponentHandCard;

            //牵制对手得分
            if(myselfHandCard > opponentHandCard) {
                myselfScore += 1;
            }else if(myselfHandCard < opponentHandCard) {
                opponentScore += 1;
            }

            //控制游戏局势得分-售卖货物将货物堆清零
            if(goodsPile.getZeroPileNum() == 2) {
                if(myselfHandCard > goodsPile.getGoodsLeftNum()[i]) {
                    myselfScore += 5;
                }
                if(opponentHandCard < goodsPile.getGoodsLeftNum()[i]) {
                    opponentScore += 5;
                }
            }

            //控制游戏局势得分-拿取一张牌将未翻开牌堆清零
            if(gameState.getCardsPile().getLeftNum() == 1) {
                if(gameState.getHandOrder() == HandOrder.MYSELF) {
                    if(myself.getHandCardsNum() < Const.HAND_CARD_LIMIT) {
                        myselfScore += 5;
                    }
                }else {
                    if(opponent.getHandCardsNum() < Const.HAND_CARD_LIMIT) {
                        opponentScore += 5;
                    }
                }
            }

            //控制游戏局势得分-拿取骆驼牌将未翻开牌堆清零
            if((gameState.getCardsPile().getLeftNum() >= 2) && (gameState.getCardsPile().getLeftNum() <= 5)) {
                if(gameState.getHandOrder() == HandOrder.MYSELF) {
                    myselfScore += 5;
                }else {
                    opponentScore += 5;
                }
            }
        }

        return myselfScore - opponentScore;
    }
}
