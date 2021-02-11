package jaipur.model;

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
}
