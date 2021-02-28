package jaipur.command;

import jaipur.control.BaseState;

import java.util.HashMap;

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

    /**
     * 校验输入的货物命令是否正确
     *
     * 说明：长度必须是偶数，且每2个字符都应该在预设命令列表中
     */
    public static boolean checkItems(String command) {
        if(command.length()%2 != 0) {
            return false;
        }

        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        for(int i=1; i<=command.length()/2; i++) {
            String sub = command.substring(i*2-2, i*2);
            if(itemMap.get(sub) == null) {
                return false;
            }
        }

        return true;
    }
}
