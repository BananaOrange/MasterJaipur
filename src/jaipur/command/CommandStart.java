package jaipur.command;

import jaipur.annotation.Command;

/**
 * 命令：开始游戏
 * 用途：开始一局新游戏，并初始化必要的状态
 *
 * 语法：start [公共牌堆] [我的手牌] [对手骆驼牌数量] [先后手]
 * 举例：start 1h1z3l 1x2b1s1p ? 0
 *
 * 说明：当不确定对手的骆驼牌数量时，可以使用 ? 指代
 * 说明：0表示自己是先手; 1表示自己是后手
 */
@Command("start")
public class CommandStart {
    /**
     * 执行命令
     */
    public static void exec() {

    }
}
