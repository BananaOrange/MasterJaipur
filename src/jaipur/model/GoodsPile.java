package jaipur.model;

import jaipur.control.BaseState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 货物标志类
 */
public class GoodsPile {
    private int[] diamondScore;//钻石标志物(存储的是分值)
    private int[] goldScore;//黄金标志物(存储的是分值)
    private int[] silverScore;//白银标志物(存储的是分值)
    private int[] silkScore;//丝绸标志物(存储的是分值)
    private int[] spiceScore;//香料标志物(存储的是分值)
    private int[] leatherScore;//皮革标志物(存储的是分值)
    private int zeroPileNum;//个数为零的标志物堆数量

    public GoodsPile() {
        this.diamondScore = new int[]{7, 7, 5, 5, 5};
        this.goldScore =    new int[]{6, 6, 5, 5, 5};
        this.silverScore =  new int[]{5, 5, 5, 5, 5};
        this.silkScore =    new int[]{5, 3, 3, 2, 2, 1, 1};
        this.spiceScore =   new int[]{5, 3, 3, 2, 2, 1, 1};
        this.leatherScore = new int[]{4, 3, 2, 1, 1, 1, 1, 1, 1};
        this.zeroPileNum = 0;
    }

    public int[] getDiamondScore() {
        return diamondScore;
    }

    public void setDiamondScore(int[] diamondScore) {
        this.diamondScore = diamondScore;
    }

    public int[] getGoldScore() {
        return goldScore;
    }

    public void setGoldScore(int[] goldScore) {
        this.goldScore = goldScore;
    }

    public int[] getSilverScore() {
        return silverScore;
    }

    public void setSilverScore(int[] silverScore) {
        this.silverScore = silverScore;
    }

    public int[] getSilkScore() {
        return silkScore;
    }

    public void setSilkScore(int[] silkScore) {
        this.silkScore = silkScore;
    }

    public int[] getSpiceScore() {
        return spiceScore;
    }

    public void setSpiceScore(int[] spiceScore) {
        this.spiceScore = spiceScore;
    }

    public int[] getLeatherScore() {
        return leatherScore;
    }

    public void setLeatherScore(int[] leatherScore) {
        this.leatherScore = leatherScore;
    }

    public int getZeroPileNum() {
        return zeroPileNum;
    }

    public void setZeroPileNum(int zeroPileNum) {
        this.zeroPileNum = zeroPileNum;
    }

    /**
     *  获取出售货物后的分值
     */
    public int getGoodsScore(String item) {
        HashMap<String, Integer> itemMap = BaseState.getInstance().getItemMap();

        int index = itemMap.get(item);
        int score = 0;

        switch (index) {
            case 0 :
                score = removeGoods(diamondScore, item);
                break;

            case 1 :
                score = removeGoods(goldScore, item);
                break;

            case 2 :
                score = removeGoods(silverScore, item);
                break;

            case 3 :
                score = removeGoods(silkScore, item);
                break;

            case 4 :
                score = removeGoods(spiceScore, item);
                break;

            case 5 :
                score = removeGoods(leatherScore, item);
                break;

            default:
                score = 0;
        }

        return score;
    }

    public int removeGoods(int[] goodsPile, String item) {
        int goodsNum = Integer.parseInt(item.substring(0, 1));//货物个数
        int score = 0;
        int count = 0;

        for (int i=0; i<goodsPile.length; i++) {
            if(goodsPile[i] != 0) {
                score += goodsPile[i];
                goodsPile[i] = 0;
                count++;
            }
            if(count == goodsNum) {
                break;
            }
        }

        if(score == 0) {
            zeroPileNum ++;
        }

        return score;
    }
}
