package jaipur.command;

import jaipur.constant.Const;
import jaipur.control.BaseState;
import jaipur.view.StoredViews;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 命令分发(处理)类
 */
public class Dispatcher {
    /**
     * 分发命令
     */
    public static void postCommand(String command) {
        try{
            //对输入命令做简单的校验
            String commandKey;
            command = command.trim();
            if(command.contains(" ")) {
                commandKey = command.split("\\s+")[0];
            }else {
                commandKey = command;
            }
            //判断是否是预设命令
            HashMap<String, Class> commandMap = BaseState.getInstance().getCommandMap();
            if (commandMap.get(commandKey) != null) {
                Class clazz = commandMap.get(commandKey);
                Method execMethod = clazz.getDeclaredMethod("exec", String.class );
                execMethod.invoke(clazz, new Object[]{command} );
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            StoredViews.getInstance().showCommandMessage(Const.COMMAND_PARSING_ERROR);
        }

    }

    /**
     * 装载命令
     */
    public static void loadCommand() {
        //获取实例
        BaseState baseState = BaseState.getInstance();
        //装载命令列表
        HashMap<String, Class> commandMap = baseState.getCommandMap();
        commandMap.put("start", CommandStart.class);
        baseState.setCommandMap(commandMap);
        //装载预设物品列表
        HashSet<String> itemSet = baseState.getItemSet();
        itemSet.add("1z");itemSet.add("2z");itemSet.add("3z");itemSet.add("4z");itemSet.add("5z");
        itemSet.add("1h");itemSet.add("2h");itemSet.add("3h");itemSet.add("4h");itemSet.add("5h");
        itemSet.add("1b");itemSet.add("2b");itemSet.add("3b");itemSet.add("4b");itemSet.add("5b");
        itemSet.add("1s");itemSet.add("2s");itemSet.add("3s");itemSet.add("4s");itemSet.add("5s");
        itemSet.add("1x");itemSet.add("2x");itemSet.add("3x");itemSet.add("4x");itemSet.add("5x");
        itemSet.add("1p");itemSet.add("2p");itemSet.add("3p");itemSet.add("4p");itemSet.add("5p");
        itemSet.add("1l");itemSet.add("2l");itemSet.add("3l");itemSet.add("4l");itemSet.add("5l");
        baseState.setItemSet(itemSet);

    }
}
