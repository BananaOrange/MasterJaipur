package jaipur.model;

import jaipur.constant.Const;
import jaipur.control.BaseState;

import java.util.HashMap;

/**
 * 牌堆类，保存每种牌的相关信息
 */
public class CardsPile {
    private int[] unknownCards;//未翻开的牌堆(数组容量7,分别对应钻石/黄金/白银/丝绸/香料/皮革/骆驼的数量)
    private int[] publicCards;//公共牌堆(数组容量7,分别对应钻石/黄金/白银/丝绸/香料/皮革/骆驼的数量)

    public CardsPile() {
        this.unknownCards = new int[7];
        this.publicCards = new int[7];
    }

    public int[] getUnknownCards() {
        return unknownCards;
    }

    public void setUnknownCards(int[] unknownCards) {
        this.unknownCards = unknownCards;
    }

    public int[] getPublicCards() {
        return publicCards;
    }

    public void setPublicCards(int[] publicCards) {
        this.publicCards = publicCards;
    }

    /**
     * 初始化未翻开的牌堆
     */
    public void initUnknownCards() {
        //初始化未翻开牌堆
        unknownCards[0] = Const.DIAMOND_CARD_NUM;
        unknownCards[1] = Const.GOLD_CARD_NUM;
        unknownCards[2] = Const.SILVER_CARD_NUM;
        unknownCards[3] = Const.SILK_CARD_NUM;
        unknownCards[4] = Const.SPICE_CARD_NUM;
        unknownCards[5] = Const.LEATHER_CARD_NUM;
        unknownCards[6] = Const.CAMEL_CARD_NUM;
        //初始化公共牌堆
        publicCards[0] = 0;
        publicCards[1] = 0;
        publicCards[2] = 0;
        publicCards[3] = 0;
        publicCards[4] = 0;
        publicCards[5] = 0;
        publicCards[6] = 0;
    }

    /**
     * 发牌，即从未翻开的牌堆中翻牌
     *
     * 说明：命令中数字表示从未翻开牌堆中减去的牌的数量
     */
    public void removeUnknownCards(String command) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            int index = itemMap.get(sub);
            unknownCards[index] = unknownCards[index] - Integer.parseInt(sub.substring(0, 1));
        }
    }

    /**
     * 给公共牌堆添加牌
     *
     * 说明：命令中数字表示公共牌堆中添加的牌的数量
     */
    public void addPublicCards(String command) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            int index = itemMap.get(sub);
            publicCards[index] = publicCards[index] + Integer.parseInt(sub.substring(0, 1));
        }
    }

    /**
     * 从公共牌堆中拿牌
     *
     * 说明：命令中数字表示公共牌堆中减去的牌的数量
     */
    public void removePublicCards(String command) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            int index = itemMap.get(sub);
            publicCards[index] = publicCards[index] - Integer.parseInt(sub.substring(0, 1));
        }
    }
}
