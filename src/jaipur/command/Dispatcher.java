package jaipur.command;

/**
 * 命令分发类
 */
public class Dispatcher {
    /**
     * 分发命令
     */
    public static void postCommand(String command) {
        if(simpleCheck(command)) {
            // TODO: 2021-02-11 利用反射实现命令分发
        }else {
            // TODO: 2021-02-11 调用统一的错误命令处理方法
        }
    }

    /**
     * 对输入命令进行简单判断
     */
    public static boolean simpleCheck(String command) {
        boolean flag = false;//命令判断结果
    }
}
