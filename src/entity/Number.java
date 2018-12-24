package entity;

public class Number {
    private int huiQian;//待会签
    private int dingGao;//待定稿
    private int shenPi;//待审批
    private int qianDing;//待签订

    public Number(int huiQian, int dingGao, int shenPi, int qianDing) {
        this.huiQian = huiQian;
        this.dingGao = dingGao;
        this.shenPi = shenPi;
        this.qianDing = qianDing;
    }

    public Number() {
    }

    public int getDingGao() {
        return dingGao;
    }

    public void setDingGao(int dingGao) {
        this.dingGao = dingGao;
    }

    public int getShenPi() {
        return shenPi;
    }

    public void setShenPi(int shenPi) {
        this.shenPi = shenPi;
    }

    public int getQianDing() {
        return qianDing;
    }

    public void setQianDing(int qianDing) {
        this.qianDing = qianDing;
    }

    public int getHuiQian() {
        return huiQian;
    }

    public void setHuiQian(int huiQian) {
        this.huiQian = huiQian;
    }
}
