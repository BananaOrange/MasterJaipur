package jaipur.model;

import jaipur.control.BaseState;

import java.util.HashMap;

/**
 * 奖励标志类
 */
public class BonusPile {
    private int threeMinScore;//三个货物一起出售的奖励分最小值
    private int threeMaxScore;//三个货物一起出售的奖励分最大值
    private int threeTokenNum;//标志物个数

    private int fourMinScore;//四个货物一起出售的奖励分最小值
    private int fourMaxScore;//四个货物一起出售的奖励分最大值
    private int fourTokenNum;//标志物个数

    private int fiveMinScore;//五个货物一起出售的奖励分最小值
    private int fiveMaxScore;//五个货物一起出售的奖励分最大值
    private int fiveTokenNum;//标志物个数

    public BonusPile() {
        this.threeMinScore = 1;
        this.threeMaxScore = 3;
        this.threeTokenNum = 6;

        this.fourMinScore = 4;
        this.fourMaxScore = 6;
        this.fourTokenNum = 6;

        this.fiveMinScore = 8;
        this.fiveMaxScore = 10;
        this.fiveTokenNum = 6;
    }

    public int getThreeMinScore() {
        return threeMinScore;
    }

    public void setThreeMinScore(int threeMinScore) {
        this.threeMinScore = threeMinScore;
    }

    public int getThreeMaxScore() {
        return threeMaxScore;
    }

    public void setThreeMaxScore(int threeMaxScore) {
        this.threeMaxScore = threeMaxScore;
    }

    public int getThreeTokenNum() {
        return threeTokenNum;
    }

    public void setThreeTokenNum(int threeTokenNum) {
        this.threeTokenNum = threeTokenNum;
    }

    public int getFourMinScore() {
        return fourMinScore;
    }

    public void setFourMinScore(int fourMinScore) {
        this.fourMinScore = fourMinScore;
    }

    public int getFourMaxScore() {
        return fourMaxScore;
    }

    public void setFourMaxScore(int fourMaxScore) {
        this.fourMaxScore = fourMaxScore;
    }

    public int getFourTokenNum() {
        return fourTokenNum;
    }

    public void setFourTokenNum(int fourTokenNum) {
        this.fourTokenNum = fourTokenNum;
    }

    public int getFiveMinScore() {
        return fiveMinScore;
    }

    public void setFiveMinScore(int fiveMinScore) {
        this.fiveMinScore = fiveMinScore;
    }

    public int getFiveMaxScore() {
        return fiveMaxScore;
    }

    public void setFiveMaxScore(int fiveMaxScore) {
        this.fiveMaxScore = fiveMaxScore;
    }

    public int getFiveTokenNum() {
        return fiveTokenNum;
    }

    public void setFiveTokenNum(int fiveTokenNum) {
        this.fiveTokenNum = fiveTokenNum;
    }

    /**
     *  获取出售货物后的奖励分值
     */
    public int[] getBonusScore(String command) {

        int[] score = new int[]{0, 0};
        int goodsNum = Integer.parseInt(command.substring(0, 1));//货物个数

        if(goodsNum < 3) {
            return score;
        }else {
            switch (goodsNum) {
                case 3 :
                    score[0] = threeMinScore;
                    score[1] = threeMaxScore;
                    threeTokenNum --;
                    break;

                case 4 :
                    score[0] = fourMinScore;
                    score[1] = fourMaxScore;
                    fourTokenNum --;
                    break;

                case 5 :
                    score[0] = fiveMinScore;
                    score[1] = fiveMaxScore;
                    fiveTokenNum --;
                    break;
                default:
                    score[0] = 0;
                    score[1] = 0;
            }
        }
        return score;
    }
}
