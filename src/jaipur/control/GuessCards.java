package jaipur.control;

import jaipur.constant.Const;

import java.util.HashMap;

/**
 * 猜测对手手牌
 */
public class GuessCards {

    private boolean guessFlag;//是否已经猜测出对手手牌
    private int knownCardsNum;//已知手牌数
    private int allCardsNum;//所有手牌数
    private int[] knownHandCards;//已知的玩家手牌(数组容量7,分别对应钻石/黄金/白银/丝绸/香料/皮革/骆驼的数量)

    public GuessCards() {
        guessFlag = false;
        knownCardsNum = 0;
        allCardsNum = Const.HARD_CARD_INIT;
        knownHandCards = new int[]{0, 0, 0, 0, 0, 0, 0};
    }

    public boolean getGuessFlag() {
        return guessFlag;
    }

    public void setGuessFlag(boolean guessFlag) {
        this.guessFlag = guessFlag;
    }

    public int getKnownCardsNum() {
        return knownCardsNum;
    }

    public void setKnownCardsNum(int knownCardsNum) {
        this.knownCardsNum = knownCardsNum;
    }

    public int getAllCardsNum() {
        return allCardsNum;
    }

    public void setAllCardsNum(int allCardsNum) {
        this.allCardsNum = allCardsNum;
    }

    public int[] getKnownHandCards() {
        return knownHandCards;
    }

    public void setKnownHandCards(int[] knownHandCards) {
        this.knownHandCards = knownHandCards;
    }

    /**
     * 在执行完start命令之后，猜测对手手牌
     *
     * @param item [对手骆驼牌]
     */
    public void guessByStart(String item) {
        int subNum = Integer.parseInt(item.substring(0, 1));

        if(!item.equals("?")) {
            //更新已知手牌数
            knownCardsNum = subNum;

            //更新已知手牌
            int index = BaseState.getInstance().getItemMap().get(item);
            knownHandCards[index] = subNum;
        }

        //更新猜测标记
        guessFlag = (knownCardsNum == allCardsNum);
    }

    /**
     * 在执行完take命令之后，猜测对手手牌
     *
     * @param item [公共牌堆]
     */
    public void guessByTake(String item) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();
        int subNum = Integer.parseInt(item.substring(0, 1));

        //更新已知手牌数
        knownCardsNum += subNum;

        //更新已知手牌
        int index = itemMap.get(item);
        knownHandCards[index] += subNum;

        //更新所有手牌数
        allCardsNum ++;

        //更新猜测标记
        guessFlag = (knownCardsNum == allCardsNum);
    }

    /**
     * 在执行完swap命令之后，猜测对手手牌
     *
     * @param item1 [公共牌堆]
     * @param item2 [玩家手牌]
     */
    public void guessBySwap(String item1, String item2) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        //从公共牌堆取牌
        for(int i=1; i<=item1.length()/2; i++) {
            String sub = item1.substring(i*2-2, i*2);
            int subNum = Integer.parseInt(sub.substring(0, 1));

            //更新已知手牌数
            knownCardsNum += subNum;
            //更新已知手牌
            knownHandCards[itemMap.get(sub)] += subNum;
            //更新所有手牌数
            allCardsNum += subNum;
        }

        //交出玩家手牌
        for(int i=1; i<=item2.length()/2; i++) {
            String sub = item2.substring(i*2-2, i*2);
            int subNum = Integer.parseInt(sub.substring(0, 1));

            //需判断从已知牌堆中取牌的情况
            int removeNum = subNum;
            int storedNum = knownHandCards[itemMap.get(sub)];

            if(storedNum == 0) { //全部从未知手牌中取牌

                //更新所有手牌数
                allCardsNum -= removeNum;

            } else {
                if (removeNum <= storedNum) { //全部从已知手牌中取牌

                    //更新已知手牌数
                    knownCardsNum -= removeNum;
                    //更新已知手牌
                    knownHandCards[itemMap.get(sub)] -= removeNum;
                    //更新所有手牌数
                    allCardsNum -= removeNum;

                }else { //一部分从未知手牌中取牌,一部分从已知手牌中取牌

                    //更新已知手牌数
                    knownCardsNum -= storedNum;
                    //更新已知手牌
                    knownHandCards[itemMap.get(sub)] -= storedNum;
                    //更新所有手牌数
                    allCardsNum -= removeNum;
                }
            }
        }

        //更新猜测标记
        guessFlag = (knownCardsNum == allCardsNum);
    }

    /**
     * 在执行完sell命令之后，猜测对手手牌
     *
     * @param item [玩家手牌]
     */
    public void guessBySell(String item) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        //交出玩家手牌
        for(int i=1; i<=item.length()/2; i++) {
            String sub = item.substring(i*2-2, i*2);
            int subNum = Integer.parseInt(sub.substring(0, 1));

            //需判断从已知牌堆中取牌的情况
            int removeNum = subNum;
            int storedNum = knownHandCards[itemMap.get(sub)];

            if(storedNum == 0) { //全部从未知手牌中取牌

                //更新所有手牌数
                allCardsNum -= removeNum;

            } else {
                if (removeNum <= storedNum) { //全部从已知手牌中取牌

                    //更新已知手牌数
                    knownCardsNum -= removeNum;
                    //更新已知手牌
                    knownHandCards[itemMap.get(sub)] -= removeNum;
                    //更新所有手牌数
                    allCardsNum -= removeNum;

                }else { //一部分从未知手牌中取牌,一部分从已知手牌中取牌

                    //更新已知手牌数
                    knownCardsNum -= storedNum;
                    //更新已知手牌
                    knownHandCards[itemMap.get(sub)] -= storedNum;
                    //更新所有手牌数
                    allCardsNum -= removeNum;
                }
            }
        }

        //更新猜测标记
        guessFlag = (knownCardsNum == allCardsNum);
    }

    /**
     * 在执行完camel命令之后，猜测对手手牌
     *
     * @param item [公共牌堆中骆驼牌]
     */
    public void guessByCamel(String item) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();
        int subNum = Integer.parseInt(item.substring(0, 1));

        //更新已知手牌数
        knownCardsNum += subNum;

        //更新已知手牌
        int index = itemMap.get(item);
        knownHandCards[index] += subNum;

        //更新所有手牌数
        allCardsNum ++;

        //更新猜测标记
        guessFlag = (knownCardsNum == allCardsNum);
    }

    /**
     * 将猜测结果复制到玩家类中
     */
    public void copyGuessCards() {
        GameState.getInstance().getOpponent().setHandCards(knownHandCards);
    }
}
