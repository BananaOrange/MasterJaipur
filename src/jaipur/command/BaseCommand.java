package jaipur.command;

/**
 * 命令类基类
 */
public class BaseCommand {
    /**
     * 拆分输入命令
     */
    public static String[] getSplitCommand(String command) {
        return command.split("\\s+");
    }
}
