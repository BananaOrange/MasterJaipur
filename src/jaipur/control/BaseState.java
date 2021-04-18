package jaipur.control;

import java.util.HashMap;

/**
 * 保存一些基本的游戏状态
 */
public class BaseState {

    private boolean startFlag = false;//游戏是否开始
    private GuessCards guessCards = new GuessCards();//对手手牌猜测类
    private HashMap<String, Class> commandMap = new HashMap<>();//命令列表
    private HashMap<String, Integer> itemMap = new HashMap<String, Integer>();//预设物品列表,用于快速判断输入命令正确与否;同时为牌堆数组快速提供下标值,便于操作数组

    private static BaseState instance;

    private BaseState(){ }

    public static BaseState getInstance() {
        if (instance == null) {
            instance = new BaseState();
        }
        return instance;
    }

    public boolean getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    public HashMap<String, Class> getCommandMap() {
        return commandMap;
    }

    public void setCommandMap(HashMap<String, Class> commandMap) {
        this.commandMap = commandMap;
    }

    public HashMap<String, Integer> getItemMap() {
        return itemMap;
    }

    public void setItemMap(HashMap<String, Integer> itemMap) {
        this.itemMap = itemMap;
    }

    public GuessCards getGuessCards() {
        return guessCards;
    }

    public void setGuessCards(GuessCards guessCards) {
        this.guessCards = guessCards;
    }
}
