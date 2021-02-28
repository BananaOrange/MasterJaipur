package jaipur.model;

import jaipur.control.BaseState;

import java.util.HashMap;

/**
 * 玩家类
 */
public class Player {

    private int minScore;//玩家最低得分(用来处理奖励标志分值不确定的情况)
    private int maxScore;//玩家最高得分(用来处理奖励标志分值不确定的情况)
    private int[] handCards;//玩家手牌(数组容量7,分别对应钻石/黄金/白银/丝绸/香料/皮革/骆驼的数量)
    private boolean camelBonus;//是否有骆驼币奖励

    public Player() {
        this.minScore = 0;
        this.maxScore = 0;
        this.handCards = new int[]{0, 0, 0, 0, 0, 0, 0};
        this.camelBonus = false;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public int[] getHandCards() {
        return handCards;
    }

    public void setHandCards(int[] handCards) {
        this.handCards = handCards;
    }

    public boolean isCamelBonus() {
        return camelBonus;
    }

    public void setCamelBonus(boolean camelBonus) {
        this.camelBonus = camelBonus;
    }

    /**
     * 设置手牌
     *
     * 说明：命令中数字表示公共牌堆中要设置的牌的数量
     */
    public void fitHandCards(String command) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            int index = itemMap.get(sub);
            handCards[index] = Integer.parseInt(sub.substring(0, 1));
        }
    }

    /**
     * 将牌堆中牌的数量设置为-1，表示数量未知
     */
    public void fitHandCardsWithMinusOne(int index) {
        handCards[index] = -1;
    }
}
