package jaipur.control;

import jaipur.model.BonusPile;
import jaipur.model.CardsPile;
import jaipur.model.GoodsPile;
import jaipur.model.Player;

/**
 * 保存全局游戏状态
 */
public class GlobalState {


    private int handOrder = 0;//先后手次序 0:先手 1:后手

    private Player opponent = new Player();//玩家：对手
    private Player myself = new Player();//玩家：自己
    private CardsPile cardsPile = new CardsPile();//牌堆
    private GoodsPile goodsPile = new GoodsPile();//货物标志堆
    private BonusPile bonusPile = new BonusPile();//奖励标志堆

    private static GlobalState instance = new GlobalState();

    /**
     * 私有构造方法
     */
    private GlobalState() { }

    /**
     * 获取实例
     */
    public static GlobalState getInstance() {
        if (instance == null) {
            instance = new GlobalState();
        }
        return instance;
    }

    public int getHandOrder() {
        return handOrder;
    }

    public void setHandOrder(int handOrder) {
        this.handOrder = handOrder;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Player getMyself() {
        return myself;
    }

    public void setMyself(Player myself) {
        this.myself = myself;
    }

    public CardsPile getCardsPile() {
        return cardsPile;
    }

    public void setCardsPile(CardsPile cardsPile) {
        this.cardsPile = cardsPile;
    }

    public GoodsPile getGoodsPile() {
        return goodsPile;
    }

    public void setGoodsPile(GoodsPile goodsPile) {
        this.goodsPile = goodsPile;
    }

    public BonusPile getBonusPile() {
        return bonusPile;
    }

    public void setBonusPile(BonusPile bonusPile) {
        this.bonusPile = bonusPile;
    }
}
