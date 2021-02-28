package jaipur.model;

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
        unknownCards[0] = 6;
        unknownCards[1] = 6;
        unknownCards[2] = 6;
        unknownCards[3] = 8;
        unknownCards[4] = 8;
        unknownCards[5] = 10;
        unknownCards[6] = 11;
    }

    /**
     * 发牌，即从未翻开的牌堆中翻牌
     *
     * 说明：命令中数字表示从未翻开牌堆中减去的牌的数量
     */
    public void deelCards(String command) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            int index = itemMap.get(sub);
            unknownCards[index] = unknownCards[index] - Integer.parseInt(sub.substring(0, 1));
        }
    }

    /**
     * 设置公共牌堆
     *
     * 说明：命令中数字表示公共牌堆中要设置的牌的数量
     */
    public void fitPublicCards(String command) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            int index = itemMap.get(sub);
            publicCards[index] = Integer.parseInt(sub.substring(0, 1));
        }
    }
}
