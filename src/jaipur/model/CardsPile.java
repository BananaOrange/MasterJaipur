package jaipur.model;

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
}
