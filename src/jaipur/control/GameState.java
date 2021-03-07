package jaipur.control;

import jaipur.constant.HandOrder;
import jaipur.model.BonusPile;
import jaipur.model.CardsPile;
import jaipur.model.GoodsPile;
import jaipur.model.Player;

/**
 * 只保存游戏局面,以减少后续的计算量
 */
public class GameState {

    private Enum<HandOrder> handOrder;//当前游戏方
    private Player opponent = new Player();//玩家：对手
    private Player myself = new Player();//玩家：自己
    private CardsPile cardsPile = new CardsPile();//牌堆
    private GoodsPile goodsPile = new GoodsPile();//货物标志堆
    private BonusPile bonusPile = new BonusPile();//奖励标志堆

    private static GameState instance = new GameState();

    /**
     * 私有构造方法
     */
    private GameState() { }

    /**
     * 获取实例
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
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

    public Enum<HandOrder> getHandOrder() {
        return handOrder;
    }

    public void setHandOrder(Enum<HandOrder> handOrder) {
        this.handOrder = handOrder;
    }

    /**
     * 刷新游戏全局信息
     */
    public String refreshGameState() {
        StringBuffer gameInfo = new StringBuffer();
        //刷新玩家信息
        gameInfo.append("---------------------------玩家信息---------------------------\n");
        gameInfo.append("【我】得分：" + myself.getMinScore() + " ~ " + myself.getMaxScore());
        gameInfo.append("                              骆驼牌：" + myself.getHandCards()[6] + "\n");
        gameInfo.append("钻石：" + myself.getHandCards()[0] + "  黄金：" + myself.getHandCards()[1]);

        return gameInfo.toString();
    }
}
