package jaipur.control;

/**
 * 保存全局游戏状态
 */
public class GlobalState {

    private static GlobalState instance = new GlobalState();

    //私有构造方法
    private GlobalState() { }

    public static GlobalState getInstance() {
        if (instance == null) {
            instance = new GlobalState();
        }
        return instance;
    }

}
