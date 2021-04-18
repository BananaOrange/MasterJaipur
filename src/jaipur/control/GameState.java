package jaipur.control;

import jaipur.constant.HandOrder;
import jaipur.model.BonusPile;
import jaipur.model.CardsPile;
import jaipur.model.GoodsPile;
import jaipur.model.Player;

import java.util.ArrayList;
import java.util.Arrays;

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

    private static GameState instance;

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
        GuessCards guessCards = BaseState.getInstance().getGuessCards();
        //刷新玩家信息
        gameInfo.append("当前游戏方：" + (handOrder==HandOrder.MYSELF?"我":"对手"));
        gameInfo.append("                手牌猜测成功：" + (guessCards.getGuessFlag()?"是":"否") + "\n");
        gameInfo.append("\n");
        gameInfo.append("---------------------------玩家信息---------------------------\n");
        gameInfo.append("【我】得分：" + myself.getMinScore() + " ~ " + myself.getMaxScore());
        gameInfo.append("                              骆驼牌：" + myself.getHandCards()[6] + "\n");
        gameInfo.append("钻石：" + myself.getHandCards()[0] + "  黄金：" + myself.getHandCards()[1] + "  白银：" + myself.getHandCards()[2] + "\n");
        gameInfo.append("丝绸：" + myself.getHandCards()[3] + "  香料：" + myself.getHandCards()[4] + "  皮革：" + myself.getHandCards()[5] + "\n");
        gameInfo.append("\n");
        gameInfo.append("【对手】得分：" + opponent.getMinScore() + " ~ " + opponent.getMaxScore());
        gameInfo.append("                           骆驼牌：" + opponent.getHandCards()[6] + "\n");
        gameInfo.append("钻石：" + opponent.getHandCards()[0] + "  黄金：" + opponent.getHandCards()[1] + "  白银：" + opponent.getHandCards()[2] + "\n");
        gameInfo.append("丝绸：" + opponent.getHandCards()[3] + "  香料：" + opponent.getHandCards()[4] + "  皮革：" + opponent.getHandCards()[5] + "\n");
        gameInfo.append("\n");
        gameInfo.append("---------------------------发牌堆信息-----------------------\n");
        gameInfo.append("钻石：" + cardsPile.getUnknownCards()[0] + "  黄金：" + cardsPile.getUnknownCards()[1] + "  白银：" + cardsPile.getUnknownCards()[2] + "\n");
        gameInfo.append("丝绸：" + cardsPile.getUnknownCards()[3] + "  香料：" + cardsPile.getUnknownCards()[4] + "  皮革：" + cardsPile.getUnknownCards()[5] + "\n");
        gameInfo.append("骆驼：" + cardsPile.getUnknownCards()[6] + "\n");
        gameInfo.append("---------------------------公共牌堆信息-----------------------\n");
        gameInfo.append("钻石：" + cardsPile.getPublicCards()[0] + "  黄金：" + cardsPile.getPublicCards()[1] + "  白银：" + cardsPile.getPublicCards()[2] + "\n");
        gameInfo.append("丝绸：" + cardsPile.getPublicCards()[3] + "  香料：" + cardsPile.getPublicCards()[4] + "  皮革：" + cardsPile.getPublicCards()[5] + "\n");
        gameInfo.append("骆驼：" + cardsPile.getPublicCards()[6] + "\n");
        gameInfo.append("\n");
        gameInfo.append("---------------------------奖励信息---------------------------\n");
        gameInfo.append("钻石：" + Arrays.toString(goodsPile.getDiamondScore()) + "\n");
        gameInfo.append("黄金：" + Arrays.toString(goodsPile.getGoldScore()) + "\n");
        gameInfo.append("白银：" + Arrays.toString(goodsPile.getSilverScore()) + "\n");
        gameInfo.append("丝绸：" + Arrays.toString(goodsPile.getSilkScore()) + "\n");
        gameInfo.append("香料：" + Arrays.toString(goodsPile.getSpiceScore()) + "\n");
        gameInfo.append("皮革：" + Arrays.toString(goodsPile.getLeatherScore()) + "\n");
        gameInfo.append("\n");
        gameInfo.append("三堆：" + bonusPile.getThreeTokenNum());
        gameInfo.append("  四堆：" + bonusPile.getFourTokenNum());
        gameInfo.append("  五堆：" + bonusPile.getFiveTokenNum());

        return gameInfo.toString();
    }
}
