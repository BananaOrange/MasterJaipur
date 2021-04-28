package jaipur.constant;

/**
 * 保存一些常量
 */
public class Const {

    public static final int UNKNOWN_CARDS_NUM = 55;//未翻开牌堆初始数量

    public static final int DIAMOND_CARD_NUM = 6;//钻石牌数量

    public static final int GOLD_CARD_NUM = 6;//黄金牌数量

    public static final int SILVER_CARD_NUM = 6;//白银牌数量

    public static final int SILK_CARD_NUM = 8;//丝绸牌数量

    public static final int SPICE_CARD_NUM = 8;//香料牌数量

    public static final int LEATHER_CARD_NUM = 10;//皮革牌数量

    public static final int CAMEL_CARD_NUM = 11;//骆驼牌数量

    public static final int CAMEL_CARD_BONUS = 5;//骆驼牌得分奖励

    public static final int HAND_CARD_LIMIT = 7;//手牌数量限制

    public static final int HARD_CARD_INIT = 5;//初始手牌数量

    public static final int EVAL_SCALE = 10;//局面评估时的估值放大系数

    public static final int MAX_INT = 9999;//游戏结束时的附加得分

    public static final String COMMAND_PARSING_ERROR = "命令解析错误,请重新输入";

    public static final String COMMAND_PARAM_MISSING = "命令参数个数不足,请重新输入";

    public static final String COMMAND_ITEMS_ERROR = "命令中货物通用命令输入错误,请重新输入";

    public static final String COMMAND_ORDER_ERROR = "命令中先后手标志输入错误,请重新输入";

    public static final String GAME_HAS_NOT_STARTED = "游戏尚未开始,请重新输入";
}
