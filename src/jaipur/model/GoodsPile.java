package jaipur.model;

import java.util.ArrayList;
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

    public GoodsPile() {
        this.diamondScore = new int[]{7, 7, 5, 5, 5};
        this.goldScore =    new int[]{6, 6, 5, 5, 5};
        this.silverScore =  new int[]{5, 5, 5, 5, 5};
        this.silkScore =    new int[]{5, 3, 3, 2, 2, 1, 1};
        this.spiceScore =   new int[]{5, 3, 3, 2, 2, 1, 1};
        this.leatherScore = new int[]{4, 3, 2, 1, 1, 1, 1, 1, 1};
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
}
