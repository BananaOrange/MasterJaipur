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
     * 添加手牌
     *
     * 说明：命令中数字表示手牌堆中添加的牌的数量
     */
    public void addHandCards(String command) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            int index = itemMap.get(sub);
            handCards[index] = handCards[index] + Integer.parseInt(sub.substring(0, 1));
        }
    }

    /**
     * 拿取手牌
     *
     * 说明：命令中数字表示手牌堆中减去的牌的数量
     */
    public void removeHandCards(String command) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            int index = itemMap.get(sub);
            handCards[index] = handCards[index] - Integer.parseInt(sub.substring(0, 1));
        }
    }

    /**
     * 将手牌堆中牌的数量设置为-1，表示数量未知
     */
    public void setUnknownHandCards(int index) {
        handCards[index] = -1;
    }

    /**
     *  更新玩家分数
     *
     *  说明：传入的参数表示待增加的分数范围
     */
    public void addScore(int min, int max) {
        minScore += min;
        maxScore += max;
    }
}
