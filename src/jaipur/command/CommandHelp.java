package jaipur.command;

import jaipur.annotation.Command;
import jaipur.view.StoredViews;

/**
 * 用途：显示命令列表
 */
@Command("help")
public class CommandHelp extends BaseCommand{
    /**
     * 执行命令
     */
    public static void exec(String command) {

        StringBuffer sb = new StringBuffer();

        sb.append("start [公共牌堆] [我的手牌] [对手骆驼牌] [先后手]\n");
        sb.append("start 1h1z 1x2b1s1p ? 0\n");
        sb.append("\n");
        sb.append("take [公共牌堆] [翻出的牌]\n");
        sb.append("take 1h 1l\n");
        sb.append("\n");
        sb.append("swap [公共牌堆] [玩家手牌]\n");
        sb.append("swap 1z1h 2p\n");
        sb.append("\n");
        sb.append("sell [玩家手牌]\n");
        sb.append("sell 3z\n");
        sb.append("\n");
        sb.append("camel [公共牌堆中骆驼牌] [翻出的牌]\n");
        sb.append("camel 3l 1s1x1p\n");
        sb.append("\n");
        sb.append("ai [搜索深度]\n");
        sb.append("ai 5\n");

        StoredViews.getInstance().showCommandMessage(sb.toString());

        execSuccess(false, true);
    }

}
