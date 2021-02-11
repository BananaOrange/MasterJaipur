package jaipur.model;

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
        this.handCards = new int[7];
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
}
