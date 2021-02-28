package jaipur.command;

import jaipur.constant.Const;
import jaipur.control.BaseState;
import jaipur.control.GameState;
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
            //刷新并输出游戏全局信息
            String gameInfo = GameState.getInstance().refreshGameState();
            StoredViews.getInstance().showGameInfo(gameInfo);

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
        HashMap<String, Integer> itemMap = baseState.getItemMap();
        itemMap.put("1z", 0);itemMap.put("2z", 0);itemMap.put("3z", 0);itemMap.put("4z", 0);itemMap.put("5z", 0);
        itemMap.put("1h", 1);itemMap.put("2h", 1);itemMap.put("3h", 1);itemMap.put("4h", 1);itemMap.put("5h", 1);
        itemMap.put("1b", 2);itemMap.put("2b", 2);itemMap.put("3b", 2);itemMap.put("4b", 2);itemMap.put("5b", 2);
        itemMap.put("1s", 3);itemMap.put("2s", 3);itemMap.put("3s", 3);itemMap.put("4s", 3);itemMap.put("5s", 3);
        itemMap.put("1x", 4);itemMap.put("2x", 4);itemMap.put("3x", 4);itemMap.put("4x", 4);itemMap.put("5x", 4);
        itemMap.put("1p", 5);itemMap.put("2p", 5);itemMap.put("3p", 5);itemMap.put("4p", 5);itemMap.put("5p", 5);
        itemMap.put("1l", 6);itemMap.put("2l", 6);itemMap.put("3l", 6);itemMap.put("4l", 6);itemMap.put("5l", 6);
        baseState.setItemMap(itemMap);
    }
}
