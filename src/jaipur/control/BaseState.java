package jaipur.control;

import java.util.HashMap;

/**
 * 保存一些基本的游戏状态
 */
public class BaseState {

    private int handOrder = 0;//先后手次序 0:先手 1:后手
    private HashMap<String, Class> commandMap = new HashMap<>();//命令列表

    private static BaseState instance = new BaseState();

    private BaseState(){ }

    public static BaseState getInstance() {
        if (instance == null) {
            instance = new BaseState();
        }
        return instance;
    }

    public int getHandOrder() {
        return handOrder;
    }

    public void setHandOrder(int handOrder) {
        this.handOrder = handOrder;
    }

    public HashMap<String, Class> getCommandMap() {
        return commandMap;
    }

    public void setCommandMap(HashMap<String, Class> commandMap) {
        this.commandMap = commandMap;
    }
}
