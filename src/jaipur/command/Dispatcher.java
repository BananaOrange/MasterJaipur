package jaipur.command;

import jaipur.control.BaseState;

import java.lang.reflect.Method;
import java.util.HashMap;

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
                Method execMethod = clazz.getDeclaredMethod("exec" );
                execMethod.invoke(clazz);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            //throw e;
        }

    }

    /**
     * 装载命令
     */
    public static void loadCommand() {
        BaseState baseState = BaseState.getInstance();
        HashMap<String, Class> commandMap = baseState.getCommandMap();
        commandMap.put("start", CommandStart.class);
        baseState.setCommandMap(commandMap);
    }
}
